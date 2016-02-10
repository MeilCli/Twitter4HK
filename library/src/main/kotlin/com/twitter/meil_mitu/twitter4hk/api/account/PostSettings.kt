package com.twitter.meil_mitu.twitter4hk.api.account


import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.ISettingConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException


class PostSettings<TSetting>(
        oauth: AbsOauth,
        protected val json: ISettingConverter<TSetting>) : AbsPost<TSetting>(oauth) {

    public var trendLocationWoeid: Int? by intParam("trend_location_woeid")
    public var sleepTimeEnabled: Boolean? by booleanParam("sleep_time_enabled")
    public var startSleepTime: Int? by intParam("start_sleep_time")
    public var endSleepTime: Int? by intParam("end_sleep_time")
    public var timeZone: String? by stringParam("time_zone")
    public var lang: String? by stringParam("lang")
    override val url = "https://api.twitter.com/1.1/account/settings.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): TSetting {
        return json.toSetting(oauth.post(this))
    }
}
