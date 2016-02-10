package com.twitter.meil_mitu.twitter4hk.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

interface IOauthTokenConverter<TOauthToken> : IJsonConverter {
    @Throws(Twitter4HKException::class)
    fun toOauthToken(res: Response): TOauthToken
}