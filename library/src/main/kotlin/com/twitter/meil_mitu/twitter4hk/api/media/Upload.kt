package com.twitter.meil_mitu.twitter4hk.api.media

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IMediaConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import java.io.File

class Upload<TMedia>(
        oauth: AbsOauth,
        protected val json: IMediaConverter<TMedia>,
        media: File) : AbsPost<TMedia>(oauth) {

    var media: File? by fileParam("media")
    override val url = "https://upload.twitter.com/1.1/media/upload.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    init {
        this.media = media
    }

    @Throws(Twitter4HKException::class)
    override fun call(): TMedia {
        return json.toMedia(oauth.post(this))
    }
}
