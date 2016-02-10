package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONArray
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import org.json.JSONObject
import java.util.*

class SearchResult {

    val statuses: Array<Status>

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        if (obj.isNull("statuses")) {
            statuses = emptyArray()
        } else {
            val ar = getJSONArray(obj, "statuses")
            val size = ar.length()
            statuses = Array(size, { i -> Status(getJSONObject(ar, i)) })
        }
    }

    override fun toString(): String {
        return "SearchResult{" + "Statuses=" + Arrays.toString(statuses) + '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SearchResult) return false

        if (!Arrays.equals(statuses, other.statuses)) return false

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(statuses)
    }

}
