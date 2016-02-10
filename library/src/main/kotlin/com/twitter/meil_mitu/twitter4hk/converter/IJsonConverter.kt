package com.twitter.meil_mitu.twitter4hk.converter

import com.squareup.okhttp.ResponseBody
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import org.json.JSONArray
import org.json.JSONObject

interface IJsonConverter {

    @Throws(Twitter4HKException::class)
    fun toJSONObject(res: String): JSONObject

    @Throws(Twitter4HKException::class)
    fun toJSONArray(res: String): JSONArray

    @Throws(Twitter4HKException::class)
    fun toString(body: ResponseBody): String
}