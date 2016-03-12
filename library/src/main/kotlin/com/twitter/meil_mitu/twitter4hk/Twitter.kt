package com.twitter.meil_mitu.twitter4hk

import com.twitter.meil_mitu.twitter4hk.api.account.AccountAPI
import com.twitter.meil_mitu.twitter4hk.api.application.ApplicationAPI
import com.twitter.meil_mitu.twitter4hk.api.blocks.BlocksAPI
import com.twitter.meil_mitu.twitter4hk.api.directmessages.DirectMessagesAPI
import com.twitter.meil_mitu.twitter4hk.api.favorites.FavoritesAPI
import com.twitter.meil_mitu.twitter4hk.api.followers.FollowersAPI
import com.twitter.meil_mitu.twitter4hk.api.friends.FriendsAPI
import com.twitter.meil_mitu.twitter4hk.api.friendships.FriendshipsAPI
import com.twitter.meil_mitu.twitter4hk.api.geo.GeoAPI
import com.twitter.meil_mitu.twitter4hk.api.help.HelpAPI
import com.twitter.meil_mitu.twitter4hk.api.lists.ListsAPI
import com.twitter.meil_mitu.twitter4hk.api.media.MediaAPI
import com.twitter.meil_mitu.twitter4hk.api.mutes.MutesAPI
import com.twitter.meil_mitu.twitter4hk.api.oauth.OauthAPI
import com.twitter.meil_mitu.twitter4hk.api.oauth2.Oauth2API
import com.twitter.meil_mitu.twitter4hk.api.savedsearches.SavedSearchesAPI
import com.twitter.meil_mitu.twitter4hk.api.search.SearchAPI
import com.twitter.meil_mitu.twitter4hk.api.statuses.StatusesAPI
import com.twitter.meil_mitu.twitter4hk.api.trends.TrendsAPI
import com.twitter.meil_mitu.twitter4hk.api.users.UsersAPI
import com.twitter.meil_mitu.twitter4hk.converter.api.ITwitterConverter
import com.twitter.meil_mitu.twitter4hk.streaming.StreamAPI

open class Twitter<TBanner, TConfigurationResult, TCursorIDs, TCursorLists,
        TCursorUsers, TDirectMessage, TFriendship, TIDs, TLanguage, TMedia, TOembedStatus, TPlace,
        TPlaceQuery, TPrivacyResult, TRateLimitResult, TRelationship, TSavedSearch, TSearchResult,
        TSetting, TStatus, TSuggestion, TSuggestionUser, TTosResult, TTrendPlace, TTrendResult,
        TUser, TUserList>(
        oauth: AbsOauth,
        protected var json: ITwitterConverter<TBanner, TConfigurationResult, TCursorIDs,
                TCursorLists, TCursorUsers, TDirectMessage, TFriendship, TIDs, TLanguage, TMedia,
                TOembedStatus, TPlace, TPlaceQuery, TPrivacyResult, TRateLimitResult, TRelationship,
                TSavedSearch, TSearchResult, TSetting, TStatus, TSuggestion, TSuggestionUser,
                TTosResult, TTrendPlace, TTrendResult, TUser, TUserList>) {

    var oauth: AbsOauth
        protected set
    val oauth1 = OauthAPI(oauth, json)
    val oauth2 = Oauth2API(oauth, json)
    val statuses = StatusesAPI(oauth, json)
    val media = MediaAPI(oauth, json)
    val search = SearchAPI(oauth, json)
    val directMessage = DirectMessagesAPI(oauth, json)
    val friendships = FriendshipsAPI(oauth, json)
    val friends = FriendsAPI(oauth, json)
    val followers = FollowersAPI(oauth, json)
    val account = AccountAPI(oauth, json)
    val blocks = BlocksAPI(oauth, json)
    val users = UsersAPI(oauth, json)
    val mutes = MutesAPI(oauth, json)
    val favorites = FavoritesAPI(oauth, json)
    val lists = ListsAPI(oauth, json)
    val savedSearches = SavedSearchesAPI(oauth, json)
    val geo = GeoAPI(oauth, json)
    val trends = TrendsAPI(oauth, json)
    val application = ApplicationAPI(oauth, json)
    val help = HelpAPI(oauth, json)
    val stream = StreamAPI(oauth, json)

    init {
        this.oauth = oauth
    }

}
