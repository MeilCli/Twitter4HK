package com.twitter.meil_mitu.twitter4hk.api.followers

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

    public var userId: Long? by longParam("user_id")
    public var screenName: String? by stringParam("screen_name")
    public var cursor: Long? by longParam("cursor")
    public var count: Int? by intParam("count")
    public var skipStatus: Boolean? by booleanParam("skip_status")
    public var includeUserEntities: Boolean? by booleanParam("include_user_entities")
    override val url = "https://api.twitter.com/1.1/followers/list.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TCursorUsers> {
        return json.toCursorUsersResponseData(oauth.get(this))
    }
}
