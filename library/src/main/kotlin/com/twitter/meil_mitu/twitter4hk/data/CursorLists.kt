package com.twitter.meil_mitu.twitter4hk.data

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONArray
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import org.json.JSONObject
import java.util.*

class CursorLists : Cursor {

    val lists: Array<UserList>

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) : super(obj) {
        if (obj.isNull("lists")) {
            lists = emptyArray()
        } else {
            val ar = getJSONArray(obj, "lists")
            val size = ar.length()
            lists = Array(size, { i -> UserList(getJSONObject(ar, i)) })
        }
    }

    override fun toString(): String {
        return super.toString() + " CursorLists{" + "Lists=" + Arrays.toString(lists) + '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CursorLists) return false
        if (!super.equals(other)) return false

        if (!Arrays.equals(lists, other.lists)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + Arrays.hashCode(lists)
        return result
    }
}
