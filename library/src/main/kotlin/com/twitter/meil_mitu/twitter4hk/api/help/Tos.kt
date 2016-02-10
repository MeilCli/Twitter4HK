package com.twitter.meil_mitu.twitter4hk.api.help

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.ITosResultConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Tos<TTosResult>(
        oauth: AbsOauth,
        protected val json: ITosResultConverter<TTosResult>) :
        AbsGet<ResponseData<TTosResult>>(oauth) {

    override val url = "https://api.twitter.com/1.1/help/tos.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TTosResult> {
        return json.toTosResultResponseData(oauth.get(this))
    }
}
