package com.twitter.meil_mitu.twitter4hk.oauth


import com.squareup.okhttp.Request
import com.squareup.okhttp.RequestBody
import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.*
import com.twitter.meil_mitu.twitter4hk.exception.IncorrectException
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.base64Encode
import com.twitter.meil_mitu.twitter4hk.util.tryAndThrow
import com.twitter.meil_mitu.twitter4hk.util.urlEncode
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

open class Oauth : AbsOauth {

    var accessToken: String? = null
    var accessTokenSecret: String? = null

    constructor(
            config: Twitter4HKConfig?,
            consumerKey: String,
            consumerSecret: String) : super(true, config, consumerKey, consumerSecret) {
    }

    constructor(
            config: Twitter4HKConfig?,
            consumerKey: String,
            consumerSecret: String,
            accessToken: String?,
            accessTokenSecret: String?) : super(true, config, consumerKey, consumerSecret) {
        this.accessToken = accessToken
        this.accessTokenSecret = accessTokenSecret
    }

    protected constructor(
            isRequireConsumer: Boolean,
            config: Twitter4HKConfig?,
            consumerKey: String?,
            consumerSecret: String?) :
    super(isRequireConsumer, config, consumerKey, consumerSecret) {
    }

    protected constructor(
            isRequireConsumer: Boolean,
            config: Twitter4HKConfig?,
            consumerKey: String?,
            consumerSecret: String?,
            accessToken: String?,
            accessTokenSecret: String?) :
    super(isRequireConsumer, config, consumerKey, consumerSecret) {
        this.accessToken = accessToken
        this.accessTokenSecret = accessTokenSecret
    }

    @Throws(Twitter4HKException::class)
    override fun <T> get(param: AbsGet<T>): Response {
        if ((param.allowOauthType and OauthType.oauth1) == 0 &&
                (param.allowOauthType and OauthType.oauth1RequestToken) == 0) {
            throw IncorrectException("do not allow OauthType")
        }
        val builder = Request.Builder()
        builder.url(toUrl(param))
        builder.header("User-Agent", config.userAgent)
        if (param.isAuthorization) {
            tryAndThrow {
                builder.addHeader("Authorization", "OAuth ${createAuthorization(param, false)}")
            }
        }
        builder.get()
        return tryAndThrow { call(builder.build()) }.apply {
                checkError(this)
            }
    }

    @Throws(Twitter4HKException::class)
    override fun <T> post(param: AbsPost<T>): Response {
        if ((param.allowOauthType and OauthType.oauth1) == 0 &&
                (param.allowOauthType and OauthType.oauth1RequestToken) == 0) {
            throw IncorrectException("do not allow OauthType")
        }
        val builder = Request.Builder()
        builder.url(param.url)
        builder.header("User-Agent", config.userAgent)
        if (param.isAuthorization) {
            tryAndThrow {
                builder.addHeader("Authorization", "OAuth ${createAuthorization(param, param.fileSize > 0)}")
            }
        }
        if ((param.allowOauthType and OauthType.oauth1RequestToken) == 0) {
            builder.post(toBody(param))
        } else {
            builder.post(RequestBody.create(AbsOauth.mediaText, ""))
        }
        return tryAndThrow { call(builder.build()) }.apply {
                checkError(this)
            }
    }

    protected fun makeOauthNonce(): String {
        return Random().nextInt(99999999).toString()
    }

    protected fun makeTimestamp(): String {
        return (System.currentTimeMillis() / 1000).toString()
    }

    @Throws(Exception::class)
    protected fun <T> createAuthorization(method: AbsMethod<T>, isMultiPost: Boolean): String {
        val nonce = makeOauthNonce()
        val time = makeTimestamp()
        val callback: String?
        if ((method.allowOauthType and OauthType.oauth1RequestToken) == 0) {
            callback = null
        } else {
            callback = method.paramMap["oauth_callback"]
        }
        val params = TreeMap<String, String>()
        params.put("oauth_nonce", nonce)
        if (callback != null) {
            params.put("oauth_callback", callback)
        }
        params.put("oauth_signature_method", "HMAC-SHA1")
        params.put("oauth_timestamp", time)
        params.put("oauth_consumer_key", consumerKey!!)
        if (accessToken != null) {
            params.put("oauth_token", accessToken!!)
        }
        params.put("oauth_version", "1.0")
        params.put("oauth_signature", createSignature(
                method,
                isMultiPost == false ||
                        (method.allowOauthType and OauthType.oauth1RequestToken) == 0,
                callback,
                nonce,
                time))
        val baseValue = StringBuilder()
        for (e in params.entries) {
            if (baseValue.length > 0) {
                baseValue.append(',')
                baseValue.append(' ')
            }
            baseValue.append(e.key.urlEncode())
            baseValue.append('=')
            baseValue.append('"')
            baseValue.append(e.value.urlEncode())
            baseValue.append('"')
        }
        val baseString = baseValue.toString()
        return baseString
    }

    @Throws(Exception::class)
    protected fun <T> createSignature(
            method: AbsMethod<T>,
            isContainValue: Boolean,
            callback: String?,
            nonce: String,
            time: String): String {
        val params = TreeMap<String, String>()
        params.put("oauth_nonce", nonce)
        if (callback != null) {
            params.put("oauth_callback", callback)
        }
        params.put("oauth_signature_method", "HMAC-SHA1")
        params.put("oauth_timestamp", time)
        params.put("oauth_consumer_key", consumerKey!!)
        if (accessToken != null) {
            params.put("oauth_token", accessToken!!)
        }
        params.put("oauth_version", "1.0")
        if (isContainValue) {
            for (e in method.param) {
                params.put(e.key, e.value)
            }
        }
        val baseValue = StringBuilder()
        for (e in params.entries) {
            if (baseValue.length > 0) {
                baseValue.append('&')
            }
            baseValue.append(e.key.urlEncode())
            baseValue.append('=')
            baseValue.append(e.value.urlEncode())
        }
        var baseString = "${method.method}&${method.url.urlEncode()}&${baseValue.toString().urlEncode()}"
        val keyText = "${consumerSecret!!.urlEncode()}&" + (if (accessTokenSecret == null) "" else accessTokenSecret!!.urlEncode())
        val signingKey = SecretKeySpec(keyText.toByteArray(), "HmacSHA1")
        val mac = Mac.getInstance(signingKey.algorithm)
        mac.init(signingKey)
        val binary = mac.doFinal(baseString.toByteArray())
        val signature = base64Encode(binary)
        return signature
    }
}
