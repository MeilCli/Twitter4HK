package com.twitter.meil_mitu.twitter4hk.api.lists.members

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.ICursorUsersConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Get<TCursorUsers> : AbsGet<ResponseData<TCursorUsers>> {

    protected val json: ICursorUsersConverter<TCursorUsers>
    var listId: Long? by longParam("list_id")
    var slug: String? by stringParam("slug")
    var ownerScreenName: String? by stringParam("owner_screen_name")
    var ownerId: Long? by longParam("owner_id")
    var count: Int? by intParam("count")
    var cursor: Long? by longParam("cursor")
    var includeEntities: Boolean? by booleanParam("include_entities")
    var skipStatus: Boolean? by booleanParam("skip_status")
    override val url = "https://api.twitter.com/1.1/lists/members.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    constructor(
            oauth: AbsOauth,
            json: ICursorUsersConverter<TCursorUsers>,
            listId: Long) : super(oauth) {
        this.json = json
        this.listId = listId
    }

    constructor(
            oauth: AbsOauth,
            json: ICursorUsersConverter<TCursorUsers>,
            slug: String) : super(oauth) {
        this.json = json
        this.slug = slug
    }

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TCursorUsers> {
        return json.toCursorUsersResponseData(oauth.get(this))
    }
}
