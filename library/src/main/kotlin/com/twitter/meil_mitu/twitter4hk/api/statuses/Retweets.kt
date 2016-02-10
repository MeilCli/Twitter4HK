package com.twitter.meil_mitu.twitter4hk.api.statuses

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.converter.IStatusConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Retweets<TStatus>(
        oauth: AbsOauth,
        protected val json: IStatusConverter<TStatus>,
        private val id: Long) : AbsGet<ResponseList<TStatus>>(oauth) {

    public var count: Int? by intParam("count")
    /**
     * must not use in JsonConverter for User
     */
    public var trimUser: Boolean? by booleanParam("trim_user")
    override val url = "https://api.twitter.com/1.1/statuses/retweets/$id.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseList<TStatus> {
        return json.toStatusResponseList(oauth.get(this))
    }
}
