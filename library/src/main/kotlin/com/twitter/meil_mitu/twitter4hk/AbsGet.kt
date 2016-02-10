package com.twitter.meil_mitu.twitter4hk

abstract class AbsGet<T>(protected var oauth: AbsOauth) : AbsMethod<T>() {

    override val method = "GET"

    init {
    }
}
