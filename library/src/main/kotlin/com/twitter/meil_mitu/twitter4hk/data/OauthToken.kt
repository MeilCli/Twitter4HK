package com.twitter.meil_mitu.twitter4hk.data


class OauthToken {

    val oauthToken: String
    val oauthTokenSecret: String
    val screenName: String
    val name: String
    val userId: Long

    constructor(res: String) {
        var oauthTokenString = ""
        var oauthTokenSecretString = ""
        var userIdString = ""
        var screenNameString = ""
        var nameString = ""
        for (s in res.split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
            if (s.startsWith("oauth_token=") == true) {
                oauthTokenString = s.substring(s.indexOf('=') + 1)
            } else if (s.startsWith("oauth_token_secret=") == true) {
                oauthTokenSecretString = s.substring(s.indexOf('=') + 1)
            } else if (s.startsWith("user_id=") == true) {
                userIdString = s.substring(s.indexOf('=') + 1)
            } else if (s.startsWith("screen_name=") == true) {
                screenNameString = s.substring(s.indexOf('=') + 1)
            } else if (s.startsWith("name=") == true) {
                nameString = s.substring(s.indexOf('=') + 1)
            }
        }
        oauthToken = oauthTokenString
        oauthTokenSecret = oauthTokenSecretString
        screenName = screenNameString
        name = nameString
        userId = java.lang.Long.parseLong(userIdString)
    }

    override fun toString(): String {
        return "OauthToken{OauthToken='$oauthToken', OauthTokenSecret='$oauthTokenSecret', ScreenName='$screenName', Name='$name', UserId=$userId}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OauthToken) return false

        if (userId != other.userId) return false
        if (name != other.name) return false
        if (oauthToken != other.oauthToken) return false
        if (oauthTokenSecret != other.oauthTokenSecret) return false
        if (screenName != other.screenName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = oauthToken.hashCode()
        result = 31 * result + oauthTokenSecret.hashCode()
        result = 31 * result + screenName.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + (userId xor (userId.ushr(32))).toInt()
        return result
    }

}
