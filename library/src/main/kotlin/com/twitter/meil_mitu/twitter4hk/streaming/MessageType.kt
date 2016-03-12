package com.twitter.meil_mitu.twitter4hk.streaming

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject

object MessageType {

    val unknown = 0
    val status = 1
    val directMessage = 2
    val block = 3
    val unBlock = 4
    val favorite = 5
    val unFavorite = 6
    val follow = 7
    val unFollow = 8
    val listCreated = 9
    val listDestroyed = 10
    val listUpdated = 11
    val listMemberAdded = 12
    val listMemberRemoved = 13
    val listUserSubscribed = 14
    val listUserUnSubscribed = 15
    val userUpdate = 16
    //https://blog.twitter.com/ja/2014/streaming-api-new-features
    val mute = 17
    val unMute = 18
    //https://twittercommunity.com/t/addition-of-new-social-event-to-streaming-api-retweet-has-been-retweeted/30911
    val favoritedRetweet = 19
    val retweetedRetweet = 20
    //https://twittercommunity.com/t/quote-tweet-events-in-the-streaming-api/38457
    val quotedTweet = 21
    val friends = 22
    val deleteStatus = 23

    fun type(obj: JSONObject): Int {
        if (obj.isNull("sender") == false) {
            return MessageType.directMessage
        } else if (obj.isNull("text") == false) {
            return MessageType.status
        } else if (obj.isNull("direct_message") == false) {
            return MessageType.directMessage
        } else if (obj.isNull("friends") == false) {
            return MessageType.friends
        } else if (obj.isNull("delete") == false) {
            val delete = getJSONObject(obj, "delete")
            if (delete.isNull("status") == false) {
                return MessageType.deleteStatus
            }
        } else if (obj.isNull("event") == false) {
            try {
                val event = getString(obj, "event")
                if (event == "block") {
                    return MessageType.block
                } else if (event == "unblock") {
                    return MessageType.unBlock
                } else if (event == "favorite") {
                    return MessageType.favorite
                } else if (event == "unfavorite") {
                    return MessageType.unFavorite
                } else if (event == "follow") {
                    return MessageType.follow
                } else if (event == "unfollow") {
                    return MessageType.unFollow
                } else if (event == "list_created") {
                    return MessageType.listCreated
                } else if (event == "list_destroyed") {
                    return MessageType.listDestroyed
                } else if (event == "list_updated") {
                    return MessageType.listUpdated
                } else if (event == "list_member_added") {
                    return MessageType.listMemberAdded
                } else if (event == "list_member_removed") {
                    return MessageType.listMemberRemoved
                } else if (event == "list_user_subscribed") {
                    return MessageType.listUserSubscribed
                } else if (event == "list_user_unsubscribed") {
                    return MessageType.listUserUnSubscribed
                } else if (event == "user_update") {
                    return MessageType.userUpdate
                } else if (event == "mute") {
                    return MessageType.mute
                } else if (event == "unmute") {
                    return MessageType.unMute
                } else if (event == "favorited_retweet") {
                    return MessageType.favoritedRetweet
                } else if (event == "retweeted_retweet") {
                    return MessageType.retweetedRetweet
                } else if (event == "quoted_tweet") {
                    return MessageType.quotedTweet
                }
            } catch (e: Twitter4HKException) {
                e.printStackTrace()
            }

        }
        return MessageType.unknown
    }
}
