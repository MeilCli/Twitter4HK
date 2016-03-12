package com.twitter.meil_mitu.twitter4hk.api.favorites

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.converter.IStatusConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class List<TStatus>(
        oauth: AbsOauth,
        protected val json: IStatusConverter<TStatus>) :
        AbsGet<ResponseList<TStatus>>(oauth) {

    var userId: Long? by longParam("user_id")
    var screenName: String? by stringParam("screen_name")
    var count: Int? by intParam("count")
    var sinceId: Long? by longParam("since_id")
    var maxId: Long? by longParam("max_id")
    var includeEntities: Boolean? by booleanParam("include_entities")
    override val url = "https://api.twitter.com/1.1/favorites/list.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseList<TStatus> {
        return json.toStatusResponseList(oauth.get(this))
    }
}
