package com.twitter.meil_mitu.twitter4hk.converter.api

interface IStreamConverter<TDirectMessage, TStatus, TUser, TUserList> :
        IFilterStreamConverter<TStatus>,
        ISampleStreamConverter<TStatus>,
        IUserStreamConverter<TDirectMessage, TStatus, TUser, TUserList> {
}