package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject

class Oauth2Token {

    val tokenType: String
    val accessToken: String

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        this.tokenType = getString(obj, "token_type")
        this.accessToken = getString(obj, "access_token")
    }

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject, tokenType: String?) {
        if (tokenType == null) {
            throw Twitter4HKException("tokenType is null")
        }
        this.tokenType = tokenType
        this.accessToken = getString(obj, "access_token")
    }

    override fun toString(): String {
        return "Oauth2Token{TokenType='$tokenType', AccessToken='$accessToken'}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Oauth2Token) return false

        if (accessToken != other.accessToken) return false
        if (tokenType != other.tokenType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = tokenType.hashCode()
        result = 31 * result + accessToken.hashCode()
        return result
    }

}
