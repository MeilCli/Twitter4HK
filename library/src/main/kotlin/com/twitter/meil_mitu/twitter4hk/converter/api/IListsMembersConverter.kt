package com.twitter.meil_mitu.twitter4hk.converter.api

import com.twitter.meil_mitu.twitter4hk.converter.ICursorUsersConverter
import com.twitter.meil_mitu.twitter4hk.converter.IUserConverter
import com.twitter.meil_mitu.twitter4hk.converter.IUserListConverter


interface IListsMembersConverter<TCursorUsers, TUser, TUserList> :
        ICursorUsersConverter<TCursorUsers>,
        IUserConverter<TUser>,
        IUserListConverter<TUserList> {
}