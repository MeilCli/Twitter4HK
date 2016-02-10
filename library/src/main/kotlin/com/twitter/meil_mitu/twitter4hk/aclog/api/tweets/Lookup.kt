package com.twitter.meil_mitu.twitter4hk.aclog.api.tweets

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.aclog.AbsAclogGet
import com.twitter.meil_mitu.twitter4hk.aclog.converter.IAclogStatusConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import java.util.*

class Lookup<TAclogStatus>(
        oauth: AbsOauth,
        protected val json: IAclogStatusConverter<TAclogStatus>,
        ids: LongArray) : AbsAclogGet<ArrayList<TAclogStatus>>(oauth) {

    public var authorization: Boolean = false
    override val isAuthorization = authorization
    public var ids: LongArray? by longArrayParam("ids")
    override val url = "$host/api/tweets/lookup.json"
    override val allowOauthType = OauthType.oauthEcho

    init {
        this.ids = ids
    }

    @Throws(Twitter4HKException::class)
    override fun call(): ArrayList<TAclogStatus> {
        return json.toStatusList(oauth.get(this))
    }
}
