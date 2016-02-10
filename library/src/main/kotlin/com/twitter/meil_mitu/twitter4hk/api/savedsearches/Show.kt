package com.twitter.meil_mitu.twitter4hk.api.savedsearches

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.ISavedSearchConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Show<TSavedSearch>(
        oauth: AbsOauth,
        protected val json: ISavedSearchConverter<TSavedSearch>,
        private val id: Long) : AbsGet<ResponseData<TSavedSearch>>(oauth) {

    override val url = "https://api.twitter.com/1.1/saved_searches/show/$id.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TSavedSearch> {
        return json.toSavedSearchResponseData(oauth.get(this))
    }
}
