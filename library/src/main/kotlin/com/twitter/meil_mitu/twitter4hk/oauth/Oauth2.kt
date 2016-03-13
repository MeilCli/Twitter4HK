package com.twitter.meil_mitu.twitter4hk.oauth


import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.*
import com.twitter.meil_mitu.twitter4hk.api.oauth2.InvalidateToken
import com.twitter.meil_mitu.twitter4hk.api.oauth2.Token
import com.twitter.meil_mitu.twitter4hk.converter.TwitterConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.*

class Oauth2 : AbsOauth {

    var accessToken: String? = null
    var tokenType: String? = null

    constructor(
            config: Twitter4HKConfig?,
            consumerKey: String?,
            consumerSecret: String?) : super(true, config, consumerKey, consumerSecret) {
    }

    constructor(
            config: Twitter4HKConfig?,
            consumerKey: String?,
            consumerSecret: String?,
            accessToken: String?,
            tokenType: String?) : super(true, config, consumerKey, consumerSecret) {
        this.accessToken = accessToken
        this.tokenType = tokenType
    }

    @Throws(Twitter4HKException::class)
    override fun<T> get(param: AbsGet<T>): Response {
        requirePermission(param.allowOauthType, OauthType.oauth2, OauthType.oauth2Basic)
        if (accessToken == null || tokenType == null) {
            Token(this, TwitterConverter.getDefaultConverter()).call()// token is post
        }
        val builder = Request.Builder()
        builder.url(toUrl(param))
        builder.header("User-Agent", config.userAgent)
        if (param.isAuthorization) {
            if (includePermission(param.allowOauthType, OauthType.oauth2)) {
                builder.addHeader("Authorization", "$tokenType $accessToken")
            } else {
                builder.addHeader("Authorization", "Basic ${createAuthorization()}")
            }
        }
        builder.get()
        try {
            return tryAndThrow { call(builder.build()) }.apply {
                checkError(this)
            }
        } catch (e: Twitter4HKException) {
            if (e.errorCode == 89) {
                InvalidateToken(this, TwitterConverter.getDefaultConverter()).call()
                return get(param)
            }
            throw e
        }
    }

    @Throws(Twitter4HKException::class)
    override fun<T> post(param: AbsPost<T>): Response {
        requirePermission(param.allowOauthType, OauthType.oauth2, OauthType.oauth2Basic)
        val builder = Request.Builder()
        builder.url(param.url)
        builder.header("User-Agent", config.userAgent)
        if (param.isAuthorization) {
            if (includePermission(param.allowOauthType, OauthType.oauth2)) {
                builder.addHeader("Authorization", "$tokenType $accessToken")
            } else {
                builder.addHeader("Authorization", "Basic ${createAuthorization()}")
            }
        }
        builder.post(toBody(param))
        return tryAndThrow { call(builder.build()) }.apply {
            checkError(this)
        }
    }

    protected fun createAuthorization(): String {
        val encodedConsumerKey = consumerKey!!.urlEncode()
        val encodedConsumerSecret = consumerSecret!!.urlEncode()
        val bearer = "$encodedConsumerKey:$encodedConsumerSecret"
        return base64Encode(bearer.toByteArray())
    }
}
