package com.twitter.meil_mitu.twitter4hk.api.users

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.api.users.suggestions.SuggestionsAPI
import com.twitter.meil_mitu.twitter4hk.converter.api.IUsersConverter

class UsersAPI<TBanner, TSuggestion, TSuggestionUser, TUser>(
        oauth: AbsOauth,
        protected val json: IUsersConverter<TBanner, TSuggestion, TSuggestionUser, TUser>) :
        AbsAPI(oauth) {

    val suggestions = SuggestionsAPI(oauth, json)

    fun lookup() = Lookup(oauth, json)

    fun show(userId: Long) = Show(oauth, json, userId)

    fun show(screenName: String) = Show(oauth, json, screenName)

    fun search(q: String) = Search(oauth, json, q)

    fun profileBanner() = ProfileBanner(oauth, json)

    fun reportSpam() = ReportSpam(oauth, json)

}
