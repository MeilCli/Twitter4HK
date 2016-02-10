package com.twitter.meil_mitu.twitter4hk.data

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONArray
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getLong
import org.json.JSONObject
import java.util.*

class CursorIDs : Cursor {

    val ids: LongArray

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) : super(obj) {
        if (obj.isNull("ids")) {
            ids = LongArray(0)
        } else {
            val ar = getJSONArray(obj, "ids")
            val size = ar.length()
            ids = LongArray(size)
            for (i in 0..size - 1) {
                ids[i] = getLong(ar, i)
            }
        }
    }

    override fun toString(): String {
        return super.toString() + " CursorIDs{" + "Ids=" + Arrays.toString(ids) + '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CursorIDs) return false
        if (!super.equals(other)) return false

        if (!Arrays.equals(ids, other.ids)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + Arrays.hashCode(ids)
        return result
    }

}
