package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONArray
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import org.json.JSONObject
import java.util.*

class CursorUsers : Cursor {

    val users: Array<User>

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) : super(obj) {
        if (obj.isNull("users")) {
            users = emptyArray()
        } else {
            val ar = getJSONArray(obj, "users")
            val size = ar.length()
            users = Array(size, { i -> User(getJSONObject(ar, i)) })
        }
    }

    override fun toString(): String {
        return super.toString() + " CursorUsers{" + "Users=" + Arrays.toString(users) + '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CursorUsers) return false
        if (!super.equals(other)) return false

        if (!Arrays.equals(users, other.users)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + Arrays.hashCode(users)
        return result
    }

}
