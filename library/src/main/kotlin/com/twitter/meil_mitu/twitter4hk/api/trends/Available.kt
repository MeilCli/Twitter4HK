package com.twitter.meil_mitu.twitter4hk.api.trends

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.converter.ITrendPlaceConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Available<TTrendPlace>(
        oauth: AbsOauth,
        protected val json: ITrendPlaceConverter<TTrendPlace>) :
        AbsGet<ResponseList<TTrendPlace>>(oauth) {

    override val url = "https://api.twitter.com/1.1/trends/available.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseList<TTrendPlace> {
        return json.toTrendPlaceResponseList(oauth.get(this))
    }
}
