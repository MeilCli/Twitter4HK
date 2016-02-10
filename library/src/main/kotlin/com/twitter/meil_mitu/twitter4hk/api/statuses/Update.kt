package com.twitter.meil_mitu.twitter4hk.api.statuses

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IStatusConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Update<TStatus>(
        oauth: AbsOauth,
        protected val json: IStatusConverter<TStatus>,
        status: String) :
        AbsPost<TStatus>(oauth) {

    public var status: String? by stringParam("status")
    public var inReplyToStatusId: Long? by longParam("in_reply_to_status_id")
    public var possiblySensitive: Boolean? by booleanParam("possibly_sensitive")
    public var latitude: String? by stringParam("lat")
    public var longitude: String? by stringParam("long")
    public var placeId: String? by stringParam("place_id")
    public var displayCoordinates: Boolean? by booleanParam("display_coordinates")
    /**
     * must not use in JsonConverter for User
     */
    public var trimUser: Boolean? by booleanParam("trim_user")
    public var mediaIds: LongArray? by longArrayParam("media_ids")
    override val url = "https://api.twitter.com/1.1/statuses/update.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    init {
        this.status = status
    }

    @Throws(Twitter4HKException::class)
    override fun call(): TStatus {
        return json.toStatus(oauth.post(this))
    }
}
