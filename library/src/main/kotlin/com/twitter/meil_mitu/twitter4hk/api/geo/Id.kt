package com.twitter.meil_mitu.twitter4hk.api.geo

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.IPlaceConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Id<TPlace>(
        oauth: AbsOauth,
        protected val json: IPlaceConverter<TPlace>,
        private val placeId: String) : AbsGet<ResponseData<TPlace>>(oauth) {

    override val url = "https://api.twitter.com/1.1/geo/id/$placeId.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TPlace> {
        return json.toPlaceResponseData(oauth.get(this))
    }
}
