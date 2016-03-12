package com.twitter.meil_mitu.twitter4hk.api.directmessages

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IDirectMessageConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Destroy<TDirectMessage>(
        oauth: AbsOauth,
        protected val json: IDirectMessageConverter<TDirectMessage>,
        id: Long) : AbsPost<TDirectMessage>(oauth) {

    var id: Long? by longParam("id")
    var includeEntities: Boolean? by booleanParam("include_entities")
    override val url = "https://api.twitter.com/1.1/direct_messages/destroy.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    init {
        this.id = id
    }

    @Throws(Twitter4HKException::class)
    override fun call(): TDirectMessage {
        return json.toDirectMessage(oauth.post(this))
    }
}
