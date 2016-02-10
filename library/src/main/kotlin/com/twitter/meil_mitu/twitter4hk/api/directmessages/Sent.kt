package com.twitter.meil_mitu.twitter4hk.api.directmessages

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.IDirectMessageConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Sent<TDirectMessage>(
        oauth: AbsOauth,
        protected val json: IDirectMessageConverter<TDirectMessage>) :
        AbsGet<ResponseData<TDirectMessage>>(oauth) {

    public var sinceId: Long? by longParam("since_id")
    public var maxId: Long? by longParam("max_id")
    public var count: Int? by intParam("count")
    public var page: Int? by intParam("page")
    public var includeEntities: Boolean? by booleanParam("include_entities")
    override val url = "https://api.twitter.com/1.1/direct_messages/sent.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TDirectMessage> {
        return json.toDirectMessageResponseData(oauth.get(this))
    }
}
