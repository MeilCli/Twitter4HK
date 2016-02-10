package com.twitter.meil_mitu.twitter4hk.aclog.api.tweets

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.aclog.AbsAclogGet
import com.twitter.meil_mitu.twitter4hk.aclog.converter.IAclogStatusConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import java.util.*

class UserFavorites<TAclogStatus>(
        oauth: AbsOauth,
        protected val json: IAclogStatusConverter<TAclogStatus>) :
        AbsAclogGet<ArrayList<TAclogStatus>>(oauth) {

    public var authorization: Boolean = false
    override val isAuthorization = authorization
    public var userId: Long? by longParam("user_id")
    public var screenName: String? by stringParam("screen_name")
    public var count: Int? by intParam("count")
    public var page: Int? by intParam("page")
    public var reactions: Int? by intParam("reactions")
    override val url = "$host/api/tweets/user_favorites.json"
    override val allowOauthType = OauthType.oauthEcho

    @Throws(Twitter4HKException::class)
    override fun call(): ArrayList<TAclogStatus> {
        return json.toStatusList(oauth.get(this))
    }
}
