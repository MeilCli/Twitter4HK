package com.twitter.meil_mitu.twitter4hk.api.mutes.users

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IMutesUsersConverter

class UsersAPI<TCursorIDs, TCursorUsers, TUser>(
        oauth: AbsOauth,
        protected val json: IMutesUsersConverter<TCursorIDs, TCursorUsers, TUser>) :
        AbsAPI(oauth) {

    fun create() = Create(oauth, json)

    fun destroy() = Destroy(oauth, json)

    fun ids() = Ids(oauth, json)

    fun list() = List(oauth, json)

}
