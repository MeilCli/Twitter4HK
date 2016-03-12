package com.twitter.meil_mitu.twitter4hk.api.directmessages

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IDirectMessageConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class PostNew<TDirectMessage>(
        oauth: AbsOauth,
        protected val json: IDirectMessageConverter<TDirectMessage>,
        text: String) : AbsPost<TDirectMessage>(oauth) {

    var text: String? by stringParam("text")
    var userId: Long? by longParam("user_id")
    var screenName: String? by stringParam("screen_name")
    override val url = "https://api.twitter.com/1.1/direct_messages/new.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    init {
        this.text = text
    }

    @Throws(Twitter4HKException::class)
    override fun call(): TDirectMessage {
        return json.toDirectMessage(oauth.post(this))
    }
}
