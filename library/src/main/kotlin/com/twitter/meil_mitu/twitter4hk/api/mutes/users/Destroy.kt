package com.twitter.meil_mitu.twitter4hk.api.mutes.users

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IUserConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Destroy<TUser>(oauth: AbsOauth, protected val json: IUserConverter<TUser>) : AbsPost<TUser>(oauth) {

    public var screenName: String? by stringParam("screen_name")
    public var userId: Long? by longParam("user_id")
    override val url = "https://api.twitter.com/1.1/mutes/users/destroy.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): TUser {
        return json.toUser(oauth.post(this))
    }
}
