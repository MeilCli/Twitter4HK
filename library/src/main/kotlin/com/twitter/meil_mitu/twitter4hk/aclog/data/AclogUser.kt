package com.twitter.meil_mitu.twitter4hk.aclog.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getInt
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getLong
import org.json.JSONObject

class AclogUser {

    val count: Int
    val id: Long

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        count = getInt(obj, "count")
        id = getLong(obj, "id")
    }

    override fun toString(): String {
        return "AclogUser{Count=$count, Id=$id}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AclogUser) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return (id xor (id.ushr(32))).toInt()
    }

}
