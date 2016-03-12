package com.twitter.meil_mitu.twitter4hk.api.application

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IApplicationConverter

class ApplicationAPI<TRateLimitResult>(
        oauth: AbsOauth,
        protected val json: IApplicationConverter<TRateLimitResult>) : AbsAPI(oauth) {

    fun rateLimitStatus() = RateLimitStatus(oauth, json)

}
