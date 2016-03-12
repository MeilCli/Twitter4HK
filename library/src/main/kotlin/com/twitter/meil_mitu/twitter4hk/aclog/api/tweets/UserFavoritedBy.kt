package com.twitter.meil_mitu.twitter4hk.aclog.api.tweets

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.aclog.AbsAclogGet
import com.twitter.meil_mitu.twitter4hk.aclog.converter.IAclogStatusConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import java.util.*

class UserFavoritedBy<TAclogStatus>(
        oauth: AbsOauth,
        protected val json: IAclogStatusConverter<TAclogStatus>) :
        AbsAclogGet<ArrayList<TAclogStatus>>(oauth) {

    var authorization: Boolean = false
    override val isAuthorization = authorization
    var userId: Long? by longParam("user_id")
    var screenName: String? by stringParam("screen_name")
    var sourceUserId: Long? by longParam("source_user_id")
    var sourceScreenName: String? by stringParam("source_screen_name")
    var count: Int? by intParam("count")
    var page: Int? by intParam("page")
    var sinceId: Long? by longParam("since_id")
    var maxId: Long? by longParam("max_id")
    var reactions: Int? by intParam("reactions")
    override val url = "$host/api/tweets/user_favorited_by.json"
    override val allowOauthType = OauthType.oauthEcho

    @Throws(Twitter4HKException::class)
    override fun call(): ArrayList<TAclogStatus> {
        return json.toStatusList(oauth.get(this))
    }
}
