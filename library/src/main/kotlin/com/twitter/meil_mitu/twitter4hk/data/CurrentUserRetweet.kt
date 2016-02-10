package com.twitter.meil_mitu.twitter4hk.data

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getLong
import org.json.JSONObject

class CurrentUserRetweet  {

    val id: Long

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        id = getLong(obj, "id")
    }

    override fun toString(): String {
        return "CurrentUserRetweet{Id=$id}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CurrentUserRetweet) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return (id xor (id.ushr(32))).toInt()
    }

}
