package com.twitter.meil_mitu.twitter4hk.api.directmessages

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.converter.IDirectMessageConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Get<TDirectMessage>(
        oauth: AbsOauth,
        protected val json: IDirectMessageConverter<TDirectMessage>) :
        AbsGet<ResponseList<TDirectMessage>>(oauth) {

    public var sinceId: Long? by longParam("since_id")
    public var maxId: Long? by longParam("max_id")
    public var count: Int? by intParam("count")
    public var includeEntities: Boolean? by booleanParam("include_entities")
    /**
     * must not use in JsonConverter for DirectMessage
     */
    public var skipStatus: Boolean? by booleanParam("skip_status")
    override val url = "https://api.twitter.com/1.1/direct_messages.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseList<TDirectMessage> {
        return json.toDirectMessageResponseList(oauth.get(this))
    }
}
