package com.twitter.meil_mitu.twitter4hk.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

interface ISettingConverter<TSetting> : IJsonConverter {
    @Throws(Twitter4HKException::class)
    fun toSetting(res: Response): TSetting

    @Throws(Twitter4HKException::class)
    fun toSettingResponseData(res: Response): ResponseData<TSetting>
}