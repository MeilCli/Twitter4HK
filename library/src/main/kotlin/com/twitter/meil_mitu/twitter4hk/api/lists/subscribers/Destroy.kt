package com.twitter.meil_mitu.twitter4hk.api.lists.subscribers

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IUserListConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Destroy<TUserList> : AbsPost<TUserList> {

    protected val json: IUserListConverter<TUserList>
    public var listId: Long? by longParam("list_id")
    public var slug: String? by stringParam("slug")
    public var ownerScreenName: String? by stringParam("owner_screen_name")
    public var ownerId: Long? by longParam("owner_id")
    override val url = "https://api.twitter.com/1.1/lists/subscribers/destroy.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    constructor(
            oauth: AbsOauth,
            json: IUserListConverter<TUserList>,
            listId: Long) : super(oauth) {
        this.json = json
        this.listId = listId
    }

    constructor(
            oauth: AbsOauth,
            json: IUserListConverter<TUserList>,
            slug: String) : super(oauth) {
        this.json = json
        this.slug = slug
    }

    @Throws(Twitter4HKException::class)
    override fun call(): TUserList {
        return json.toUserList(oauth.post(this))
    }
}
