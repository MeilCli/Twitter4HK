package com.twitter.meil_mitu.twitter4hk.data

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getInt
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getLong
import org.json.JSONObject

class RateLimit {

    val limit: Int
    val remaining: Int
    val reset: Long

    constructor(res: Response) {
        limit = Integer.parseInt(res.header("X-Rate-Limit-Limit", "0"))
        remaining = Integer.parseInt(res.header("X-Rate-Limit-Remaining", "0"))
        reset = java.lang.Long.parseLong(res.header("X-Rate-Limit-Reset", "-1"))
    }

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        limit = getInt(obj, "limit")
        remaining = getInt(obj, "remaining")
        reset = getLong(obj, "reset")
    }

    override fun toString(): String {
        return "RateLimit{Limit=$limit, Remaining=$remaining, Reset=$reset}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RateLimit) return false

        if (limit != other.limit) return false
        if (remaining != other.remaining) return false
        if (reset != other.reset) return false

        return true
    }

    override fun hashCode(): Int {
        var result = limit
        result = 31 * result + remaining
        result = 31 * result + (reset xor (reset.ushr(32))).toInt()
        return result
    }

}
