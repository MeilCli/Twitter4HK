package com.twitter.meil_mitu.twitter4hk.data

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getLong
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.putJSONObject
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.putLong
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.putNull
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.putString
import org.json.JSONObject

class MediaEntity : URLEntity {

    val id: Long
    val mediaUrl: String
    val type: String
    val videoInfo: VideoInfo?

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) : super(obj) {
        id = getLong(obj, "id")
        mediaUrl = getString(obj, "media_url")
        type = getString(obj, "type")
        if (obj.isNull("video_info")) {
            videoInfo = null
        } else {
            videoInfo = VideoInfo(getJSONObject(obj, "video_info"))
        }
    }

    override fun toString(): String {
        return super.toString() + " MediaEntity{" + "Id=" + id + ", MediaUrl='" + mediaUrl + '\'' + ", Type='" + type + '\'' + '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MediaEntity) return false
        if (!super.equals(other)) return false

        if (id != other.id) return false
        if (mediaUrl != other.mediaUrl) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (id xor (id.ushr(32))).toInt()
        result = 31 * result + mediaUrl.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }

}
