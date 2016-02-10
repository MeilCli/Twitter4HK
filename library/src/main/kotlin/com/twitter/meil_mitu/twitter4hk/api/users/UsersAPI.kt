package com.twitter.meil_mitu.twitter4hk.api.users

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.api.users.suggestions.SuggestionsAPI
import com.twitter.meil_mitu.twitter4hk.converter.api.IUsersConverter

class UsersAPI<TBanner, TSuggestion, TSuggestionUser, TUser>(
        oauth: AbsOauth,
        protected val json: IUsersConverter<TBanner, TSuggestion, TSuggestionUser, TUser>) :
        AbsAPI(oauth) {

    protected var suggestions: SuggestionsAPI<TSuggestion, TSuggestionUser, TUser>

    init {
        suggestions = SuggestionsAPI(oauth, json)
    }

    fun lookup(): Lookup<TUser> {
        return Lookup(oauth, json)
    }

    fun show(userId: Long): Show<TUser> {
        return Show(oauth, json, userId)
    }

    fun show(screenName: String): Show<TUser> {
        return Show(oauth, json, screenName)
    }

    fun search(q: String): Search<TUser> {
        return Search(oauth, json, q)
    }

    fun profileBanner(): ProfileBanner<TBanner> {
        return ProfileBanner(oauth, json)
    }

    fun suggestions(): SuggestionsAPI<TSuggestion, TSuggestionUser, TUser> {
        return suggestions
    }

    fun reportSpam(): ReportSpam<TUser> {
        return ReportSpam(oauth, json)
    }

}
