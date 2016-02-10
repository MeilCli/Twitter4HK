package com.twitter.meil_mitu.twitter4hk.converter.api

import com.twitter.meil_mitu.twitter4hk.converter.ISettingConverter
import com.twitter.meil_mitu.twitter4hk.converter.IUserConverter

interface IAccountConverter<TSetting, TUser> :
        ISettingConverter<TSetting>,
        IUserConverter<TUser> {
}