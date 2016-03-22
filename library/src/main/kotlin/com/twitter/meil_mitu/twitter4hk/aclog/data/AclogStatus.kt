package com.twitter.meil_mitu.twitter4hk.aclog.data


import net.meilcli.hkjson.HKJson
import net.meilcli.hkjson.IJson
import net.meilcli.hkjson.objects.IntJson
import net.meilcli.hkjson.objects.LongJson
import org.json.JSONObject
import java.util.*

class AclogStatus(json: JSONObject? = null) : IJson by HKJson(json) {

    val id by LongJson.json("id")
    val userId by LongJson.json("user_id")
    val favoritesCount by IntJson.json("favorites_count")
    val retweetsCount by IntJson.json("retweets_count")
    val favoriters by LongJson.jsonOptionalArray("favoriters")
    val retweeters by LongJson.jsonOptionalArray("retweeters")

    init {
        clearJsonCache()
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
