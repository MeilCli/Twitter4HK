package com.twitter.meil_mitu.twitter4hk.api.statuses

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.converter.IStatusConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class RetweetsOfMe<TStatus>(
        oauth: AbsOauth,
        protected val json: IStatusConverter<TStatus>) :
        AbsGet<ResponseList<TStatus>>(oauth) {

    var sinceId: Long? by longParam("since_id")
    var maxId: Long? by longParam("max_id")
    /**
     * must not use in JsonConverter for User
     */
    var trimUser: Boolean? by booleanParam("trim_user")
    var includeEntities: Boolean? by booleanParam("include_entities")
    var includeUserEntities: Boolean? by booleanParam("include_user_entities")
    override val url = "https://api.twitter.com/1.1/statuses/retweets_of_me.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseList<TStatus> {
        return json.toStatusResponseList(oauth.get(this))
    }
}
