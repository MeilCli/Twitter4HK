package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getInt
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject

class VideoVariant  {

    val bitrate: Int
    val contentType: String
    val url: String

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        bitrate = getInt(obj, "bitrate", -1)
        contentType = getString(obj, "content_type")
        url = getString(obj, "url")
    }

    override fun toString(): String {
        return "VideoVariant{Bitrate=$bitrate, ContentType='$contentType', Url='$url'}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VideoVariant) return false

        if (bitrate != other.bitrate) return false
        if (contentType != other.contentType) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = bitrate
        result = 31 * result + contentType.hashCode()
        result = 31 * result + url.hashCode()
        return result
    }

}
