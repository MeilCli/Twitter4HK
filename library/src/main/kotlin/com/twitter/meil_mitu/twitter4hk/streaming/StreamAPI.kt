package com.twitter.meil_mitu.twitter4hk.streaming

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IStreamConverter
import com.twitter.meil_mitu.twitter4hk.streaming.filter.FilterStream
import com.twitter.meil_mitu.twitter4hk.streaming.sample.SampleStream
import com.twitter.meil_mitu.twitter4hk.streaming.user.UserStream

class StreamAPI<TDirectMessage, TStatus, TUser, TUserList>(
        oauth: AbsOauth,
        protected val json: IStreamConverter<TDirectMessage, TStatus, TUser, TUserList>) :
        AbsAPI(oauth) {

    fun sample() = SampleStream(oauth, json)

    fun filter() = FilterStream(oauth, json)

    fun user() = UserStream(oauth, json)

}
