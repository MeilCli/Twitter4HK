package com.twitter.meil_mitu.twitter4hk.api.statuses

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IStatusesConverter

class StatusesAPI<TCursorIDs, TOembedStatus, TStatus>(
        oauth: AbsOauth,
        protected val json: IStatusesConverter<TCursorIDs, TOembedStatus, TStatus>) :
        AbsAPI(oauth) {

    fun mentionsTimeline(): MentionsTimeline<TStatus> {
        return MentionsTimeline(oauth, json)
    }

    fun userTimeline(): UserTimeline<TStatus> {
        return UserTimeline(oauth, json)
    }

    fun homeTimeline(): HomeTimeline<TStatus> {
        return HomeTimeline(oauth, json)
    }

    fun retweetsOfMe(): RetweetsOfMe <TStatus> {
        return RetweetsOfMe(oauth, json)
    }

    fun retweets(id: Long): Retweets<TStatus> {
        return Retweets(oauth, json, id)
    }

    fun show(id: Long): Show<TStatus> {
        return Show(oauth, json, id)
    }

    fun destroy(id: Long): Destroy<TStatus> {
        return Destroy(oauth, json, id)
    }

    fun update(status: String): Update<TStatus> {
        return Update(oauth, json, status)
    }

    fun retweet(id: Long): Retweet<TStatus> {
        return Retweet(oauth, json, id)
    }

    fun oembed(id: Long): Oembed<TOembedStatus> {
        return Oembed(oauth, json, id)
    }

    fun retweeters(id: Long): Retweeters<TCursorIDs> {
        return Retweeters(oauth, json, id)
    }

    fun lookup(id: LongArray): Lookup<TStatus> {
        return Lookup(oauth, json, id)
    }
}
