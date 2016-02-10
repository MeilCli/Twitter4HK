package com.twitter.meil_mitu.twitter4hk.api.friendships

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IFriendshipsConverter

class FriendshipsAPI<TCursorIDs, TFriendship, TIDs, TRelationship, TUser>(
        oauth: AbsOauth,
        protected val json: IFriendshipsConverter
        <TCursorIDs, TFriendship, TIDs, TRelationship, TUser>) :
        AbsAPI(oauth) {

    fun noRetweets(): NoRetweets<TIDs> {
        return NoRetweets(oauth, json)
    }

    fun incoming(): Incoming<TCursorIDs> {
        return Incoming(oauth, json)
    }

    fun outgoing(): Outgoing<TCursorIDs> {
        return Outgoing(oauth, json)
    }

    fun create(): Create<TUser> {
        return Create(oauth, json)
    }

    fun destroy(): Destroy<TUser> {
        return Destroy(oauth, json)
    }

    fun update(): Update<TRelationship> {
        return Update(oauth, json)
    }

    fun show(): Show<TRelationship> {
        return Show(oauth, json)
    }

    fun lookup(): Lookup<TFriendship> {
        return Lookup(oauth, json)
    }

}
