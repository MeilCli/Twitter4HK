package com.twitter.meil_mitu.twitter4hk.aclog.api.tweets

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.aclog.converter.api.ITweetsConverter

class TweetsAPI<TAclogStatus>(
        oauth: AbsOauth,
        protected val json: ITweetsConverter<TAclogStatus>) : AbsAPI(oauth) {

    fun show(id: Long): Show<TAclogStatus> {
        return Show(oauth, json, id)
    }

    fun lookup(ids: LongArray): Lookup<TAclogStatus> {
        return Lookup(oauth, json, ids)
    }

    fun userBest(): UserBest<TAclogStatus> {
        return UserBest(oauth, json)
    }

    fun userTimeline(): UserTimeline<TAclogStatus> {
        return UserTimeline(oauth, json)
    }

    fun userFavorites(): UserFavorites<TAclogStatus> {
        return UserFavorites(oauth, json)
    }

    fun userFavoritedBy(): UserFavoritedBy<TAclogStatus> {
        return UserFavoritedBy(oauth, json)
    }

}
