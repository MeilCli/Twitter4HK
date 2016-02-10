package com.twitter.meil_mitu.twitter4hk.api.help

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.IConfigurationResultConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Configuration<TConfigurationResult>(
        oauth: AbsOauth,
        protected val json: IConfigurationResultConverter<TConfigurationResult>) :
        AbsGet<ResponseData<TConfigurationResult>>(oauth) {

    override val url = "https://api.twitter.com/1.1/help/configuration.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TConfigurationResult> {
        return json.toConfigurationResultResponseData(oauth.get(this))
    }
}
