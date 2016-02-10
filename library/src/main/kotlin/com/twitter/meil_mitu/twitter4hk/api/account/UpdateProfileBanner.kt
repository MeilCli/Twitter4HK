package com.twitter.meil_mitu.twitter4hk.api.account

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IJsonConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

import java.io.File

class UpdateProfileBanner(
        oauth: AbsOauth,
        protected val json: IJsonConverter,
        banner: File) : AbsPost<Unit>(oauth) {

    public var banner: File? by fileParam("banner")
    public var width: Int? by intParam("width")
    public var height: Int? by intParam("height")
    public var offsetLeft: Int? by intParam("offset_left")
    public var offsetTop: Int? by intParam("offset_top")
    override val url = "https://api.twitter.com/1.1/account/update_profile_banner.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    init {
        this.banner = banner
    }

    @Throws(Twitter4HKException::class)
    override fun call(): Unit {
        // okhttp's connection is called close() in body().string()
        json.toString(oauth.post(this).body())
    }
}
