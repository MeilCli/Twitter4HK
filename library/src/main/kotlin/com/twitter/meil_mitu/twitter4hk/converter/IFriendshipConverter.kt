package com.twitter.meil_mitu.twitter4hk.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

interface IFriendshipConverter<TFriendship> : IJsonConverter {

    @Throws(Twitter4HKException::class)
    fun toFriendshipResponseList(res: Response): ResponseList<TFriendship>
}