package com.twitter.meil_mitu.twitter4hk.api.oauth

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IOauthRequestTokenConverter
import com.twitter.meil_mitu.twitter4hk.data.OauthRequestToken
import com.twitter.meil_mitu.twitter4hk.exception.IncorrectException
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.oauth.Oauth

class RequestToken(
        oauth: AbsOauth,
        protected val json: IOauthRequestTokenConverter<OauthRequestToken>) :
        AbsPost<OauthRequestToken>(oauth) {

    private var oauth1: Oauth
    var oauthCallback: String? by stringParam("oauth_callback")
    override val url = "https://api.twitter.com/oauth/request_token"
    override val allowOauthType = OauthType.oauth1RequestToken
    override val isAuthorization = true

    init {
        if (oauth is Oauth == false) {
            throw IncorrectException("Oauth is not Oauth")
        }
        oauth1 = oauth as Oauth
    }

    @Throws(Twitter4HKException::class)
    override fun call(): OauthRequestToken {
        val token = json.toOauthRequestToken(oauth.post(this))
        oauth1.accessToken = token.oauthToken
        oauth1.accessTokenSecret = token.oauthTokenSecret
        return token
    }
}
