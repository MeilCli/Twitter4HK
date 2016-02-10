package com.twitter.meil_mitu.twitter4hk.data

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getInt
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONArray
import org.json.JSONObject

open class Entity {

    val start: Int
    val end: Int

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        val ar = getJSONArray(obj, "indices")
        start = getInt(ar, 0)
        end = getInt(ar, 1)
    }

    override fun toString(): String {
        return "Entity{Start=$start, End=$end}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Entity) return false

        if (end != other.end) return false
        if (start != other.start) return false

        return true
    }

    override fun hashCode(): Int {
        var result = start
        result = 31 * result + end
        return result
    }

}
