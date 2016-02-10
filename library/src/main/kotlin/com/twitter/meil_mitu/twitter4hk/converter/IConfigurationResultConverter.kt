package com.twitter.meil_mitu.twitter4hk.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

interface IConfigurationResultConverter<TConfigurationResult> : IJsonConverter {
    @Throws(Twitter4HKException::class)
    fun toConfigurationResultResponseData(res: Response): ResponseData<TConfigurationResult>
}