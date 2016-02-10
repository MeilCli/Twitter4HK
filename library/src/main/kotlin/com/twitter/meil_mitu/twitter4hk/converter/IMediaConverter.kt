package com.twitter.meil_mitu.twitter4hk.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

interface IMediaConverter<TMedia> : IJsonConverter {
    @Throws(Twitter4HKException::class)
    fun toMedia(res: Response): TMedia
}