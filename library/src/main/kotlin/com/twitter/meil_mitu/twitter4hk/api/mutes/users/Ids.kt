package com.twitter.meil_mitu.twitter4hk.api.mutes.users

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

    public var cursor: Long? by longParam("cursor")
    override val url = "https://api.twitter.com/1.1/mutes/users/ids.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TCursorIDs> {
        return json.toCursorIDsResponseData(oauth.get(this))
    }
}
