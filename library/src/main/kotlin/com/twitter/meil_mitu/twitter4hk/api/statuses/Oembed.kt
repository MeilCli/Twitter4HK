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

    public var id: Long? by longParam("id")
    public var maxwidth: Int? by intParam("maxwidth")
    public var hideMedia: Boolean? by booleanParam("hide_media")
    public var hideThread: Boolean? by booleanParam("hide_thread")
    public var omitScript: Boolean? by booleanParam("omit_script")
    public var align: String? by stringParam("align")
    public var related: String? by stringParam("related")
    public var lang: String? by stringParam("lang")
    public var widgetType: String? by stringParam("widget_type")
    public var hideTweet: Boolean? by booleanParam("hide_tweet")
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
