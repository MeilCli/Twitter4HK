package com.twitter.meil_mitu.twitter4hk.api.friends

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IFriendsConverter

class FriendsAPI<TCursorIDs, TCursorUsers>(
        oauth: AbsOauth,
        protected val json: IFriendsConverter<TCursorIDs, TCursorUsers>) : AbsAPI(oauth) {

    fun ids(): Ids<TCursorIDs> {
        return Ids(oauth, json)
    }

    fun list(): List<TCursorUsers> {
        return List(oauth, json)
    }
}
