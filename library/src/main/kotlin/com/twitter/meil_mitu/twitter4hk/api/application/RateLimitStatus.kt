package com.twitter.meil_mitu.twitter4hk.api.application

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.IRateLimitResultConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class RateLimitStatus<TRateLimitResult>(
        oauth: AbsOauth,
        protected val json: IRateLimitResultConverter<TRateLimitResult>) :
        AbsGet<ResponseData<TRateLimitResult>>(oauth) {

    public var resources: Array<String>? by stringArrayParam("resources")
    override val url = "https://api.twitter.com/1.1/application/rate_limit_status.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TRateLimitResult> {
        return json.toRateLimitResultResponseData(oauth.get(this))
    }
}
