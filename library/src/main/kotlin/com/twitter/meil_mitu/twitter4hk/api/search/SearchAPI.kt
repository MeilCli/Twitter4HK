package com.twitter.meil_mitu.twitter4hk.api.search

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.ISearchConverter

class SearchAPI<TSearchResult>(
        oauth: AbsOauth,
        protected val json: ISearchConverter<TSearchResult>) : AbsAPI(oauth) {

    fun tweets(q: String): Tweets<TSearchResult> {
        return Tweets(oauth, json, q)
    }

}
