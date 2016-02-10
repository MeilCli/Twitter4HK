package com.twitter.meil_mitu.twitter4hk.api.savedsearches

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.ISavedSearchConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Create<TSavedSearch>(
        oauth: AbsOauth,
        protected val json: ISavedSearchConverter<TSavedSearch>,
        query: String) : AbsPost<TSavedSearch>(oauth) {

    public var query: String? by stringParam("query")
    override val url = "https://api.twitter.com/1.1/saved_searches/create.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    init {
        this.query = query
    }

    @Throws(Twitter4HKException::class)
    override fun call(): TSavedSearch {
        return json.toSavedSearch(oauth.post(this))
    }
}
