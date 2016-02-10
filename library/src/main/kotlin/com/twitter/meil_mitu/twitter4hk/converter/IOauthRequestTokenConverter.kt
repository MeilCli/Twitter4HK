package com.twitter.meil_mitu.twitter4hk.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

interface IOauthRequestTokenConverter<TOauthRequestToken> : IJsonConverter {

    @Throws(Twitter4HKException::class)
    fun toOauthRequestToken(res: Response): TOauthRequestToken
}