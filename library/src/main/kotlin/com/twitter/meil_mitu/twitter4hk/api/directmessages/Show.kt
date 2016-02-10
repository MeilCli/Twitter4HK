package com.twitter.meil_mitu.twitter4hk.api.directmessages

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.IDirectMessageConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Show<TDirectMessage>(
        oauth: AbsOauth,
        protected val json: IDirectMessageConverter<TDirectMessage>,
        id: Long) : AbsGet<ResponseData<TDirectMessage>>(oauth) {

    public var id: Long? by longParam("id")
    override val url = "https://api.twitter.com/1.1/direct_messages/show.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    init {
        this.id = id
    }

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TDirectMessage> {
        return json.toDirectMessageResponseData(oauth.get(this))
    }
}
