package com.twitter.meil_mitu.twitter4hk.aclog.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.converter.IJsonConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

interface IAclogStatsConverter<TAclogStats> : IJsonConverter {

    @Throws(Twitter4HKException::class)
    fun toStats(res: Response): TAclogStats
}