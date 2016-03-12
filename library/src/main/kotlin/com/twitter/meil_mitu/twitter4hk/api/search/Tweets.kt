package com.twitter.meil_mitu.twitter4hk.api.search

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.ISearchResultConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Tweets<TSearchResult>(
        oauth: AbsOauth,
        protected val json: ISearchResultConverter<TSearchResult>,
        q: String) : AbsGet<ResponseData<TSearchResult>>(oauth) {

    var q: String? by stringParam("q")
    var geocode: String? by stringParam("geocode")
    var lang: String? by stringParam("lang")
    var locale: String? by stringParam("locale")
    var resultType: String? by stringParam("result_type")
    var count: Int? by intParam("count")
    var until: String? by stringParam("until")
    var sinceId: Long? by longParam("since_id")
    var maxId: Long? by longParam("max_id")
    var includeEntities: Boolean? by booleanParam("include_entities")
    var callback: String? by stringParam("callback")
    override val url = "https://api.twitter.com/1.1/search/tweets.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    init {
        this.q = q
    }

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TSearchResult> {
        return json.toSearchResultResponseData(oauth.get(this))
    }
}
