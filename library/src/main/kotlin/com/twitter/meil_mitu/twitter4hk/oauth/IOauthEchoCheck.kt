package com.twitter.meil_mitu.twitter4hk.oauth

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

interface IOauthEchoCheck {

    @Throws(Twitter4HKException::class)
    fun checkError(res: Response)
}
