package com.twitter.meil_mitu.twitter4hk.aclog.api.users

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.aclog.AbsAclogGet
import com.twitter.meil_mitu.twitter4hk.aclog.converter.IAclogUserConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import java.util.*

class DiscoveredBy<TAclogUser>(
        oauth: AbsOauth,
        protected val json: IAclogUserConverter<TAclogUser>) :
        AbsAclogGet<ArrayList<TAclogUser>>(oauth) {

    public var authorization: Boolean = false
    override val isAuthorization = authorization
    public var id: Long? by longParam("id")
    public var screenName: String? by stringParam("screen_name")
    override val url = "$host/api/users/discovered_by.json"
    override val allowOauthType = OauthType.oauthEcho

    @Throws(Twitter4HKException::class)
    override fun call(): ArrayList<TAclogUser> {
        return json.toUserList(oauth.get(this))
    }
}
