package com.twitter.meil_mitu.twitter4hk.aclog

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.aclog.api.tweets.TweetsAPI
import com.twitter.meil_mitu.twitter4hk.aclog.api.users.UsersAPI
import com.twitter.meil_mitu.twitter4hk.aclog.converter.api.IAclogConverter

open class Aclog<TAclogStats, TAclogStatus, TAclogUser>(
        protected var oauth: AbsOauth,
        protected var json: IAclogConverter<TAclogStats, TAclogStatus, TAclogUser>) {
    public val tweets: TweetsAPI<TAclogStatus>
    public val users: UsersAPI<TAclogStats, TAclogUser>

    init {
        //api
        this.tweets = TweetsAPI(oauth, json)
        this.users = UsersAPI(oauth, json)
    }


}
