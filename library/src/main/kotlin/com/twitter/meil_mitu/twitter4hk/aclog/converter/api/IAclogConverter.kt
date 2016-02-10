package com.twitter.meil_mitu.twitter4hk.aclog.converter.api

interface IAclogConverter<TAclogStats, TAclogStatus, TAclogUser> :
        ITweetsConverter<TAclogStatus>,
        IUsersConverter<TAclogStats, TAclogUser> {
}