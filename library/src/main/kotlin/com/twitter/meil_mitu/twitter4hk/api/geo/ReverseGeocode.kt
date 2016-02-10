package com.twitter.meil_mitu.twitter4hk.api.geo

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.IPlaceQueryConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class ReverseGeocode<TPlaceQuery>(
        oauth: AbsOauth,
        protected val json: IPlaceQueryConverter<TPlaceQuery>,
        latitude: String,
        longitude: String) : AbsGet<ResponseData<TPlaceQuery>>(oauth) {

    public var latitude: String? by stringParam("lat")
    public var longitude: String? by stringParam("long")
    public var accuracy: String? by stringParam("accuracy")
    public var granularity: String? by stringParam("granularity")
    public var maxResults: Int? by intParam("max_results")
    public var callback: String? by stringParam("callback")
    override val url = "https://api.twitter.com/1.1/geo/reverse_geocode.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    init {
        this.latitude = latitude
        this.longitude = longitude
    }

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TPlaceQuery> {
        return json.toPlaceQueryResponseData(oauth.get(this))
    }
}
