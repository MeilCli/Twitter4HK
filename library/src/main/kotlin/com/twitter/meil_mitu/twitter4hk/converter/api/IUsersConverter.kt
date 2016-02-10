package com.twitter.meil_mitu.twitter4hk.converter.api

import com.twitter.meil_mitu.twitter4hk.converter.IBannerConverter
import com.twitter.meil_mitu.twitter4hk.converter.IUserConverter

interface IUsersConverter<TBanner, TSuggestion, TSuggestionUser, TUser> :
        IBannerConverter<TBanner>,
        IUsersSuggestionsConverter<TSuggestion, TSuggestionUser, TUser>,
        IUserConverter<TUser> {
}