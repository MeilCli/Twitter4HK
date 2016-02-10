package com.twitter.meil_mitu.twitter4hk.aclog.converter.api

import com.squareup.okhttp.Response
import com.squareup.okhttp.ResponseBody
import com.twitter.meil_mitu.twitter4hk.Twitter4HKConfig
import com.twitter.meil_mitu.twitter4hk.aclog.data.AclogStats
import com.twitter.meil_mitu.twitter4hk.aclog.data.AclogStatus
import com.twitter.meil_mitu.twitter4hk.aclog.data.AclogUser
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*

class AclogConverter : IAclogConverter<AclogStats, AclogStatus, AclogUser> {

    @Throws(Twitter4HKException::class)
    override fun toJSONObject(res: String): JSONObject {
        try {
            return JSONObject(res)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    override fun toJSONArray(res: String): JSONArray {
        try {
            return JSONArray(res)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    override fun toString(body: ResponseBody): String {
        try {
            return body.string()
        } catch (e: IOException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    override fun toStatus(res: Response): AclogStatus {
        return AclogStatus(toJSONObject(toString(res.body())))
    }

    @Throws(Twitter4HKException::class)
    override fun toStatusList(res: Response): ArrayList<AclogStatus> {
        val ar = toJSONArray(toString(res.body()))
        val size = ar.length()
        val list = ArrayList<AclogStatus>()
        for (i in 0..size - 1) {
            try {
                list.add(AclogStatus(getJSONObject(ar, i)))
            } catch (e: Twitter4HKException) {
                e.printStackTrace()
                if (Twitter4HKConfig.isDebug) {
                    throw e
                }
            }

        }
        return list
    }

    @Throws(Twitter4HKException::class)
    override fun toStats(res: Response): AclogStats {
        return AclogStats(toJSONObject(toString(res.body())))
    }

    @Throws(Twitter4HKException::class)
    override fun toUserList(res: Response): ArrayList<AclogUser> {
        val ar = toJSONArray(toString(res.body()))
        val size = ar.length()
        val list = ArrayList<AclogUser>()
        for (i in 0..size - 1) {
            try {
                list.add(AclogUser(getJSONObject(ar, i)))
            } catch (e: Twitter4HKException) {
                e.printStackTrace()
                if (Twitter4HKConfig.isDebug) {
                    throw e
                }
            }

        }
        return list
    }

    companion object {

        private var _defaultConverter: AclogConverter? = null

        fun getDefaultConverter(): AclogConverter {
            if (_defaultConverter == null) {
                _defaultConverter = AclogConverter()
            }
            return _defaultConverter!!
        }
    }
}
