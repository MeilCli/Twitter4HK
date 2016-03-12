package com.twitter.meil_mitu.twitter4hk.api.geo

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.IPlaceQueryConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Search<TPlaceQuery>(
        oauth: AbsOauth,
        protected val json: IPlaceQueryConverter<TPlaceQuery>) :
        AbsGet<ResponseData<TPlaceQuery>>(oauth) {

    var latitude: String? by stringParam("lat")
    var longitude: String? by stringParam("long")
    var query: String? by stringParam("query")
    var ip: String? by stringParam("ip")
    var granularity: String? by stringParam("granularity")
    var accuracy: String? by stringParam("accuracy")
    var maxResults: Int? by intParam("max_results")
    var containedWithin: String? by stringParam("contained_within")
    var attributeStreetAddress: String? by stringParam("attribute:street_address")
    var callback: String? by stringParam("callback")
    override val url = "https://api.twitter.com/1.1/geo/search.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TPlaceQuery> {
        return json.toPlaceQueryResponseData(oauth.get(this))
    }
}
