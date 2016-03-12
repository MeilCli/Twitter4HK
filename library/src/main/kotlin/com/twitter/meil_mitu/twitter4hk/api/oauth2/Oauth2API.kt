package com.twitter.meil_mitu.twitter4hk.api.oauth2

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IOauth2Converter

class Oauth2API(oauth: AbsOauth, protected val json: IOauth2Converter) : AbsAPI(oauth) {

    fun token() = Token(oauth, json)

    fun invalidateToken() = InvalidateToken(oauth, json)

}
