package com.twitter.meil_mitu.twitter4hk.api.mutes

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.api.mutes.users.UsersAPI
import com.twitter.meil_mitu.twitter4hk.converter.api.IMutesConverter

class MutesAPI<TCursorIDs, TCursorUsers, TUser>(
        oauth: AbsOauth,
        protected val json: IMutesConverter<TCursorIDs, TCursorUsers, TUser>) : AbsAPI(oauth) {

    val users = UsersAPI(oauth, json)

}
