package com.twitter.meil_mitu.twitter4hk.aclog

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth

abstract class AbsAclogGet<T>(oauth: AbsOauth) : AbsGet<T>(oauth) {

    public var host = "http://aclog.koba789.com"

}
