package com.twitter.meil_mitu.twitter4hk.api.account

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IUserConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import java.io.File

class UpdateProfileImage<TUser>(
        oauth: AbsOauth,
        protected val json: IUserConverter<TUser>,
        image: File) : AbsPost<TUser>(oauth) {

    var image: File? by fileParam("image")
    var includeEntities: Boolean? by booleanParam("include_entities")
    var skipStatus: Boolean? by booleanParam("skip_status")
    override val url = "https://api.twitter.com/1.1/account/update_profile_image.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    init {
        this.image = image
    }

    @Throws(Twitter4HKException::class)
    override fun call(): TUser {
        return json.toUser(oauth.post(this))
    }
}
