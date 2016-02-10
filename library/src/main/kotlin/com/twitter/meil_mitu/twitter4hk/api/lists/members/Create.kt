package com.twitter.meil_mitu.twitter4hk.api.lists.members

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IUserListConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Create<TUserList> : AbsPost<TUserList> {

    protected val json: IUserListConverter<TUserList>
    public var listId: Long? by longParam("list_id")
    public var userId: Long? by longParam("user_id")
    public var screenName: String? by stringParam("screen_name")
    public var slug: String? by stringParam("slug")
    public var ownerScreenName: String? by stringParam("owner_screen_name")
    public var ownerId: Long? by longParam("owner_id")
    override val url = "https://api.twitter.com/1.1/lists/members/create.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    constructor(
            oauth: AbsOauth,
            json: IUserListConverter<TUserList>,
            listId: Long,
            userId: Long) : super(oauth) {
        this.json = json
        this.listId = listId
        this.userId = userId
    }

    constructor(
            oauth: AbsOauth,
            json: IUserListConverter<TUserList>,
            listId: Long,
            screenName: String) : super(oauth) {
        this.json = json
        this.listId = listId
        this.screenName = screenName
    }

    constructor(
            oauth: AbsOauth,
            json: IUserListConverter<TUserList>,
            slug: String,
            userId: Long) : super(oauth) {
        this.json = json
        this.slug = slug
        this.userId = userId
    }

    constructor(
            oauth: AbsOauth,
            json: IUserListConverter<TUserList>,
            slug: String,
            screenName: String) : super(oauth) {
        this.json = json
        this.slug = slug
        this.screenName = screenName
    }

    @Throws(Twitter4HKException::class)
    override fun call(): TUserList {
        return json.toUserList(oauth.post(this))
    }
}
