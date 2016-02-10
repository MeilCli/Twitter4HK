package com.twitter.meil_mitu.twitter4hk.data


class OauthRequestToken {

    val oauthToken: String
    val oauthTokenSecret: String

    constructor(res: String) {
        var oauthTokenString = ""
        var oauthTokenSecretString = ""
        for (s in res.split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
            if (s.startsWith("oauth_token=") == true) {
                oauthTokenString = s.substring(s.indexOf('=') + 1)
            } else if (s.startsWith("oauth_token_secret=") == true) {
                oauthTokenSecretString = s.substring(s.indexOf('=') + 1)
            }
        }
        oauthToken = oauthTokenString
        oauthTokenSecret = oauthTokenSecretString
    }

    override fun toString(): String {
        return "OauthRequestToken{OauthToken='$oauthToken', OauthTokenSecret='$oauthTokenSecret'}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OauthRequestToken) return false

        if (oauthToken != other.oauthToken) return false
        if (oauthTokenSecret != other.oauthTokenSecret) return false

        return true
    }

    override fun hashCode(): Int {
        var result = oauthToken.hashCode()
        result = 31 * result + oauthTokenSecret.hashCode()
        return result
    }
}
