package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.putString
import org.json.JSONObject

open class URLEntity : Entity {

    val url: String
    val displayUrl: String
    val expandedUrl: String

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) : super(obj) {
        url = getString(obj, "url")
        displayUrl = getString(obj, "display_url")
        expandedUrl = getString(obj, "expanded_url")
    }

    override fun toString(): String {
        return super.toString() + " URLEntity{" + "Url='" + url + '\'' + ", DisplayUrl='" + displayUrl + '\'' + ", ExpandedUrl='" + expandedUrl + '\'' + '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is URLEntity) return false
        if (!super.equals(other)) return false

        if (displayUrl != other.displayUrl) return false
        if (expandedUrl != other.expandedUrl) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + displayUrl.hashCode()
        result = 31 * result + expandedUrl.hashCode()
        return result
    }

}
