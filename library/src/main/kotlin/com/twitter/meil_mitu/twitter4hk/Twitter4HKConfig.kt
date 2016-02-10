package com.twitter.meil_mitu.twitter4hk

class Twitter4HKConfig {

    var userAgent = "Mozilla/5.0 (Linux; U; Android 4.1.2; jp-jp; SonySOL21 Build/9.1.D.0.395)AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30"
    var isUseHttp2 = true
    var isUseSpdy = true
    var connectTimeoutSecond: Long = 20
    var readTimeoutSecond: Long = 40

    companion object {

        var isDebug: Boolean = false
    }
}
