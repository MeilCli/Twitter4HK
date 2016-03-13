package com.twitter.meil_mitu.twitter4hk.oauth

import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.Twitter4HKConfig
import com.twitter.meil_mitu.twitter4hk.api.account.VerifyCredentials
import com.twitter.meil_mitu.twitter4hk.converter.TwitterConverter
import com.twitter.meil_mitu.twitter4hk.data.User
import com.twitter.meil_mitu.twitter4hk.exception.IncorrectException
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.tryAndThrow
import java.net.CookieManager

class OauthEcho : Oauth {
    protected var check: IOauthEchoCheck? = null
    protected var cookie: CookieManager? = null

    constructor(
            check: IOauthEchoCheck?,
            config: Twitter4HKConfig?) : super(false, config, null, null) {
        init(check)
    }

    constructor(
            check: IOauthEchoCheck?,
            config: Twitter4HKConfig?,
            consumerKey: String?,
            consumerSecret: String?) : super(false, config, consumerKey, consumerSecret) {
        init(check)
    }

    constructor(
            check: IOauthEchoCheck?,
            config: Twitter4HKConfig?,
            consumerKey: String?,
            consumerSecret: String?,
            accessToken: String?,
            accessTokenSecret: String?) :
    super(false, config, consumerKey, consumerSecret, accessToken, accessTokenSecret) {
        init(check)
    }

    private fun init(check: IOauthEchoCheck?) {
        if (verify == null) {
            verify = VerifyCredentials(this, TwitterConverter.getDefaultConverter())
        }
        this.check = check
        if (this.check == null) {
            this.check = defaultCheck
        }
    }

    override fun okhttpSetting(config: Twitter4HKConfig) {
        super.okhttpSetting(config)
        //for aclog
        if (cookie == null) {
            cookie = CookieManager()
        }
        http!!.setCookieHandler(cookie)
    }

    @Throws(Twitter4HKException::class)
    override fun<T> get(param: AbsGet<T>): Response {
        if ((param.allowOauthType and OauthType.oauthEcho) == 0) {
            throw IncorrectException("do not allow OauthType")
        }
        val builder = Request.Builder()
        builder.url(toUrl(param))
        builder.header("User-Agent", config.userAgent)
        if (param.isAuthorization && consumerKey != null &&
                consumerSecret != null && accessToken != null && accessTokenSecret != null) {
            tryAndThrow {
                builder.addHeader("X-Auth-Service-Provider", verify!!.url)
                builder.addHeader("X-Verify-Credentials-Authorization", "OAuth ${createAuthorization(verify!!, false)}")
            }
        }
        builder.get()
        return tryAndThrow { call(builder.build()) }.apply {
            check!!.checkError(this)
        }
    }

    @Throws(Twitter4HKException::class)
    override fun<T> post(param: AbsPost<T>): Response {
        if ((param.allowOauthType and OauthType.oauthEcho) == 0) {
            throw IncorrectException("do not allow OauthType")
        }
        val builder = Request.Builder()
        builder.url(param.url)
        builder.header("User-Agent", config.userAgent)
        if (param.isAuthorization && consumerKey != null &&
                consumerSecret != null && accessToken != null && accessTokenSecret != null) {
            tryAndThrow {
                builder.addHeader("X-Auth-Service-Provider", verify!!.url)
                builder.addHeader("X-Verify-Credentials-Authorization", "OAuth ${createAuthorization(verify!!, false)}")
            }
        }
        builder.post(toBody(param))
        return tryAndThrow { call(builder.build()) }.apply {
            check!!.checkError(this)
        }

    }

    companion object {

        protected var verify: VerifyCredentials<User>? = null

        private val defaultCheck = object : IOauthEchoCheck {
            override fun checkError(res: Response) {
                if (res.isSuccessful == false) throw Twitter4HKException("not success")
            }
        }
    }

}
