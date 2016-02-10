package com.twitter.meil_mitu.twitter4hk

import com.twitter.meil_mitu.twitter4hk.converter.TwitterConverter
import com.twitter.meil_mitu.twitter4hk.data.*

class Twitter4HK(oauth: AbsOauth) : Twitter<Banner, ConfigurationResult, CursorIDs,
        CursorLists, CursorUsers, DirectMessage, Friendship, IDs, Language, Media,
        OembedStatus, Place, PlaceQuery, PrivacyResult, RateLimitResult, Relationship,
        SavedSearch, SearchResult, Setting, Status, Suggestion, SuggestionUser, TosResult,
        TrendPlace, TrendResult, User, UserList>(
        oauth,
        TwitterConverter.getDefaultConverter()) {
}