package com.twitter.meil_mitu.twitter4hk.converter.api

import com.twitter.meil_mitu.twitter4hk.converter.IDirectMessageConverter
import com.twitter.meil_mitu.twitter4hk.converter.IStatusConverter
import com.twitter.meil_mitu.twitter4hk.converter.IUserConverter
import com.twitter.meil_mitu.twitter4hk.converter.IUserListConverter

interface IUserStreamConverter<TDirectMessage, TStatus, TUser, TUserList> :
        IDirectMessageConverter<TDirectMessage>,
        IStatusConverter<TStatus>,
        IUserConverter<TUser>,
        IUserListConverter<TUserList> {
}