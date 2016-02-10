package com.twitter.meil_mitu.twitter4hk.api.users.suggestions

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.converter.ISuggestionConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class List<TSuggestion>(
        oauth: AbsOauth,
        protected val json: ISuggestionConverter<TSuggestion>) :
        AbsGet<ResponseList<TSuggestion>>(oauth) {

    public var lang: String? by stringParam("lang")
    override val url = "https://api.twitter.com/1.1/users/suggestions.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseList<TSuggestion> {
        return json.toSuggestionResponseList(oauth.get(this))
    }
}
