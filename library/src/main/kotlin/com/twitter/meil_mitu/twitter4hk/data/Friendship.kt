package com.twitter.meil_mitu.twitter4hk.data

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONArray
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getLong
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject
import java.util.*

class Friendship {

    val name: String
    val screenName: String
    val id: Long
    val connections: Array<String>

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        name = getString(obj, "name")
        screenName = getString(obj, "screen_name")
        id = getLong(obj, "id")
        if (obj.isNull("connections")) {
            throw Twitter4HKException("connections is null")
        }
        val ar = getJSONArray(obj, "connections")
        val size = ar.length()
        connections = Array(size, { i -> getString(ar, i) })
    }

    override fun toString(): String {
        return "Friendship{" + "Name='" + name + '\'' + ", ScreenName='" + screenName + '\'' + ", Id=" + id + ", Connections=" + Arrays.toString(connections) + '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Friendship) return false

        if (id != other.id) return false
        if (!Arrays.equals(connections, other.connections)) return false
        if (name != other.name) return false
        if (screenName != other.screenName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + screenName.hashCode()
        result = 31 * result + (id xor (id.ushr(32))).toInt()
        result = 31 * result + Arrays.hashCode(connections)
        return result
    }

}
