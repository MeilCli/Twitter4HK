package com.twitter.meil_mitu.twitter4hk.api.lists.subscribers

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IListsSubscribersConverter


class SubscribersAPI<TCursorUsers, TUser, TUserList>(
        oauth: AbsOauth,
        protected val json: IListsSubscribersConverter<TCursorUsers, TUser, TUserList>) :
        AbsAPI(oauth) {

    operator fun get(listId: Long): Get<TCursorUsers> {
        return Get(oauth, json, listId)
    }

    operator fun get(slug: String): Get<TCursorUsers> {
        return Get(oauth, json, slug)
    }

    fun create(listId: Long): Create<TUserList> {
        return Create(oauth, json, listId)
    }

    fun create(slug: String): Create<TUserList> {
        return Create(oauth, json, slug)
    }

    fun show(listId: Long, userId: Long): Show<TUser> {
        return Show(oauth, json, listId, userId)
    }

    fun show(listId: Long, screenName: String): Show<TUser> {
        return Show(oauth, json, listId, screenName)
    }

    fun show(slug: String, userId: Long): Show<TUser> {
        return Show(oauth, json, slug, userId)
    }

    fun show(slug: String, screenName: String): Show<TUser> {
        return Show(oauth, json, slug, screenName)
    }

    fun destroy(listId: Long): Destroy<TUserList> {
        return Destroy(oauth, json, listId)
    }

    fun destroy(slug: String): Destroy<TUserList> {
        return Destroy(oauth, json, slug)
    }

}
