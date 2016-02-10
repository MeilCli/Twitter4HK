package com.twitter.meil_mitu.twitter4hk

import com.twitter.meil_mitu.twitter4hk.data.RateLimit
import java.util.*

class ResponseList<T>(val rateLimit: RateLimit) : ArrayList<T>()
