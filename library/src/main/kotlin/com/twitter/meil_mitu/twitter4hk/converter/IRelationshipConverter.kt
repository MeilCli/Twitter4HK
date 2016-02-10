package com.twitter.meil_mitu.twitter4hk.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

interface IRelationshipConverter<TRelationship> : IJsonConverter {

    @Throws(Twitter4HKException::class)
    fun toRelationship(res: Response): TRelationship

    @Throws(Twitter4HKException::class)
    fun toRelationshipResponseData(res: Response): ResponseData<TRelationship>
}