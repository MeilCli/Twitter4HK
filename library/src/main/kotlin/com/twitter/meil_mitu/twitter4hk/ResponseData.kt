package com.twitter.meil_mitu.twitter4hk

import com.twitter.meil_mitu.twitter4hk.data.RateLimit

class ResponseData<T>(val response: T, val rateLimit: RateLimit)
