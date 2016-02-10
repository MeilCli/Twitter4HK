package com.twitter.meil_mitu.twitter4hk.api.favorites

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IFavoritesConverter

class FavoritesAPI<TStatus>(
        oauth: AbsOauth,
        protected val json: IFavoritesConverter<TStatus>) : AbsAPI(oauth) {

    fun list(): List<TStatus> {
        return List(oauth, json)
    }

    fun destroy(id: Long): Destroy<TStatus> {
        return Destroy(oauth, json, id)
    }

    fun create(id: Long): Create<TStatus> {
        return Create(oauth, json, id)
    }

}
