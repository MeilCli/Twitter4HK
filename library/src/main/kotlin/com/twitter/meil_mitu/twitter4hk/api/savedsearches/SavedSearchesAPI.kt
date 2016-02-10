package com.twitter.meil_mitu.twitter4hk.api.savedsearches

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.ISavedSearchesConverter

class SavedSearchesAPI<TSavedSearch>(
        oauth: AbsOauth,
        protected val json: ISavedSearchesConverter<TSavedSearch>) : AbsAPI(oauth) {

    fun list(): List<TSavedSearch> {
        return List(oauth, json)
    }

    fun show(id: Long): Show<TSavedSearch> {
        return Show(oauth, json, id)
    }

    fun create(query: String): Create<TSavedSearch> {
        return Create(oauth, json, query)
    }

    fun destroy(id: Long): Destroy<TSavedSearch> {
        return Destroy(oauth, json, id)
    }

}
