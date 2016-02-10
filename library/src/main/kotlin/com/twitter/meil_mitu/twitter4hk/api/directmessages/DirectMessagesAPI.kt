package com.twitter.meil_mitu.twitter4hk.api.directmessages

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IDirectMessagesConverter

class DirectMessagesAPI<TDirectMessage>(
        oauth: AbsOauth,
        protected val json: IDirectMessagesConverter<TDirectMessage>) : AbsAPI(oauth) {

    fun sent(): Sent<TDirectMessage> {
        return Sent(oauth, json)
    }

    fun show(id: Long): Show<TDirectMessage> {
        return Show(oauth, json, id)
    }

    fun get(): Get<TDirectMessage> {
        return Get(oauth, json)
    }

    fun destroy(id: Long): Destroy<TDirectMessage> {
        return Destroy(oauth, json, id)
    }

    fun postNew(text: String): PostNew<TDirectMessage> {
        return PostNew(oauth, json, text)
    }
}
