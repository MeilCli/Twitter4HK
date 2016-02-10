package com.twitter.meil_mitu.twitter4hk.data

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getInt
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject

class BannerSize {

    val h: Int
    val w: Int
    val url: String

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        h = getInt(obj, "h")
        w = getInt(obj, "w")
        url = getString(obj, "url")
    }

    override fun toString(): String {
        return "BannerSize{H=$h, W=$w, Url='$url'}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BannerSize) return false
        if (h != other.h) return false
        if (w != other.w) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = h
        result = 31 * result + w
        result = 31 * result + url.hashCode()
        return result
    }

}
