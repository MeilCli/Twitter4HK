package com.twitter.meil_mitu.twitter4hk.streaming.user

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.api.IUserStreamConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.streaming.IStreamListener
import com.twitter.meil_mitu.twitter4hk.streaming.IStreamParam
import com.twitter.meil_mitu.twitter4hk.streaming.MessageType
import com.twitter.meil_mitu.twitter4hk.streaming.Stream
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONArray
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getLong
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.toJSONObject
import java.io.InputStream
import java.util.concurrent.Executors

class UserStream<TDirectMessage, TStatus, TUser, TUserList>(
        oauth: AbsOauth,
        protected val json: IUserStreamConverter<TDirectMessage, TStatus, TUser, TUserList>) :
        AbsGet<Stream>(oauth), IStreamParam {

    override var streamListener: IStreamListener? = null
    var userStreamListener
            : IUserStreamListener<TDirectMessage, TStatus, TUser, TUserList>? = null
    private val executorService = Executors.newSingleThreadExecutor()

    /**
     * should not use this
     */
    var delimited: Boolean? by booleanParam("delimited")
    var stallWarnings: Boolean? by booleanParam("stall_warnings")
    var with: String? by stringParam("with")
    var replies: String? by stringParam("replies")
    var track: Array<String>? by stringArrayParam("track")
    var locations: Array<String>? by stringArrayParam("locations")
    var stringifyFriendIds: Boolean? by booleanParam("stringify_friend_ids")
    override val url = "https://userstream.twitter.com/1.1/user.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    override fun call(): Stream {
        return Stream(this)
    }

    override val inputStream: InputStream
        @Throws(Twitter4HKException::class)
        get() = oauth.get(this).body().byteStream()

    override fun onLine(line: String) {
        if (userStreamListener == null) {
            return
        }
        executorService.submit { handleLine(line) }
    }

    private fun handleLine(line: String) {
        try {
            val obj = toJSONObject(line)
            val type = MessageType.type(obj)
            when (type) {
                MessageType.status -> userStreamListener!!.onStatus(json.toStatus(obj))
                MessageType.deleteStatus -> {
                    val delete = getJSONObject(obj, "delete")
                    val status = getJSONObject(delete, "status")
                    userStreamListener!!.onDeleteStatus(
                            JsonUtils.getLong(status, "user_id"),
                            JsonUtils.getLong(status, "id"))
                }
                MessageType.friends -> {
                    val friends = getJSONArray(obj, "friends")
                    val ar = LongArray(friends.length())
                    for (i in 0..friends.length() - 1) {
                        ar[i] = getLong(friends, i)
                    }
                    userStreamListener!!.onFriends(ar)
                }
                MessageType.directMessage -> userStreamListener!!.onDirectMessage(
                        json.toDirectMessage(
                                if (obj.isNull("direct_message")) obj
                                else getJSONObject(obj, "direct_message")))
                MessageType.block -> userStreamListener!!.onBlock(
                        json.toUser(getJSONObject(obj, source)),
                        json.toUser(getJSONObject(obj, target)))
                MessageType.unBlock -> userStreamListener!!.onUnBlock(
                        json.toUser(getJSONObject(obj, source)),
                        json.toUser(getJSONObject(obj, target)))
                MessageType.favorite -> userStreamListener!!.onFavorite(
                        json.toUser(getJSONObject(obj, source)),
                        json.toUser(getJSONObject(obj, target)),
                        json.toStatus(getJSONObject(obj, targetObject)))
                MessageType.unFavorite -> userStreamListener!!.onUnFavorite(
                        json.toUser(getJSONObject(obj, source)),
                        json.toUser(getJSONObject(obj, target)),
                        json.toStatus(getJSONObject(obj, targetObject)))
                MessageType.follow -> userStreamListener!!.onFollow(
                        json.toUser(getJSONObject(obj, source)),
                        json.toUser(getJSONObject(obj, target)))
                MessageType.unFollow -> userStreamListener!!.onUnFollow(
                        json.toUser(getJSONObject(obj, source)),
                        json.toUser(getJSONObject(obj, target)))
                MessageType.listCreated -> userStreamListener!!.onListCreated(
                        json.toUser(getJSONObject(obj, source)),
                        json.toUserList(getJSONObject(obj, targetObject)))
                MessageType.listDestroyed -> userStreamListener!!.onListDestroyed(
                        json.toUser(getJSONObject(obj, source)),
                        json.toUserList(getJSONObject(obj, targetObject)))
                MessageType.listUpdated -> userStreamListener!!.onListUpdated(
                        json.toUser(getJSONObject(obj, source)),
                        json.toUserList(getJSONObject(obj, targetObject)))
                MessageType.listMemberAdded -> userStreamListener!!.onListMemberAdded(
                        json.toUser(getJSONObject(obj, source)),
                        json.toUser(getJSONObject(obj, target)),
                        json.toUserList(getJSONObject(obj, targetObject)))
                MessageType.listMemberRemoved -> userStreamListener!!.onListMemberRemoved(
                        json.toUser(getJSONObject(obj, source)),
                        json.toUser(getJSONObject(obj, target)),
                        json.toUserList(getJSONObject(obj, targetObject)))
                MessageType.listUserSubscribed -> userStreamListener!!.onListUserSubscribed(
                        json.toUser(getJSONObject(obj, source)),
                        json.toUser(getJSONObject(obj, target)),
                        json.toUserList(getJSONObject(obj, targetObject)))
                MessageType.listUserUnSubscribed -> userStreamListener!!.onListUserUnSubscribed(
                        json.toUser(getJSONObject(obj, source)),
                        json.toUser(getJSONObject(obj, target)),
                        json.toUserList(getJSONObject(obj, targetObject)))
                MessageType.userUpdate -> userStreamListener!!.onUserUpdate(
                        json.toUser(getJSONObject(obj, source)))
                MessageType.mute -> userStreamListener!!.onMute(
                        json.toUser(getJSONObject(obj, source)),
                        json.toUser(getJSONObject(obj, target)))
                MessageType.unMute -> userStreamListener!!.onUnMute(
                        json.toUser(getJSONObject(obj, source)),
                        json.toUser(getJSONObject(obj, target)))
                MessageType.favoritedRetweet -> userStreamListener!!.onFavoritedRetweet(
                        json.toUser(getJSONObject(obj, source)),
                        json.toUser(getJSONObject(obj, target)),
                        json.toStatus(getJSONObject(obj, targetObject)))
                MessageType.retweetedRetweet -> userStreamListener!!.onRetweetedRetweet(
                        json.toUser(getJSONObject(obj, source)),
                        json.toUser(getJSONObject(obj, target)),
                        json.toStatus(getJSONObject(obj, targetObject)))
                MessageType.quotedTweet -> userStreamListener!!.onQuotedTweet(
                        json.toUser(getJSONObject(obj, source)),
                        json.toUser(getJSONObject(obj, target)),
                        json.toStatus(getJSONObject(obj, targetObject)))
                else -> userStreamListener!!.onUnknown(line)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    companion object {
        val WithUser = "user"
        val WithFollowings = "followings"
        val RepliesAll = "all"
        private val source = "source"
        private val target = "target"
        private val targetObject = "target_object"
    }

}
