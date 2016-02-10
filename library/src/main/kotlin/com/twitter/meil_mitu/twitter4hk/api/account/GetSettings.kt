package com.twitter.meil_mitu.twitter4hk.api.account

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.ISettingConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class GetSettings<TSetting>(
        oauth: AbsOauth,
        protected val json: ISettingConverter<TSetting>) :
        AbsGet<ResponseData<TSetting>>(oauth) {

    override val url = "https://api.twitter.com/1.1/account/settings.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TSetting> {
        return json.toSettingResponseData(oauth.get(this))
    }
}
