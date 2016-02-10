package com.twitter.meil_mitu.twitter4hk.api.account

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IJsonConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class RemoveProfileBanner(
        oauth: AbsOauth,
        protected val json: IJsonConverter) : AbsPost<Unit>(oauth) {

    override val url = "https://api.twitter.com/1.1/account/remove_profile_banner.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): Unit {
        // okhttp's connection is called close() in body().string()
        json.toString(oauth.post(this).body())
    }
}
