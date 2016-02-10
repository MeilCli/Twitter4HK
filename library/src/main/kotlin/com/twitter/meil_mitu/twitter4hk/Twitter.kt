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
    public val oauth1: OauthAPI
    public val oauth2: Oauth2API
    public val statuses: StatusesAPI<TCursorIDs, TOembedStatus, TStatus>
    public val media: MediaAPI<TMedia>
    public val search: SearchAPI<TSearchResult>
    public val directMessage: DirectMessagesAPI<TDirectMessage>
    public val friendships: FriendshipsAPI<TCursorIDs, TFriendship, TIDs, TRelationship, TUser>
    public val friends: FriendsAPI<TCursorIDs, TCursorUsers>
    public val followers: FollowersAPI<TCursorIDs, TCursorUsers>
    public val account: AccountAPI<TSetting, TUser>
    public val blocks: BlocksAPI<TCursorIDs, TCursorUsers, TUser>
    public val users: UsersAPI<TBanner, TSuggestion, TSuggestionUser, TUser>
    public val mutes: MutesAPI<TCursorIDs, TCursorUsers, TUser>
    public val favorites: FavoritesAPI<TStatus>
    public val lists: ListsAPI<TCursorLists, TCursorUsers, TStatus, TUser, TUserList>
    public val savedSearches: SavedSearchesAPI<TSavedSearch>
    public val geo: GeoAPI<TPlace, TPlaceQuery>
    public val trends: TrendsAPI<TTrendPlace, TTrendResult>
    public val application: ApplicationAPI<TRateLimitResult>
    public val help: HelpAPI<TConfigurationResult, TLanguage, TPrivacyResult, TTosResult>
    public val stream: StreamAPI<TDirectMessage, TStatus, TUser, TUserList>

    init {
        this.oauth = oauth
        //API
        this.oauth1 = OauthAPI(this.oauth, json)
        this.oauth2 = Oauth2API(this.oauth, json)
        this.statuses = StatusesAPI(this.oauth, json)
        this.media = MediaAPI(this.oauth, json)
        this.search = SearchAPI(this.oauth, json)
        this.directMessage = DirectMessagesAPI(this.oauth, json)
        this.friendships = FriendshipsAPI(this.oauth, json)
        this.friends = FriendsAPI(this.oauth, json)
        this.followers = FollowersAPI(this.oauth, json)
        this.account = AccountAPI(this.oauth, json)
        this.blocks = BlocksAPI(this.oauth, json)
        this.users = UsersAPI(this.oauth, json)
        this.mutes = MutesAPI(this.oauth, json)
        this.favorites = FavoritesAPI(this.oauth, json)
        this.lists = ListsAPI(this.oauth, json)
        this.savedSearches = SavedSearchesAPI(this.oauth, json)
        this.geo = GeoAPI(this.oauth, json)
        this.trends = TrendsAPI(this.oauth, json)
        this.application = ApplicationAPI(this.oauth, json)
        this.help = HelpAPI(this.oauth, json)
        this.stream = StreamAPI(this.oauth, json)
    }

}
