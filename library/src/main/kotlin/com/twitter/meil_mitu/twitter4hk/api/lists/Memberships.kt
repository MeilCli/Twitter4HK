package com.twitter.meil_mitu.twitter4hk.api.lists

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.ICursorListsConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Memberships<TCursorLists>(
        oauth: AbsOauth,
        protected val json: ICursorListsConverter<TCursorLists>) :
        AbsGet<ResponseData<TCursorLists>>(oauth) {

    public var userId: Long? by longParam("user_id")
    public var screenName: String? by stringParam("screen_name")
    public var count: Int? by intParam("count")
    public var cursor: Long? by longParam("cursor")
    public var filterToOwnedLists: Boolean? by booleanParam("filter_to_owned_lists")
    override val url = "https://api.twitter.com/1.1/lists/memberships.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TCursorLists> {
        return json.toCursorListsResponseData(oauth.get(this))
    }
}
