package com.twitter.meil_mitu.twitter4hk.aclog

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.aclog.converter.api.AclogConverter
import com.twitter.meil_mitu.twitter4hk.aclog.data.AclogStats
import com.twitter.meil_mitu.twitter4hk.aclog.data.AclogStatus
import com.twitter.meil_mitu.twitter4hk.aclog.data.AclogUser

class Aclog4HK(oauth: AbsOauth) : Aclog<AclogStats, AclogStatus, AclogUser>(
        oauth, AclogConverter.getDefaultConverter()) {
}