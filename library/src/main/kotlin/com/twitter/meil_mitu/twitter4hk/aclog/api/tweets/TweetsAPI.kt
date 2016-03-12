package com.twitter.meil_mitu.twitter4hk.aclog.api.tweets

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.aclog.converter.api.ITweetsConverter

class TweetsAPI<TAclogStatus>(
        oauth: AbsOauth,
        protected val json: ITweetsConverter<TAclogStatus>) : AbsAPI(oauth) {

    fun show(id: Long) = Show(oauth, json, id)

    fun lookup(ids: LongArray) = Lookup(oauth, json, ids)

    fun userBest() = UserBest(oauth, json)

    fun userTimeline() = UserTimeline(oauth, json)

    fun userFavorites() = UserFavorites(oauth, json)

    fun userFavoritedBy() = UserFavoritedBy(oauth, json)

}
