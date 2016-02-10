package com.twitter.meil_mitu.twitter4hk.streaming

import java.io.InputStream

interface IStreamParam {

    fun onLine(line: String)

    val inputStream: InputStream
    var streamListener: IStreamListener?

}
