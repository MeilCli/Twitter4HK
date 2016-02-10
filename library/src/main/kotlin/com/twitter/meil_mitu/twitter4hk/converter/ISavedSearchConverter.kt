package com.twitter.meil_mitu.twitter4hk.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

interface ISavedSearchConverter<TSavedSearch> : IJsonConverter {

    @Throws(Twitter4HKException::class)
    fun toSavedSearch(res: Response): TSavedSearch

    @Throws(Twitter4HKException::class)
    fun toSavedSearchResponseData(res: Response): ResponseData<TSavedSearch>

    @Throws(Twitter4HKException::class)
    fun toSavedSearchResponseList(res: Response): ResponseList<TSavedSearch>
}