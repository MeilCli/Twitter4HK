package com.twitter.meil_mitu.twitter4hk.converter.api

import com.twitter.meil_mitu.twitter4hk.converter.ICursorIDsConverter
import com.twitter.meil_mitu.twitter4hk.converter.ICursorUsersConverter
import com.twitter.meil_mitu.twitter4hk.converter.IUserConverter

interface IMutesUsersConverter<TCursorIDs, TCursorUsers, TUser> :
        ICursorIDsConverter<TCursorIDs>,
        ICursorUsersConverter<TCursorUsers>,
        IUserConverter<TUser> {
}