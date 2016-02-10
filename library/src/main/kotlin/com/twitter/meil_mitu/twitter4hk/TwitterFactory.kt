package com.twitter.meil_mitu.twitter4hk

import com.twitter.meil_mitu.twitter4hk.oauth.Oauth
import com.twitter.meil_mitu.twitter4hk.oauth.Oauth2
import com.twitter.meil_mitu.twitter4hk.oauth.OauthEcho
import java.util.*

class TwitterFactory private constructor() {

    private val map = HashMap<OauthItem, Twitter4HK>()

    fun <T : AbsOauth> getOauth(oauthClass: Class<T>, consumerKey: String,
                                consumerSecret: String): AbsOauth? {
        return getOauth(oauthClass, consumerKey, consumerSecret, null, null)
    }

    fun <T : AbsOauth> getOauth(
            oauthClass: Class<T>,
            consumerKey: String,
            consumerSecret: String,
            accessToken: String?,
            accessTokenSecret: String?): AbsOauth? {
        val tw = getTwitter(oauthClass, consumerKey, consumerSecret, accessToken, accessTokenSecret)
        if (tw != null) {
            return tw.oauth
        }
        return null
    }

    fun <T : AbsOauth> getTwitter(oauthClass: Class<T>, consumerKey: String,
                                  consumerSecret: String): Twitter4HK? {
        return getTwitter(oauthClass, consumerKey, consumerSecret, null, null)
    }

    fun <T : AbsOauth> getTwitter(
            oauthClass: Class<T>,
            consumerKey: String,
            consumerSecret: String,
            accessToken: String?,
            accessTokenSecret: String?): Twitter4HK? {
        if (Oauth2::class.java == oauthClass == false &&
                (accessToken == null || accessTokenSecret == null)) {
            throw IllegalArgumentException("accessToken or accessTokenSecret is null")
        }
        val item = OauthItem(oauthClass, consumerKey, consumerSecret,
                accessToken, accessTokenSecret)
        if (map.containsKey(item)) {
            val tw = map[item]
            if (tw != null) {
                return tw
            }
        }
        if (Oauth::class.java == oauthClass) {
            val tw = Twitter4HK(Oauth(null, consumerKey, consumerSecret,
                    accessToken, accessTokenSecret))
            map.put(item, tw)
            return tw
        } else if (Oauth2::class.java == oauthClass) {
            val tw = Twitter4HK(Oauth2(null, consumerKey, consumerSecret))
            map.put(item, tw)
            return tw
        } else if (OauthEcho::class.java == oauthClass) {
            val tw = Twitter4HK(OauthEcho(null, null, consumerKey, consumerSecret,
                    accessToken, accessTokenSecret))
            map.put(item, tw)
            return tw
        }
        throw IllegalArgumentException("oauthClass is not default defined")
    }

    private inner class OauthItem internal constructor(
            private val oauthClass: Class<out AbsOauth>,
            private val consumerKey: String,
            private val consumerSecret: String,
            private val accessToken: String?,
            private val accessTokenSecret: String?) {

        override fun toString(): String {
            return "OauthItem{oauthClass=$oauthClass, consumerKey='$consumerKey', consumerSecret='$consumerSecret', accessToken='$accessToken', accessTokenSecret='$accessTokenSecret'}"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is OauthItem) return false

            if (oauthClass != other.oauthClass) return false
            if (consumerKey != other.consumerKey) return false
            if (consumerSecret != other.consumerSecret) return false
            if (if (accessToken != null) accessToken != other.accessToken else false)
                return false
            if (if (accessTokenSecret != null) accessTokenSecret != other.accessTokenSecret else false)
                return false

            return true
        }

        override fun hashCode(): Int {
            var result = oauthClass.hashCode()
            result = 31 * result + consumerKey.hashCode()
            result = 31 * result + consumerSecret.hashCode()
            result = 31 * result + (if (accessToken != null) accessToken.hashCode() else 0)
            result = 31 * result + (if (accessTokenSecret != null) accessTokenSecret.hashCode() else 0)
            return result
        }
    }

    companion object {

        private var _instance: TwitterFactory? = null

        fun getInstance(): TwitterFactory {
            if (_instance == null) {
                _instance = TwitterFactory()
            }
            return _instance!!
        }
    }
}
