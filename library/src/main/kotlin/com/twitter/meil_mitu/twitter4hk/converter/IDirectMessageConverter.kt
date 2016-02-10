package com.twitter.meil_mitu.twitter4hk.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import org.json.JSONObject

interface IDirectMessageConverter<TDirectMessage> : IJsonConverter {

    @Throws(Twitter4HKException::class)
    fun toDirectMessage(res: Response): TDirectMessage

    @Throws(Twitter4HKException::class)
    fun toDirectMessage(obj: JSONObject): TDirectMessage

    @Throws(Twitter4HKException::class)
    fun toDirectMessageResponseData(res: Response): ResponseData<TDirectMessage>

    @Throws(Twitter4HKException::class)
    fun toDirectMessageResponseList(res: Response): ResponseList<TDirectMessage>
}