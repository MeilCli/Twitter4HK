package com.twitter.meil_mitu.twitter4hk.api.statuses

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.converter.IStatusConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Lookup<TStatus>(
        oauth: AbsOauth,
        protected val json: IStatusConverter<TStatus>,
        id: LongArray) : AbsGet<ResponseList<TStatus>>(oauth) {

    var id: LongArray? by longArrayParam("id")
    var includeEntities: Boolean? by booleanParam("include_entities")
    /**
     * must not use in JsonConverter for User
     */
    var trimUser: Boolean? by booleanParam("trim_user")
    /**
     * must not use in JsonConverter for Status
     */
    var map: Boolean? by booleanParam("map")
    override val url = "https://api.twitter.com/1.1/statuses/lookup.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization: Boolean = true

    init {
        this.id = id
    }

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseList<TStatus> {
        return json.toStatusResponseList(oauth.get(this))
    }
}
