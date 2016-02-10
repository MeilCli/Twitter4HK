package com.twitter.meil_mitu.twitter4hk.api.blocks

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IBlocksConverter

class BlocksAPI<TCursorIDs, TCursorUsers, TUser>(
        oauth: AbsOauth,
        protected val json: IBlocksConverter<TCursorIDs, TCursorUsers, TUser>) :
        AbsAPI(oauth) {

    fun list(): List<TCursorUsers> {
        return List(oauth, json)
    }

    fun ids(): Ids<TCursorIDs> {
        return Ids(oauth, json)
    }

    fun create(): Create<TUser> {
        return Create(oauth, json)
    }

    fun destroy(): Destroy<TUser> {
        return Destroy(oauth, json)
    }

}
