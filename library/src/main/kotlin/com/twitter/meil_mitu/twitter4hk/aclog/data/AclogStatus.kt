package com.twitter.meil_mitu.twitter4hk.aclog.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getInt
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONArray
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getLong
import org.json.JSONObject
import java.util.*

class AclogStatus {

    val id: Long
    val userId: Long
    val favoritesCount: Int
    val retweetsCount: Int
    val favoriters: LongArray
    val retweeters: LongArray

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        id = getLong(obj, "id")
        userId = getLong(obj, "user_id")
        favoritesCount = getInt(obj, "favorites_count")
        retweetsCount = getInt(obj, "retweets_count")
        if (obj.isNull("favoriters")) {
            favoriters = LongArray(0)
        } else {
            val ar = getJSONArray(obj, "favoriters")
            val size = ar.length()
            favoriters = LongArray(size)
            for (i in 0..size - 1) {
                favoriters[i] = getLong(ar, i)
            }
        }
        if (obj.isNull("retweeters")) {
            retweeters = LongArray(0)
        } else {
            val ar = getJSONArray(obj, "retweeters")
            val size = ar.length()
            retweeters = LongArray(size)
            for (i in 0..size - 1) {
                retweeters[i] = getLong(ar, i)
            }
        }
    }

    override fun toString(): String {
        return "AclogStatus{" + "Id=" + id + ", UserId=" + userId + ", FavoritesCount=" + favoritesCount + ", RetweetsCount=" + retweetsCount + ", Favoriters=" + Arrays.toString(favoriters) + ", Retweeters=" + Arrays.toString(retweeters) + '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AclogStatus) return false

        if (id != other.id) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = (id xor (id.ushr(32))).toInt()
        result = 31 * result + (userId xor (userId.ushr(32))).toInt()
        return result
    }

}
