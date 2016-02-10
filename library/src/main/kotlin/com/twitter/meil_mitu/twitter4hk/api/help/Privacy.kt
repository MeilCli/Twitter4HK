package com.twitter.meil_mitu.twitter4hk.api.help

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.IPrivacyResultConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Privacy<TPrivacyResult>(
        oauth: AbsOauth,
        protected val json: IPrivacyResultConverter<TPrivacyResult>) :
        AbsGet<ResponseData<TPrivacyResult>>(oauth) {

    override val url = "https://api.twitter.com/1.1/help/privacy.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TPrivacyResult> {
        return json.toPrivacyResultResponseData(oauth.get(this))
    }
}
