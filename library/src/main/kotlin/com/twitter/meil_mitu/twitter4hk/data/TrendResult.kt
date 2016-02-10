package com.twitter.meil_mitu.twitter4hk.data

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getDate
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONArray
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import org.json.JSONObject
import java.util.*

class TrendResult {

    val asOf: Date?
    val createdAt: Date?
    val trends: Array<Trend>

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        asOf = getDate(obj, "as_of")
        createdAt = getDate(obj, "created_at")
        if (obj.isNull("trends")) {
            throw Twitter4HKException("trends is null")
        } else {
            val ar = getJSONArray(obj, "trends")
            val size = ar.length()
            trends = Array(size, { i -> Trend(getJSONObject(ar, i)) })
        }
    }

    override fun toString(): String {
        return "TrendResult{" + "AsOf=" + asOf + ", CreatedAt=" + createdAt + ", Trends=" + Arrays.toString(trends) + '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TrendResult) return false

        if (asOf != other.asOf) return false
        if (createdAt != other.createdAt) return false
        if (!Arrays.equals(trends, other.trends)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = asOf!!.hashCode()
        result = 31 * result + createdAt!!.hashCode()
        result = 31 * result + Arrays.hashCode(trends)
        return result
    }

}
