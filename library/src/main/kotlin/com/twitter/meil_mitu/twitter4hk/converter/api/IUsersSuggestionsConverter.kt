package com.twitter.meil_mitu.twitter4hk.converter.api

import com.twitter.meil_mitu.twitter4hk.converter.ISuggestionConverter
import com.twitter.meil_mitu.twitter4hk.converter.ISuggestionUserConverter
import com.twitter.meil_mitu.twitter4hk.converter.IUserConverter

interface IUsersSuggestionsConverter<TSuggestion, TSuggestionUser, TUser> :
        ISuggestionConverter<TSuggestion>,
        ISuggestionUserConverter<TSuggestionUser>,
        IUserConverter<TUser> {
}