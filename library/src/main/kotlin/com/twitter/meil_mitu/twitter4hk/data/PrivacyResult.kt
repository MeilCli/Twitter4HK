package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject

class PrivacyResult {

    val privacy: String

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        privacy = getString(obj, "privacy")
    }

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is PrivacyResult) return false

        if (privacy != other.privacy) return false

        return true
    }

    override fun hashCode(): Int{
        return privacy.hashCode()
    }

}
