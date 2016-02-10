package com.twitter.meil_mitu.twitter4hk.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

interface ILanguageConverter<TLanguage> : IJsonConverter {
    @Throws(Twitter4HKException::class)
    fun toLanguageResponseList(res: Response): ResponseList<TLanguage>
}