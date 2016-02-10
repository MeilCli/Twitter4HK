package com.twitter.meil_mitu.twitter4hk.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

interface ITosResultConverter<TTosResult> : IJsonConverter {
    @Throws(Twitter4HKException::class)
    fun toTosResultResponseData(res: Response): ResponseData<TTosResult>
}