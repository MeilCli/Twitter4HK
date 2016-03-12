package com.twitter.meil_mitu.twitter4hk.aclog.api.users

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.aclog.converter.api.IUsersConverter

class UsersAPI<TAclogStats, TAclogUser>(
        oauth: AbsOauth,
        protected val json: IUsersConverter<TAclogStats, TAclogUser>) : AbsAPI(oauth) {

    fun stats() = Stats(oauth, json)

    fun discoveredBy() = DiscoveredBy(oauth, json)

    fun discoveredUsers() = DiscoveredUsers(oauth, json)

}
