package com.twitter.meil_mitu.twitter4hk.api.oauth2

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IOauth2TokenConverter
import com.twitter.meil_mitu.twitter4hk.data.Oauth2Token
import com.twitter.meil_mitu.twitter4hk.exception.IncorrectException
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.oauth.Oauth2

class Token(
        oauth: AbsOauth,
        protected val json: IOauth2TokenConverter<Oauth2Token>) :
        AbsPost<Oauth2Token>(oauth) {

    private var oauth2: Oauth2
    private var grantType: String? by stringParam("grant_type")
    override val url = "https://api.twitter.com/oauth2/token"
    override val allowOauthType = OauthType.oauth2Basic
    override val isAuthorization = true

    init {
        if (oauth is Oauth2 == false) {
            throw IncorrectException("Oauth is not Oauth2")
        }
        oauth2 = oauth as Oauth2
        grantType = "client_credentials"
    }

    @Throws(Twitter4HKException::class)
    override fun call(): Oauth2Token {
        val token = json.toOauth2Token(oauth.post(this))
        oauth2.accessToken = token.accessToken
        oauth2.tokenType = token.tokenType
        return token
    }
}
