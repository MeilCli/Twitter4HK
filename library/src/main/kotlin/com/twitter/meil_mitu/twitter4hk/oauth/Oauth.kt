package com.twitter.meil_mitu.twitter4hk.oauth


import com.squareup.okhttp.Request
import com.squareup.okhttp.RequestBody
import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.*
import com.twitter.meil_mitu.twitter4hk.exception.IncorrectException
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.Utils
import java.io.IOException
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
            try {
                builder.addHeader("Authorization", "OAuth ${createAuthorization(param, false)}")
            } catch (e: Exception) {
                e.printStackTrace()
                throw Twitter4HKException(e.message)
            }
        }
        builder.get()
        try {
            return call(builder.build()).apply {
                checkError(this)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
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
            try {
                builder.addHeader("Authorization",
                        "OAuth ${createAuthorization(param, param.fileSize > 0)}")
            } catch (e: Exception) {
                e.printStackTrace()
                throw Twitter4HKException(e.message)
            }
        }
        if ((param.allowOauthType and OauthType.oauth1RequestToken) == 0) {
            builder.post(toBody(param))
        } else {
            builder.post(RequestBody.create(AbsOauth.mediaText, ""))
        }
        try {
            return call(builder.build()).apply {
                checkError(this)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
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
            baseValue.append(Utils.urlEncode(e.key))
            baseValue.append('=')
            baseValue.append('"')
            baseValue.append(Utils.urlEncode(e.value))
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
            baseValue.append(Utils.urlEncode(e.key))
            baseValue.append('=')
            baseValue.append(Utils.urlEncode(e.value))
        }
        var baseString = "${method.method}&${Utils.urlEncode(method.url)}&" +
                "${Utils.urlEncode(baseValue.toString())}"
        val keyText = "${Utils.urlEncode(consumerSecret!!)}&" +
                (if (accessTokenSecret == null) "" else Utils.urlEncode(accessTokenSecret!!))
        val signingKey = SecretKeySpec(keyText.toByteArray(), "HmacSHA1")
        val mac = Mac.getInstance(signingKey.algorithm)
        mac.init(signingKey)
        val binary = mac.doFinal(baseString.toByteArray())
        val signature = Utils.base64Encode(binary)
        return signature
    }
}
