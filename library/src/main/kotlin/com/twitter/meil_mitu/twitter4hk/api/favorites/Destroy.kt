package com.twitter.meil_mitu.twitter4hk.api.favorites

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IStatusConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Destroy<TStatus>(
        oauth: AbsOauth,
        protected val json: IStatusConverter<TStatus>, id: Long) : AbsPost<TStatus>(oauth) {

    public var id: Long? by longParam("id")
    public var includeEntities: Boolean? by booleanParam("include_entities")
    override val url = "https://api.twitter.com/1.1/favorites/destroy.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    init {
        this.id = id
    }

    @Throws(Twitter4HKException::class)
    override fun call(): TStatus {
        return json.toStatus(oauth.post(this))
    }
}
