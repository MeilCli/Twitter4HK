package com.twitter.meil_mitu.twitter4hk.converter.api

import com.twitter.meil_mitu.twitter4hk.converter.IMediaConverter

interface ITwitterConverter<TBanner, TConfigurationResult, TCursorIDs, TCursorLists,
        TCursorUsers, TDirectMessage, TFriendship, TIDs, TLanguage, TMedia, TOembedStatus, TPlace,
        TPlaceQuery, TPrivacyResult, TRateLimitResult, TRelationship, TSavedSearch, TSearchResult,
        TSetting, TStatus, TSuggestion, TSuggestionUser, TTosResult, TTrendPlace, TTrendResult,
        TUser, TUserList> :
        IAccountConverter<TSetting, TUser>,
        IApplicationConverter<TRateLimitResult>,
        IBlocksConverter<TCursorIDs, TCursorUsers, TUser>,
        IDirectMessagesConverter<TDirectMessage>,
        IFavoritesConverter<TStatus>,
        IFollowersConverter<TCursorIDs, TCursorUsers>,
        IFriendsConverter<TCursorIDs, TCursorUsers>,
        IFriendshipsConverter<TCursorIDs, TFriendship, TIDs, TRelationship, TUser>,
        IGeoConverter<TPlace, TPlaceQuery>,
        IHelpConverter<TConfigurationResult, TLanguage, TPrivacyResult, TTosResult>,
        IListsConverter<TCursorLists, TCursorUsers, TStatus, TUser, TUserList>,
        IMediaConverter<TMedia>,
        IMutesConverter<TCursorIDs, TCursorUsers, TUser>,
        IOauth2Converter,
        IOauthConverter,
        ISavedSearchesConverter<TSavedSearch>,
        ISearchConverter<TSearchResult>,
        IStatusesConverter<TCursorIDs, TOembedStatus, TStatus>,
        IStreamConverter<TDirectMessage, TStatus, TUser, TUserList>,
        ITrendsConverter<TTrendPlace, TTrendResult>,
        IUsersConverter<TBanner, TSuggestion, TSuggestionUser, TUser> {
}