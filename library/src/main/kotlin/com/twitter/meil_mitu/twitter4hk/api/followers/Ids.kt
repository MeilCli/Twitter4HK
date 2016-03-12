package com.twitter.meil_mitu.twitter4hk.api.followers

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.ICursorIDsConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Ids<TCursorIDs>(
        oauth: AbsOauth,
        protected val json: ICursorIDsConverter<TCursorIDs>) :
        AbsGet<ResponseData<TCursorIDs>>(oauth) {

    var userId: Long? by longParam("user_id")
    var screenName: String? by stringParam("screen_name")
    var cursor: Long? by longParam("cursor")
    /**
     * must not use in JsonConverter for CursorIDs
     */
    var stringifyIds: Boolean? by booleanParam("stringify_ids")
    var count: Int? by intParam("count")
    override val url = "https://api.twitter.com/1.1/followers/ids.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TCursorIDs> {
        return json.toCursorIDsResponseData(oauth.get(this))
    }
}
