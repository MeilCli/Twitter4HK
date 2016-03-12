package com.twitter.meil_mitu.twitter4hk.api.users

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.IUserConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Show<TUser> : AbsGet<ResponseData<TUser>> {

    protected val json: IUserConverter<TUser>
    var userId: Long? by longParam("user_id")
    var screenName: String? by stringParam("screen_name")
    var includeEntities: Boolean? by booleanParam("include_entities")
    override val url = "https://api.twitter.com/1.1/users/show.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    constructor(oauth: AbsOauth, json: IUserConverter<TUser>, userId: Long) : super(oauth) {
        this.json = json
        this.userId = userId
    }

    constructor(oauth: AbsOauth, json: IUserConverter<TUser>, screenName: String) : super(oauth) {
        this.json = json
        this.screenName = screenName
    }

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TUser> {
        return json.toUserResponseData(oauth.get(this))
    }
}
