package com.twitter.meil_mitu.twitter4hk.api.trends

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.ITrendsConverter

class TrendsAPI<TTrendPlace, TTrendResult>(
        oauth: AbsOauth,
        protected val json: ITrendsConverter<TTrendPlace, TTrendResult>) : AbsAPI(oauth) {

    fun place(id: Int): Place<TTrendResult> {
        return Place(oauth, json, id)
    }

    fun available(): Available<TTrendPlace> {
        return Available(oauth, json)
    }

    fun closest(): Closest<TTrendPlace> {
        return Closest(oauth, json)
    }

}
