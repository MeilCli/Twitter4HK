package com.twitter.meil_mitu.twitter4hk.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

interface ICursorIDsConverter<TCursorIDs> : IJsonConverter {

    @Throws(Twitter4HKException::class)
    fun toCursorIDsResponseData(res: Response): ResponseData<TCursorIDs>
}