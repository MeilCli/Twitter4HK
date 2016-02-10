package com.twitter.meil_mitu.twitter4hk.aclog.data

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getBoolean
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getInt
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getLong
import org.json.JSONObject

class AclogStats {

    val id: Long//UserId
    val reactionsCount: Int
    val isRegistered: Boolean

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        id = getLong(obj, "id")
        reactionsCount = getInt(obj, "reactions_count")
        isRegistered = getBoolean(obj, "registered")
    }

    override fun toString(): String {
        return "AclogStats{Id=$id, ReactionsCount=$reactionsCount, IsRegistered=$isRegistered}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AclogStats) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return (id xor (id.ushr(32))).toInt()
    }

}
