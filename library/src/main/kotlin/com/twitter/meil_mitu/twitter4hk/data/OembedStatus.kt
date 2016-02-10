package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject

class OembedStatus {

    val url: String
    val type: String
    val html: String

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        url = getString(obj, "url")
        type = getString(obj, "type")
        html = getString(obj, "html")
    }

    override fun toString(): String {
        return "OembedStatus{Url='$url', Type='$type', Html='$html'}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OembedStatus) return false

        if (html != other.html) return false
        if (type != other.type) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = url.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + html.hashCode()
        return result
    }
}
