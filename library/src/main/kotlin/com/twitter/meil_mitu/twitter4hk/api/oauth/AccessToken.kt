package com.twitter.meil_mitu.twitter4hk.api.oauth

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IOauthTokenConverter
import com.twitter.meil_mitu.twitter4hk.data.OauthToken
import com.twitter.meil_mitu.twitter4hk.exception.IncorrectException
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.oauth.Oauth

class AccessToken(
        oauth: AbsOauth,
        protected val json: IOauthTokenConverter<OauthToken>,
        oauthVerifier: String) : AbsPost<OauthToken>(oauth) {

    private var oauth1: Oauth
    private var oauthVerifier: String? by stringParam("oauth_verifier")
    override val url = "https://api.twitter.com/oauth/access_token"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    init {
        if (oauth is Oauth == false) {
            throw IncorrectException("Oauth is not Oauth")
        }
        oauth1 = oauth as Oauth
        this.oauthVerifier = oauthVerifier
    }

    @Throws(Twitter4HKException::class)
    override fun call(): OauthToken {
        val token = json.toOauthToken(oauth.post(this))
        oauth1.accessToken = token.oauthToken
        oauth1.accessTokenSecret = token.oauthTokenSecret
        return token
    }
}
