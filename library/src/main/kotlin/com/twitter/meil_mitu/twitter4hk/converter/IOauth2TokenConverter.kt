package com.twitter.meil_mitu.twitter4hk.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

interface IOauth2TokenConverter<TOauth2Token> : IJsonConverter {
    @Throws(Twitter4HKException::class)
    fun toOauth2Token(res: Response): TOauth2Token

    @Throws(Twitter4HKException::class)
    fun toOauth2Token(res: Response, tokenType: String): TOauth2Token
}