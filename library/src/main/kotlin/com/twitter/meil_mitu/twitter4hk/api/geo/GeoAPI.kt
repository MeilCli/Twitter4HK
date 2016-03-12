package com.twitter.meil_mitu.twitter4hk.api.geo

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IGeoConverter

class GeoAPI<TPlace, TPlaceQuery>(
        oauth: AbsOauth,
        protected val json: IGeoConverter<TPlace, TPlaceQuery>) : AbsAPI(oauth) {

    fun id(placeId: String) = Id(oauth, json, placeId)

    fun reverseGeocode(latitude: String, longitude: String) = ReverseGeocode(oauth, json, latitude, longitude)

    fun search() = Search(oauth, json)

}
