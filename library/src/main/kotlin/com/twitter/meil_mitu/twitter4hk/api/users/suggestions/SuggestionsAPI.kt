package com.twitter.meil_mitu.twitter4hk.api.users.suggestions

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IUsersSuggestionsConverter

class SuggestionsAPI<TSuggestion, TSuggestionUser, TUser>(
        oauth: AbsOauth,
        protected val json: IUsersSuggestionsConverter<TSuggestion, TSuggestionUser, TUser>) :
        AbsAPI(oauth) {

    fun get(slug: String) = Get(oauth, json, slug)

    fun list() = List(oauth, json)

    fun members(slug: String) = Members(oauth, json, slug)

}
