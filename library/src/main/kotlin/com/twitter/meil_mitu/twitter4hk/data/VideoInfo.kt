package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONArray
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getLong
import org.json.JSONObject
import java.util.*

class VideoInfo {

    val durationMillis: Long
    var variants: Array<VideoVariant>

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        durationMillis = getLong(obj, "duration_millis")
        if (obj.isNull("variants")) {
            variants = emptyArray()
        } else {
            val ar = getJSONArray(obj, "variants")
            val size = ar.length()
            variants = Array(size, { i -> VideoVariant(getJSONObject(ar, i)) })
        }
    }

    override fun toString(): String {
        return "VideoInfo{" + "DurationMillis=" + durationMillis + ", Variants=" + Arrays.toString(variants) + '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VideoInfo) return false

        if (durationMillis != other.durationMillis) return false
        if (!Arrays.equals(variants, other.variants)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = (durationMillis xor (durationMillis.ushr(32))).toInt()
        result = 31 * result + Arrays.hashCode(variants)
        return result
    }


}
