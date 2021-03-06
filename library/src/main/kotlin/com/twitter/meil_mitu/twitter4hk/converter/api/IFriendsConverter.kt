package com.twitter.meil_mitu.twitter4hk.converter.api

import com.twitter.meil_mitu.twitter4hk.converter.ICursorIDsConverter
import com.twitter.meil_mitu.twitter4hk.converter.ICursorUsersConverter

interface IFriendsConverter<TCursorIDs, TCursorUsers> :
        ICursorIDsConverter<TCursorIDs>,
        ICursorUsersConverter<TCursorUsers> {
}