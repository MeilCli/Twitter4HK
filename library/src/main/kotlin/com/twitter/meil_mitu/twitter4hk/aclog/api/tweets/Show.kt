package com.twitter.meil_mitu.twitter4hk.aclog.api.tweets

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.aclog.AbsAclogGet
import com.twitter.meil_mitu.twitter4hk.aclog.converter.IAclogStatusConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Show<TAclogStatus>(
        oauth: AbsOauth,
        protected val json: IAclogStatusConverter<TAclogStatus>,
        id: Long) : AbsAclogGet<TAclogStatus>(oauth) {

    public var authorization: Boolean = false
    override val isAuthorization = authorization
    public var id: Long?by longParam("id")
    override val url = "$host/api/tweets/show.json"
    override val allowOauthType = OauthType.oauthEcho

    init {
        this.id = id
    }

    @Throws(Twitter4HKException::class)
    override fun call(): TAclogStatus {
        return json.toStatus(oauth.get(this))
    }
}
