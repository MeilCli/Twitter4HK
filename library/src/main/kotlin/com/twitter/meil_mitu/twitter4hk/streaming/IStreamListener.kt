package com.twitter.meil_mitu.twitter4hk.streaming

interface IStreamListener {

    fun onException(e: Exception)

    fun onConnect()

    fun onDisConnect()

    fun onClose()

}
