package com.twitter.meil_mitu.twitter4hk.data

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getBoolean
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject

class Setting {

    val language: String
    val screenName: String
    val isProtected: Boolean

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        language = getString(obj, "language")
        isProtected = getBoolean(obj, "protected")
        screenName = getString(obj, "screen_name")
    }

    override fun toString(): String {
        return "Setting{Language='$language', ScreenName='$screenName', IsProtected=$isProtected}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Setting) return false

        if (isProtected != other.isProtected) return false
        if (language != other.language) return false
        if (screenName != other.screenName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = language.hashCode()
        result = 31 * result + screenName.hashCode()
        result = 31 * result + (if (isProtected) 1 else 0)
        return result
    }
}
