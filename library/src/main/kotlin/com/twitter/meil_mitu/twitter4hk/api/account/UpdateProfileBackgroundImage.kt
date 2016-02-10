package com.twitter.meil_mitu.twitter4hk.api.account

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IUserConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import java.io.File

class UpdateProfileBackgroundImage<TUser>(
        oauth: AbsOauth,
        protected val json: IUserConverter<TUser>) : AbsPost<TUser>(oauth) {

    public var image: File? by fileParam("image")
    public var tile: Boolean? by booleanParam("tile")
    public var includeEntities: Boolean? by booleanParam("include_entities")
    public var skipStatus: Boolean? by booleanParam("skip_status")
    public var use: Boolean? by booleanParam("use")
    override val url = "https://api.twitter.com/1.1/account/update_profile_background_image.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): TUser {
        return json.toUser(oauth.post(this))
    }
}
