package com.twitter.meil_mitu.twitter4hk.streaming.sample

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.api.ISampleStreamConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.streaming.IStreamListener
import com.twitter.meil_mitu.twitter4hk.streaming.IStreamParam
import com.twitter.meil_mitu.twitter4hk.streaming.MessageType
import com.twitter.meil_mitu.twitter4hk.streaming.Stream
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.toJSONObject
import java.io.InputStream
import java.util.concurrent.Executors

class SampleStream<TStatus>(
        oauth: AbsOauth,
        protected val json: ISampleStreamConverter<TStatus>) :
        AbsGet<Stream>(oauth), IStreamParam {

    override var streamListener: IStreamListener? = null
    public var sampleStreamListener: ISampleStreamListener<TStatus>? = null
    private val executorService = Executors.newSingleThreadExecutor()
    /**
     * should not use this
     */
    public var delimited: Boolean? by booleanParam("delimited")
    public var stallWarnings: Boolean? by booleanParam("stall_warnings")
    override val url = "https://stream.twitter.com/1.1/statuses/sample.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): Stream {
        return Stream(this)
    }

    override val inputStream: InputStream
        @Throws(Twitter4HKException::class)
        get() = oauth.get(this).body().byteStream()

    override fun onLine(line: String) {
        if (sampleStreamListener == null) {
            return
        }
        executorService.submit { handleLine(line) }
    }

    private fun handleLine(line: String) {
        try {
            val obj = toJSONObject(line)
            val type = MessageType.type(obj)
            when (type) {
                MessageType.status -> sampleStreamListener!!.onStatus(json.toStatus(obj))
                MessageType.deleteStatus -> {
                    val delete = JsonUtils.getJSONObject(obj, "delete")
                    val status = JsonUtils.getJSONObject(delete, "status")
                    sampleStreamListener!!.onDeleteStatus(JsonUtils.getLong(status, "user_id"), JsonUtils.getLong(status, "id"))
                }
                else -> sampleStreamListener!!.onUnknown(line)
            }
        } catch (e: Twitter4HKException) {
            e.printStackTrace()
        }

    }
}
