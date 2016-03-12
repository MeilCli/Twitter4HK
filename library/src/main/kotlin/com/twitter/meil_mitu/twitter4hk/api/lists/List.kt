package com.twitter.meil_mitu.twitter4hk.api.lists

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.converter.IUserListConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class List<TUserList>(
        oauth: AbsOauth,
        protected val json: IUserListConverter<TUserList>) :
        AbsGet<ResponseList<TUserList>>(oauth) {

    var userId: Long? by longParam("user_id")
    var screenName: String? by stringParam("screen_name")
    var reverse: Boolean? by booleanParam("reverse")
    override val url = "https://api.twitter.com/1.1/lists/list.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseList<TUserList> {
        return json.toUserListResponseList(oauth.get(this))
    }
}
