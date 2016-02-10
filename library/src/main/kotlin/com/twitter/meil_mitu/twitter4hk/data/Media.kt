package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getLong
import org.json.JSONObject

class Media {

    val mediaId: Long

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        mediaId = getLong(obj, "media_id")
    }

    override fun toString(): String {
        return "Media{MediaId=$mediaId}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Media) return false

        if (mediaId != other.mediaId) return false

        return true
    }

    override fun hashCode(): Int {
        return (mediaId xor (mediaId.ushr(32))).toInt()
    }

}
