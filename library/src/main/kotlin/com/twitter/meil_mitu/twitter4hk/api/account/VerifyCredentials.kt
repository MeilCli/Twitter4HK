package com.twitter.meil_mitu.twitter4hk.api.account

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.IUserConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class VerifyCredentials<TUser>(
        oauth: AbsOauth,
        protected val json: IUserConverter<TUser>) : AbsGet<ResponseData<TUser>>(oauth) {

    public var includeEntities: Boolean? by booleanParam("include_entities")
    public var skipStatus: Boolean? by booleanParam("skip_status")
    override val url = "https://api.twitter.com/1.1/account/verify_credentials.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TUser> {
        return json.toUserResponseData(oauth.get(this))
    }
}
