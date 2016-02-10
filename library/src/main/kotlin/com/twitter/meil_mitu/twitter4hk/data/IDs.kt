package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getLong
import org.json.JSONArray
import java.util.*

class IDs {

    val ids: LongArray

    @Throws(Twitter4HKException::class)
    constructor(ar: JSONArray) {
        val size = ar.length()
        ids = LongArray(size)
        for (i in 0..size - 1) {
            ids[i] = getLong(ar, i)
        }
    }

    override fun toString(): String {
        return "IDs{" + "Ids=" + Arrays.toString(ids) + '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is IDs) return false

        if (!Arrays.equals(ids, other.ids)) return false

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(ids)
    }

}
