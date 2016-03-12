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

    val members = MembersAPI(oauth, json)
    val subscribers = SubscribersAPI(oauth, json)

    fun list() = List(oauth, json)

    fun statuses(listId: Long) = Statuses(oauth, json, listId)

    fun statuses(slug: String) = Statuses(oauth, json, slug)

    fun memberships() = Memberships(oauth, json)

    fun destroy(listId: Long) = Destroy(oauth, json, listId)

    fun destroy(slug: String) = Destroy(oauth, json, slug)

    fun update(listId: Long) = Update(oauth, json, listId)

    fun update(slug: String) = Update(oauth, json, slug)

    fun create() = Create(oauth, json)

    fun show(listId: Long) = Show(oauth, json, listId)

    fun show(slug: String) = Show(oauth, json, slug)

    fun subscriptions() = Subscriptions(oauth, json)

    fun ownerships() = Ownerships(oauth, json)

}
