package com.twitter.meil_mitu.twitter4hk.api.users

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.converter.IUserConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Lookup<TUser>(
        oauth: AbsOauth,
        protected val json: IUserConverter<TUser>) : AbsGet<ResponseList<TUser>>(oauth) {

    var screenName: Array<String>? by stringArrayParam("screen_name")
    var userId: LongArray? by longArrayParam("user_id")
    var includeEntities: Boolean? by booleanParam("include_entities")
    override val url = "https://api.twitter.com/1.1/users/lookup.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseList<TUser> {
        return json.toUserResponseList(oauth.get(this))
    }
}
