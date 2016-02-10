package com.twitter.meil_mitu.twitter4hk.api.statuses

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.ICursorIDsConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Retweeters<TCursorIDs>(
        oauth: AbsOauth,
        protected val json: ICursorIDsConverter<TCursorIDs>,
        id: Long) : AbsGet<ResponseData<TCursorIDs>>(oauth) {

    public var id: Long? by longParam("id")
    public var cursor: Long? by longParam("cursor")
    /**
     * must not use in JsonConverter for CursorIDs
     */
    public var stringifyIds: Boolean? by booleanParam("stringify_ids")
    override val url = "https://api.twitter.com/1.1/statuses/retweeters/ids.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    init {
        this.id = id
    }

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TCursorIDs> {
        return json.toCursorIDsResponseData(oauth.get(this))
    }
}
