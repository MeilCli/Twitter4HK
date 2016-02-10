package com.twitter.meil_mitu.twitter4hk.api.oauth

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IOauthConverter
import com.twitter.meil_mitu.twitter4hk.exception.IncorrectException
import com.twitter.meil_mitu.twitter4hk.oauth.Oauth

class OauthAPI(oauth: AbsOauth, protected val json: IOauthConverter) : AbsAPI(oauth) {

    fun authorize(): String {
        val oauth1: Oauth
        if (oauth is Oauth) {
            oauth1 = oauth as Oauth
        } else {
            throw IncorrectException("Oauth is not Oauth")
        }
        return "https://api.twitter.com/oauth/authorize?oauth_token=${oauth1.accessToken}"
    }

    fun authenticate(): String {
        val oauth1: Oauth
        if (oauth is Oauth) {
            oauth1 = oauth as Oauth
        } else {
            throw IncorrectException("Oauth is not Oauth")
        }
        return "https://api.twitter.com/oauth/authenticate?oauth_token=${oauth1.accessToken}"
    }

    fun requestToken(): RequestToken {
        return RequestToken(oauth, json)
    }

    fun accessToken(verifier: String): AccessToken {
        return AccessToken(oauth, json, verifier)
    }
}
