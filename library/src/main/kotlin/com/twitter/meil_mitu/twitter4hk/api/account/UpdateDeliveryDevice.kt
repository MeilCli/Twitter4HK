package com.twitter.meil_mitu.twitter4hk.api.account

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IJsonConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class UpdateDeliveryDevice(
        oauth: AbsOauth,
        protected val json: IJsonConverter,
        device: String) : AbsPost<Unit>(oauth) {

    var device: String? by stringParam("device")
    var includeEntities: Boolean? by booleanParam("include_entities")
    override val url = "https://api.twitter.com/1.1/account/update_delivery_device.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    init {
        this.device = device
    }

    @Throws(Twitter4HKException::class)
    override fun call(): Unit {
        // okhttp's connection is called close() in body().string()
        json.toString(oauth.post(this).body())
    }
}
