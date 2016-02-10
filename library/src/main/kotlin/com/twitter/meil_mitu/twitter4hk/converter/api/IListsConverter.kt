package com.twitter.meil_mitu.twitter4hk.converter.api

import com.twitter.meil_mitu.twitter4hk.converter.ICursorListsConverter
import com.twitter.meil_mitu.twitter4hk.converter.IStatusConverter

interface IListsConverter<TCursorLists, TCursorUsers, TStatus, TUser, TUserList> :
        IListsMembersConverter<TCursorUsers, TUser, TUserList>,
        IListsSubscribersConverter<TCursorUsers, TUser, TUserList>,
        ICursorListsConverter<TCursorLists>,
        IStatusConverter<TStatus> {
}