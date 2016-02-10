package com.twitter.meil_mitu.twitter4hk.streaming.filter

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.api.IFilterStreamConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.streaming.IStreamListener
import com.twitter.meil_mitu.twitter4hk.streaming.IStreamParam
import com.twitter.meil_mitu.twitter4hk.streaming.MessageType
import com.twitter.meil_mitu.twitter4hk.streaming.Stream
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getLong
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.toJSONObject
import java.io.InputStream
import java.util.concurrent.Executors

class FilterStream<TStatus>(
        oauth: AbsOauth,
        protected val json: IFilterStreamConverter<TStatus>) :
        AbsPost<Stream>(oauth), IStreamParam {

    override var streamListener: IStreamListener? = null
    public var filterStreamListener: IFilterStreamListener<TStatus>? = null
    private val executorService = Executors.newSingleThreadExecutor()
    public var follow: LongArray? by longArrayParam("follow")
    public var track: Array<String>? by stringArrayParam("track")
    public var locations: Array<String>? by stringArrayParam("locations")
    /**
     * should not use this
     */
    public var delimited: Boolean? by booleanParam("delimited")
    public var stallWarnings: Boolean? by booleanParam("stall_warnings")
    override val url = "https://stream.twitter.com/1.1/statuses/filter.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    override fun call(): Stream {
        return Stream(this)
    }

    override val inputStream: InputStream
        @Throws(Twitter4HKException::class)
        get() = oauth.post(this).body().byteStream()

    override fun onLine(line: String) {
        if (filterStreamListener == null) {
            return
        }
        executorService.submit { handleLine(line) }
    }

    private fun handleLine(line: String) {
        try {
            val obj = toJSONObject(line)
            val type = MessageType.type(obj)
            when (type) {
                MessageType.status -> filterStreamListener!!.onStatus(json.toStatus(obj))
                MessageType.deleteStatus -> {
                    val delete = getJSONObject(obj,"delete")
                    val status = getJSONObject(delete,"status")
                    filterStreamListener!!.onDeleteStatus(getLong(status,"user_id"),getLong(status,"id"))
                }
                else -> filterStreamListener!!.onUnknown(line)
            }
        } catch (e: Twitter4HKException) {
            e.printStackTrace()
        }

    }

}
