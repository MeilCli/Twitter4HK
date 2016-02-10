package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getBoolean
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getDate
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getInt
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getLong
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject
import java.util.*

class Status {

    val createdAt: Date?
    val currentUserRetweet: CurrentUserRetweet?
    val entities: Entities
    val extendedEntities: Entities
    val favoriteCount: Int
    val retweetCount: Int
    val isFavorited: Boolean
    val isRetweeted: Boolean
    val id: Long
    val inReplyToStatusId: Long
    val inReplyToUserId: Long
    val inReplyToScreenName: String?
    val lang: String?
    //https://twittercommunity.com/t/api-payloads-to-include-original-quoted-tweet-objects/38184
    val quotedStatus : Status?
    val source: String
    val text: String
    val retweetedStatus: Status?
    val user: User?
    val entitySupport: EntitySupport
    val sourceSupport: SourceSupport

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        createdAt = getDate(obj, "created_at")
        if (obj.isNull("current_user_retweet")) {
            currentUserRetweet = null
        } else {
            currentUserRetweet = CurrentUserRetweet(getJSONObject(obj, "current_user_retweet"))
        }
        if (obj.isNull("entities")) {
            entities = Entities()
        } else {
            entities = Entities(getJSONObject(obj, "entities"))
        }
        if (obj.isNull("extended_entities")) {
            extendedEntities = Entities()
        } else {
            extendedEntities = Entities(getJSONObject(obj, "extended_entities"))
        }
        favoriteCount = getInt(obj, "favorite_count", 0)
        isFavorited = getBoolean(obj, "favorited", false)
        id = getLong(obj, "id")
        inReplyToScreenName = getString(obj, "in_reply_to_screen_name", null)
        inReplyToStatusId = getLong(obj, "in_reply_to_status_id", -1)
        inReplyToUserId = getLong(obj, "in_reply_to_user_id", -1)
        lang = getString(obj, "lang", null)
        if(obj.isNull("quoted_status")){
            quotedStatus=null
        }else{
            quotedStatus= Status(getJSONObject(obj,"quoted_status"))
        }
        retweetCount = getInt(obj, "retweet_count", 0)
        isRetweeted = getBoolean(obj, "retweeted", false)
        if (obj.isNull("retweeted_status")) {
            retweetedStatus = null
        } else {
            retweetedStatus = Status(getJSONObject(obj, "retweeted_status"))
        }
        source = getString(obj, "source")
        text = getString(obj, "text")
        if (obj.isNull("user")) {
            user = null
        } else {
            user = User(getJSONObject(obj, "user"))
        }
        entitySupport = EntitySupport(text, entities)
        sourceSupport = SourceSupport(source)
    }

    override fun toString(): String {
        return "Status{CreatedAt=$createdAt, CurrentUserRetweet=$currentUserRetweet, Entities=$entities, ExtendedEntities=$extendedEntities, FavoriteCount=$favoriteCount, RetweetCount=$retweetCount, IsFavorited=$isFavorited, IsRetweeted=$isRetweeted, Id=$id, InReplyToStatusId=$inReplyToStatusId, InReplyToUserId=$inReplyToUserId, InReplyToScreenName='$inReplyToScreenName', Lang='$lang', Source='$source', Text='$text', RetweetedStatus=$retweetedStatus, User=$user}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Status) return false

        if (id != other.id) return false
        if (if (retweetedStatus != null) retweetedStatus != other.retweetedStatus else other.retweetedStatus != null)
            return false
        if (user != other.user) return false

        return true
    }

    override fun hashCode(): Int {
        var result = (id xor (id.ushr(32))).toInt()
        result = 31 * result + (if (retweetedStatus != null) retweetedStatus.hashCode() else 0)
        result = 31 * result + user!!.hashCode()
        return result
    }

}
