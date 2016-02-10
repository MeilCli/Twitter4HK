package com.twitter.meil_mitu.twitter4hk.api.statuses

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.IStatusConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Show<TStatus>(
        oauth: AbsOauth,
        protected val json: IStatusConverter<TStatus>,
        id: Long) : AbsGet<ResponseData<TStatus>>(oauth) {

    public var id: Long? by longParam("id")
    /**
     * must not use in JsonConverter for User
     */
    public var trimUser: Boolean? by booleanParam("trim_user")
    public var includeMyRetweet: Boolean? by booleanParam("include_my_retweet")
    public var includeEntities: Boolean? by booleanParam("include_entities")
    override val url = "https://api.twitter.com/1.1/statuses/show.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    init {
        this.id = id
    }

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TStatus> {
        return json.toStatusResponseData(oauth.get(this))
    }
}
