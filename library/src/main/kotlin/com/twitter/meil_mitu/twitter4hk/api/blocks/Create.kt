package com.twitter.meil_mitu.twitter4hk.api.blocks

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IUserConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Create<TUser>(
        oauth: AbsOauth,
        protected val json: IUserConverter<TUser>) : AbsPost<TUser>(oauth) {

    var screenName: String? by stringParam("screen_name")
    var userId: Long? by longParam("user_id")
    var includeEntities: Boolean? by booleanParam("include_entities")
    var skipStatus: Boolean? by booleanParam("skip_status")
    override val url = "https://api.twitter.com/1.1/blocks/create.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): TUser {
        return json.toUser(oauth.post(this))
    }
}
