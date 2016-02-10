package com.twitter.meil_mitu.twitter4hk.data

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getDate
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getLong
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject
import java.util.*

class SavedSearch {

    val createdAt: Date?
    val id: Long
    val name: String
    val query: String

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        createdAt = getDate(obj, "created_at")
        id = getLong(obj, "id")
        name = getString(obj, "name")
        query = getString(obj, "query")
    }

    override fun toString(): String {
        return "SavedSearch{CreatedAt=$createdAt, Id=$id, Name='$name', QueryBool='$query'}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SavedSearch) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return (id xor (id.ushr(32))).toInt()
    }

}
