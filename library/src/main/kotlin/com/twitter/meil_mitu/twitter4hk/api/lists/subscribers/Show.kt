package com.twitter.meil_mitu.twitter4hk.api.lists.subscribers

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.IUserConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Show<TUser> : AbsGet<ResponseData<TUser>> {

    protected val json: IUserConverter<TUser>
    public var listId: Long? by longParam("list_id")
    public var userId: Long? by longParam("user_id")
    public var screenName: String? by stringParam("screen_name")
    public var slug: String? by stringParam("slug")
    public var ownerScreenName: String? by stringParam("owner_screen_name")
    public var ownerId: Long? by longParam("owner_id")
    public var includeEntities: Boolean? by booleanParam("include_entities")
    public var skipStatus: Boolean? by booleanParam("skip_status")
    override val url = "https://api.twitter.com/1.1/lists/subscribers/show.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    constructor(
            oauth: AbsOauth,
            json: IUserConverter<TUser>,
            listId: Long,
            userId: Long) : super(oauth) {
        this.json = json
        this.listId = listId
        this.userId = userId
    }

    constructor(
            oauth: AbsOauth,
            json: IUserConverter<TUser>,
            listId: Long,
            screenName: String) : super(oauth) {
        this.json = json
        this.listId = listId
        this.screenName = screenName
    }

    constructor(
            oauth: AbsOauth,
            json: IUserConverter<TUser>,
            slug: String,
            userId: Long) : super(oauth) {
        this.json = json
        this.slug = slug
        this.userId = userId
    }

    constructor(
            oauth: AbsOauth,
            json: IUserConverter<TUser>,
            slug: String,
            screenName: String) : super(oauth) {
        this.json = json
        this.slug = slug
        this.screenName = screenName
    }

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TUser> {
        return json.toUserResponseData(oauth.get(this))
    }
}
