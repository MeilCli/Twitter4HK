package com.twitter.meil_mitu.twitter4hk.api.users

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.IBannerConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class ProfileBanner<TBanner>(
        oauth: AbsOauth,
        protected val json: IBannerConverter<TBanner>) :
        AbsGet<ResponseData<TBanner>>(oauth) {

    var userId: Long? by longParam("user_id")
    var screenName: String? by stringParam("screen_name")
    override val url = "https://api.twitter.com/1.1/users/profile_banner.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TBanner> {
        return json.toBannerResponseData(oauth.get(this))
    }
}
