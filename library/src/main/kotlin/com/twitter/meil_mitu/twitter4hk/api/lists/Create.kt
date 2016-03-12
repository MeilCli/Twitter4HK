package com.twitter.meil_mitu.twitter4hk.api.lists

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IUserListConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Create<TUserList>(
        oauth: AbsOauth,
        protected val json: IUserListConverter<TUserList>) : AbsPost<TUserList>(oauth) {

    var name: String? by stringParam("name")
    var mode: String? by stringParam("mode")
    var description: String? by stringParam("description")
    override val url = "https://api.twitter.com/1.1/lists/create.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): TUserList {
        return json.toUserList(oauth.post(this))
    }
}
