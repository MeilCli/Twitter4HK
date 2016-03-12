package com.twitter.meil_mitu.twitter4hk.api.users.suggestions

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.ISuggestionUserConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Get<TSuggestionUser>(
        oauth: AbsOauth,
        protected val json: ISuggestionUserConverter<TSuggestionUser>,
        public var slug: String) : AbsGet<ResponseData<TSuggestionUser>>(oauth) {

    var lang: String? by stringParam("lang")
    override val url = "https://api.twitter.com/1.1/users/suggestions/$slug.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TSuggestionUser> {
        return json.toSuggestionUserResponseData(oauth.get(this))
    }
}
