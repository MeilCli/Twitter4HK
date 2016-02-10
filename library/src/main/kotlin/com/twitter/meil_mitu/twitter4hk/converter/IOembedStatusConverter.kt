package com.twitter.meil_mitu.twitter4hk.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

interface IOembedStatusConverter<TOembedStatus> : IJsonConverter {

    @Throws(Twitter4HKException::class)
    fun toOembedStatusResponseData(res: Response): ResponseData<TOembedStatus>
}