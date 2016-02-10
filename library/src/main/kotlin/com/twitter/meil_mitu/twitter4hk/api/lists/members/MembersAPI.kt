package com.twitter.meil_mitu.twitter4hk.api.lists.members

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IListsMembersConverter

class MembersAPI<TCursorUsers, TUser, TUserList>(
        oauth: AbsOauth,
        protected val json: IListsMembersConverter<TCursorUsers, TUser, TUserList>) :
        AbsAPI(oauth) {

    fun destroy(): Destroy<TUserList> {
        return Destroy(oauth, json)
    }

    fun createAll(listId: Long): CreateAll<TUserList> {
        return CreateAll(oauth, json, listId)
    }

    fun createAll(slug: String): CreateAll<TUserList> {
        return CreateAll(oauth, json, slug)
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

    operator fun get(listId: Long): Get<TCursorUsers> {
        return Get(oauth, json, listId)
    }

    operator fun get(slug: String): Get <TCursorUsers> {
        return Get(oauth, json, slug)
    }

    fun create(listId: Long, userId: Long): Create<TUserList> {
        return Create(oauth, json, listId, userId)
    }

    fun create(listId: Long, screenName: String): Create<TUserList> {
        return Create(oauth, json, listId, screenName)
    }

    fun create(slug: String, userId: Long): Create<TUserList> {
        return Create(oauth, json, slug, userId)
    }

    fun create(slug: String, screenName: String): Create<TUserList> {
        return Create(oauth, json, slug, screenName)
    }

    fun destroyAll(listId: Long): DestroyAll<TUserList> {
        return DestroyAll(oauth, json, listId)
    }

    fun destroyAll(slug: String): DestroyAll<TUserList> {
        return DestroyAll(oauth, json, slug)
    }

}
