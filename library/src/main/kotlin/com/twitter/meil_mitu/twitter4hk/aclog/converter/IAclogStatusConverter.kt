package com.twitter.meil_mitu.twitter4hk.aclog.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.converter.IJsonConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import java.util.*

interface IAclogStatusConverter<TAclogStatus> : IJsonConverter {
    @Throws(Twitter4HKException::class)
    fun toStatus(res: Response): TAclogStatus

    @Throws(Twitter4HKException::class)
    fun toStatusList(res: Response): ArrayList<TAclogStatus>
}