package com.twitter.meil_mitu.twitter4hk.data

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getLong
import org.json.JSONObject

open class Cursor {

    val previousCursor: Long
    val nextCursor: Long

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        previousCursor = getLong(obj, "previous_cursor")
        nextCursor = getLong(obj, "next_cursor")
    }

    override fun toString(): String {
        return "Cursor{PreviousCursor=$previousCursor, NextCursor=$nextCursor}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cursor) return false

        if (nextCursor != other.nextCursor) return false
        if (previousCursor != other.previousCursor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = (previousCursor xor (previousCursor.ushr(32))).toInt()
        result = 31 * result + (nextCursor xor (nextCursor.ushr(32))).toInt()
        return result
    }

}
