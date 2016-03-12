package com.twitter.meil_mitu.twitter4hk.api.trends

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.ITrendsConverter

class TrendsAPI<TTrendPlace, TTrendResult>(
        oauth: AbsOauth,
        protected val json: ITrendsConverter<TTrendPlace, TTrendResult>) : AbsAPI(oauth) {

    fun place(id: Int) = Place(oauth, json, id)

    fun available() = Available(oauth, json)

    fun closest() = Closest(oauth, json)

}
