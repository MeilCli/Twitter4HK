package com.twitter.meil_mitu.twitter4hk.api.statuses

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.IOembedStatusConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

// url is contain id ,so set substring url to id.
class Oembed<TOembedStatus>(
        oauth: AbsOauth,
        protected val json: IOembedStatusConverter<TOembedStatus>,
        id: Long) : AbsGet<ResponseData<TOembedStatus>>(oauth) {

    var id: Long? by longParam("id")
    var maxwidth: Int? by intParam("maxwidth")
    var hideMedia: Boolean? by booleanParam("hide_media")
    var hideThread: Boolean? by booleanParam("hide_thread")
    var omitScript: Boolean? by booleanParam("omit_script")
    var align: String? by stringParam("align")
    var related: String? by stringParam("related")
    var lang: String? by stringParam("lang")
    var widgetType: String? by stringParam("widget_type")
    var hideTweet: Boolean? by booleanParam("hide_tweet")
    override val url = "https://api.twitter.com/1.1/statuses/oembed.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    init {
        this.id = id
    }

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TOembedStatus> {
        return json.toOembedStatusResponseData(oauth.get(this))
    }
}
