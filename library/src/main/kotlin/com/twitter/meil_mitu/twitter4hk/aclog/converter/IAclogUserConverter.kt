package com.twitter.meil_mitu.twitter4hk.aclog.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.converter.IJsonConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import java.util.*

interface IAclogUserConverter<TAclogUser> : IJsonConverter {

    @Throws(Twitter4HKException::class)
    fun toUserList(res: Response): ArrayList<TAclogUser>
}