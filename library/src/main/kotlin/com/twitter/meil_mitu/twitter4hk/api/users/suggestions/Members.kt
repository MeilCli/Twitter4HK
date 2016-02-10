package com.twitter.meil_mitu.twitter4hk.api.users.suggestions

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.converter.IUserConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Members<TUser>(
        oauth: AbsOauth,
        protected val json: IUserConverter<TUser>,
        private val slug: String) : AbsGet<ResponseList<TUser>>(oauth) {

    override val url = "https://api.twitter.com/1.1/users/suggestions/$slug/members.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseList<TUser> {
        return json.toUserResponseList(oauth.get(this))
    }
}
