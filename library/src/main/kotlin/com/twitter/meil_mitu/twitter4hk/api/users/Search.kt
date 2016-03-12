package com.twitter.meil_mitu.twitter4hk.api.users

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.converter.IUserConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Search<TUser>(
        oauth: AbsOauth,
        protected val json: IUserConverter<TUser>,
        q: String) : AbsGet<ResponseList<TUser>>(oauth) {

    var q: String? by stringParam("q")
    var page: Int? by intParam("page")
    var count: Int? by intParam("count")
    var includeEntities: Boolean? by booleanParam("include_entities")
    override val url = "https://api.twitter.com/1.1/users/search.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    init {
        this.q = q
    }

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseList<TUser> {
        return json.toUserResponseList(oauth.get(this))
    }
}
