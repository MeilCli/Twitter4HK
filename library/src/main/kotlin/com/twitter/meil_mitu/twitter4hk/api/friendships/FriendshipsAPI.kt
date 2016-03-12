package com.twitter.meil_mitu.twitter4hk.api.friendships

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IFriendshipsConverter

class FriendshipsAPI<TCursorIDs, TFriendship, TIDs, TRelationship, TUser>(
        oauth: AbsOauth,
        protected val json: IFriendshipsConverter
        <TCursorIDs, TFriendship, TIDs, TRelationship, TUser>) :
        AbsAPI(oauth) {

    fun noRetweets() = NoRetweets(oauth, json)

    fun incoming() = Incoming(oauth, json)

    fun outgoing() = Outgoing(oauth, json)

    fun create() = Create(oauth, json)

    fun destroy() = Destroy(oauth, json)

    fun update() = Update(oauth, json)

    fun show() = Show(oauth, json)

    fun lookup() = Lookup(oauth, json)

}
