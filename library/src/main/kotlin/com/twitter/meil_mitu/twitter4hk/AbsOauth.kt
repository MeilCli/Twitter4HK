package com.twitter.meil_mitu.twitter4hk

import com.squareup.okhttp.*
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getInt
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import com.twitter.meil_mitu.twitter4hk.util.readByte
import com.twitter.meil_mitu.twitter4hk.util.urlEncode
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

abstract class AbsOauth protected constructor(
        isRequireConsumer: Boolean,
        config: Twitter4HKConfig?,
        protected var consumerKey: String?,
        protected var consumerSecret: String?) {
    protected var config: Twitter4HKConfig
    protected var http: OkHttpClient? = null

    var lastProtocol: String? = null
        private set

    init {
        var _config = config
        if (_config == null) {
            _config = Twitter4HKConfig()
        }
        this.config = _config
        if (isRequireConsumer == true && (consumerKey == null || consumerSecret == null)) {
            throw NullPointerException("ConsumerKey or ConsumerSecret is null")
        }
    }

    @Throws(Twitter4HKException::class)
    abstract fun <T> get(param: AbsGet<T>): Response

    @Throws(Twitter4HKException::class)
    abstract fun <T> post(param: AbsPost<T>): Response

    @Throws(IOException::class)
    protected fun call(request: Request): Response {
        okhttpSetting(config)
        val res = http!!.newCall(request).execute()
        lastProtocol = res.header("OkHttp-Selected-Protocol")
        return res
    }

    protected open fun okhttpSetting(config: Twitter4HKConfig) {
        if (http == null) {
            http = OkHttpClient()
            val list = ArrayList<Protocol>()
            list.add(Protocol.HTTP_1_1)
            if (config.isUseHttp2) {
                list.add(Protocol.HTTP_2)
            }
            if (config.isUseSpdy) {
                list.add(Protocol.SPDY_3)
            }
            http!!.setProtocols(list)
            http!!.setConnectionPool(ConnectionPool(5, 400))
        }
        http!!.setConnectTimeout(config.connectTimeoutSecond, TimeUnit.SECONDS)
        http!!.setReadTimeout(config.readTimeoutSecond, TimeUnit.SECONDS)
    }

    protected fun<T> toUrl(param: AbsGet<T>): String {
        val builder = StringBuilder()
        builder.append(param.url)
        builder.append('?')
        for (e in param.param) {
            builder.append(e.key)
            builder.append('=')
            builder.append(e.value.urlEncode())
            builder.append('&')
        }
        builder.deleteCharAt(builder.length - 1)
        return builder.toString()
    }

    @Throws(Twitter4HKException::class)
    protected fun <T> toBody(param: AbsPost<T>): RequestBody {
        val body: RequestBody
        if (param.fileSize == 0) {
            val builder = StringBuilder()
            for (e in param.param) {
                builder.append(e.key.urlEncode())
                builder.append('=')
                builder.append(e.value.urlEncode())
                builder.append('&')
            }
            if (builder.length > 0) {
                builder.deleteCharAt(builder.length - 1)
            }
            body = RequestBody.create(mediaText, builder.toString())
        } else {
            val multipartBuilder = MultipartBuilder()
            multipartBuilder.type(MultipartBuilder.FORM)
            for (e in param.param) {
                multipartBuilder.addFormDataPart(e.key, e.value.urlEncode())
            }
            val files = param.fileParam
            for (e in files) {
                val f = e.value
                val type = when {
                    f.name.endsWith(".png") -> mediaPng
                    f.name.endsWith(".gif") -> mediaGif
                    f.name.endsWith(".mp4") -> mediaMp4
                    else -> mediaJpeg
                }
                if (param.separateFileMap.containsKey(e.key)) {
                    val startByte = param.separateFileMap[e.key]!!.first
                    val endByte = param.separateFileMap[e.key]!!.second
                    val size = (endByte - startByte).toInt()
                    multipartBuilder.addFormDataPart(e.key.urlEncode(), f.name.urlEncode(), RequestBody.create(type, readByte(f, size, startByte)))
                } else {
                    multipartBuilder.addFormDataPart(e.key.urlEncode(), f.name.urlEncode(), RequestBody.create(type, f))
                }
            }
            body = multipartBuilder.build()
        }
        return body
    }

    // http://qiita.com/mpyw/items/b59d3ce03f08be126000
    @Throws(Twitter4HKException::class)
    protected fun checkError(res: Response) {
        if (res.isSuccessful == false) {
            var body: String? = null
            try {
                body = res.body().string()
                val obj = JSONObject(body)
                if (obj.isNull("errors") == false) {
                    val o = JsonUtils.get(obj, "errors")
                    if (o is String) {
                        throw Twitter4HKException(o, res.code(), o)
                    } else if (o is JSONArray) {
                        if (o.length() == 0) {
                            throw Twitter4HKException("errors array size is 0")
                        }
                        var message = getString(getJSONObject(o, 0), "message")
                        message += "\n\n"
                        message += res.request().headers().toString()
                        message += "\n\n"
                        message += res.request().toString()
                        val code = getInt(getJSONObject(o, 0), "code")
                        throw Twitter4HKException(message, res.code(), code)
                    } else {
                        throw Twitter4HKException("some exception")
                    }
                } else if (obj.isNull("error") == false) {
                    val message = getString(obj, "error")
                    throw Twitter4HKException(message, res.code(), message)
                } else {
                    throw Twitter4HKException(body)
                }
            } catch (e: IOException) {
                e.printStackTrace()
                throw Twitter4HKException(e.message)
            } catch(e: JSONException) {
                e.printStackTrace()
                throw Twitter4HKException(body ?: e.message)
            }
        }
    }

    companion object {

        internal val mediaText = MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8")
        internal val mediaPng = MediaType.parse("image/png")
        internal val mediaGif = MediaType.parse("image/gif")
        internal val mediaJpeg = MediaType.parse("image/jpeg")
        internal val mediaMp4 = MediaType.parse("video/mp4")
    }

}
