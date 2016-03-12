package com.twitter.meil_mitu.twitter4hk.api.followers

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IFollowersConverter

class FollowersAPI<TCursorIDs, TCursorUsers>(
        oauth: AbsOauth,
        protected val json: IFollowersConverter<TCursorIDs, TCursorUsers>) : AbsAPI(oauth) {

    fun ids() = Ids(oauth, json)

    fun list() = List(oauth, json)

}
