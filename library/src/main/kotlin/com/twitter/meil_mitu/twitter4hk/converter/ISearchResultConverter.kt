package com.twitter.meil_mitu.twitter4hk.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

interface ISearchResultConverter<TSearchResult> : IJsonConverter {

    @Throws(Twitter4HKException::class)
    fun toSearchResultResponseData(res: Response): ResponseData<TSearchResult>

}