package com.twitter.meil_mitu.twitter4hk.api.lists

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.api.lists.members.MembersAPI
import com.twitter.meil_mitu.twitter4hk.api.lists.subscribers.SubscribersAPI
import com.twitter.meil_mitu.twitter4hk.converter.api.IListsConverter

class ListsAPI<TCursorLists, TCursorUsers, TStatus, TUser, TUserList>(
        oauth: AbsOauth,
        protected val json: IListsConverter
        <TCursorLists, TCursorUsers, TStatus, TUser, TUserList>) : AbsAPI(oauth) {

    protected var Members: MembersAPI<TCursorUsers, TUser, TUserList>
    protected var Subscribers: SubscribersAPI<TCursorUsers, TUser, TUserList>

    init {
        this.Members = MembersAPI(oauth, json)
        this.Subscribers = SubscribersAPI(oauth, json)
    }

    fun list(): List<TUserList> {
        return List(oauth, json)
    }

    fun statuses(listId: Long): Statuses<TStatus> {
        return Statuses(oauth, json, listId)
    }

    fun statuses(slug: String): Statuses<TStatus> {
        return Statuses(oauth, json, slug)
    }

    fun members(): MembersAPI<TCursorUsers, TUser, TUserList> {
        return Members
    }

    fun memberships(): Memberships<TCursorLists> {
        return Memberships(oauth, json)
    }

    fun subscribers(): SubscribersAPI<TCursorUsers, TUser, TUserList> {
        return Subscribers
    }

    fun destroy(listId: Long): Destroy<TUserList> {
        return Destroy(oauth, json, listId)
    }

    fun destroy(slug: String): Destroy<TUserList> {
        return Destroy(oauth, json, slug)
    }

    fun update(listId: Long): Update<TUserList> {
        return Update(oauth, json, listId)
    }

    fun update(slug: String): Update<TUserList> {
        return Update(oauth, json, slug)
    }

    fun create(): Create<TUserList> {
        return Create(oauth, json)
    }

    fun show(listId: Long): Show<TUserList> {
        return Show(oauth, json, listId)
    }

    fun show(slug: String): Show<TUserList> {
        return Show(oauth, json, slug)
    }

    fun subscriptions(): Subscriptions<TCursorLists> {
        return Subscriptions(oauth, json)
    }

    fun ownerships(): Ownerships<TCursorLists> {
        return Ownerships(oauth, json)
    }

}
