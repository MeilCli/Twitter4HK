package com.twitter.meil_mitu.twitter4hk.api.account

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IUserConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class UpdateProfile<TUser>(
        oauth: AbsOauth,
        protected val json: IUserConverter<TUser>) : AbsPost<TUser>(oauth) {

    public var name: String? by stringParam("name")
    public var profileUrl: String? by stringParam("url")
    public var location: String? by stringParam("location")
    public var description: String? by stringParam("description")
    public var profileLinkColor: String? by stringParam("profile_link_color")
    public var includeEntities: Boolean? by booleanParam("include_entities")
    public var skipStatus: Boolean? by booleanParam("skip_status")
    override val url = "https://api.twitter.com/1.1/account/update_profile.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): TUser {
        return json.toUser(oauth.post(this))
    }
}
