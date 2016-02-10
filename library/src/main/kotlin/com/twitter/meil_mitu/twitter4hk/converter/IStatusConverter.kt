package com.twitter.meil_mitu.twitter4hk.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import org.json.JSONObject

interface IStatusConverter<TStatus> : IJsonConverter {

    @Throws(Twitter4HKException::class)
    fun toStatus(res: Response): TStatus

    @Throws(Twitter4HKException::class)
    fun toStatus(obj: JSONObject): TStatus

    @Throws(Twitter4HKException::class)
    fun toStatusResponseData(res: Response): ResponseData<TStatus>

    @Throws(Twitter4HKException::class)
    fun toStatusResponseList(res: Response): ResponseList<TStatus>

}