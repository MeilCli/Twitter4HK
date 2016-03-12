package com.twitter.meil_mitu.twitter4hk.api.trends

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.ITrendResultConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Place<TTrendResult>(
        oauth: AbsOauth,
        protected val json: ITrendResultConverter<TTrendResult>,
        id: Int) : AbsGet<ResponseData<TTrendResult>>(oauth) {

    var id: Int? by intParam("id")
    var exclude: String? by stringParam("exclude")
    override val url = "https://api.twitter.com/1.1/trends/place.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    init {
        this.id = id
    }

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TTrendResult> {
        return json.toTrendResultResponseData(oauth.get(this))
    }
}
