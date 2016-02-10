package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject

class Trend {

    val name: String
    val query: String

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        name = getString(obj, "name")
        query = getString(obj, "query")
    }

    override fun toString(): String {
        return "Trend{Name='$name', QueryBool='$query'}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Trend) return false

        if (name != other.name) return false
        if (query != other.query) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + query.hashCode()
        return result
    }

}
