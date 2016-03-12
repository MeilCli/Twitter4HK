package com.twitter.meil_mitu.twitter4hk.api.blocks

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.ICursorUsersConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class List<TCursorUsers>(
        oauth: AbsOauth,
        protected val json: ICursorUsersConverter<TCursorUsers>) :
        AbsGet<ResponseData<TCursorUsers>>(oauth) {

    var includeEntities: Boolean? by booleanParam("include_entities")
    var skipStatus: Boolean? by booleanParam("skip_status")
    var cursor: Long? by longParam("cursor")
    override val url = "https://api.twitter.com/1.1/blocks/list.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TCursorUsers> {
        return json.toCursorUsersResponseData(oauth.get(this))
    }
}
