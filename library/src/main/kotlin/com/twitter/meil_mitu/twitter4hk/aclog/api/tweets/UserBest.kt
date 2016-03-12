package com.twitter.meil_mitu.twitter4hk.aclog.api.tweets

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.aclog.AbsAclogGet
import com.twitter.meil_mitu.twitter4hk.aclog.converter.IAclogStatusConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import java.util.*

class UserBest<TAclogStatus>(
        oauth: AbsOauth,
        protected val json: IAclogStatusConverter<TAclogStatus>) :
        AbsAclogGet<ArrayList<TAclogStatus>>(oauth) {

    var authorization: Boolean = false
    override val isAuthorization = authorization
    var userId: Long? by longParam("user_id")
    var screenName: String? by stringParam("screen_name")
    var count: Int? by intParam("count")
    var page: Int? by intParam("page")
    var recent: String? by stringParam("recent")
    override val url = "$host/api/tweets/user_best.json"
    override val allowOauthType = OauthType.oauthEcho

    @Throws(Twitter4HKException::class)
    override fun call(): ArrayList<TAclogStatus> {
        return json.toStatusList(oauth.get(this))
    }
}
