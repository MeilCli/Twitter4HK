package com.twitter.meil_mitu.twitter4hk.api.statuses

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IStatusesConverter

class StatusesAPI<TCursorIDs, TOembedStatus, TStatus>(
        oauth: AbsOauth,
        protected val json: IStatusesConverter<TCursorIDs, TOembedStatus, TStatus>) :
        AbsAPI(oauth) {

    fun mentionsTimeline() = MentionsTimeline(oauth, json)

    fun userTimeline() = UserTimeline(oauth, json)

    fun homeTimeline() = HomeTimeline(oauth, json)

    fun retweetsOfMe() = RetweetsOfMe(oauth, json)

    fun retweets(id: Long) = Retweets(oauth, json, id)

    fun show(id: Long) = Show(oauth, json, id)

    fun destroy(id: Long) = Destroy(oauth, json, id)

    fun update(status: String) = Update(oauth, json, status)

    fun retweet(id: Long) = Retweet(oauth, json, id)

    fun oembed(id: Long) = Oembed(oauth, json, id)

    fun retweeters(id: Long) = Retweeters(oauth, json, id)

    fun lookup(id: LongArray) = Lookup(oauth, json, id)

}
