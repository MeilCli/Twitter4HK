package com.twitter.meil_mitu.twitter4hk.api.lists.members

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IUserListConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Destroy<TUserList>(
        oauth: AbsOauth,
        protected val json: IUserListConverter<TUserList>) : AbsPost<TUserList>(oauth) {

    public var listId: Long? by longParam("list_id")
    public var slug: String? by stringParam("slug")
    public var userId: Long? by longParam("user_id")
    public var screenName: String? by stringParam("screen_name")
    public var ownerScreenName: String? by stringParam("owner_screen_name")
    public var ownerId: Long? by longParam("owner_id")
    override val url = "https://api.twitter.com/1.1/lists/members/destroy.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): TUserList {
        return json.toUserList(oauth.post(this))
    }
}
