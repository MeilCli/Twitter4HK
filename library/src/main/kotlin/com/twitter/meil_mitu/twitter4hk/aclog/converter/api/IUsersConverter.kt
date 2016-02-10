package com.twitter.meil_mitu.twitter4hk.aclog.converter.api

import com.twitter.meil_mitu.twitter4hk.aclog.converter.IAclogStatsConverter
import com.twitter.meil_mitu.twitter4hk.aclog.converter.IAclogUserConverter

interface IUsersConverter<TAclogStats, TAclogUser> :
        IAclogStatsConverter<TAclogStats>,
        IAclogUserConverter<TAclogUser> {
}