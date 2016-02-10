package com.twitter.meil_mitu.twitter4hk.api.savedsearches

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.converter.ISavedSearchConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class List<TSavedSearch>(
        oauth: AbsOauth,
        protected val json: ISavedSearchConverter<TSavedSearch>) :
        AbsGet<ResponseList<TSavedSearch>>(oauth) {

    override val url = "https://api.twitter.com/1.1/saved_searches/list.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseList<TSavedSearch> {
        return json.toSavedSearchResponseList(oauth.get(this))
    }
}
