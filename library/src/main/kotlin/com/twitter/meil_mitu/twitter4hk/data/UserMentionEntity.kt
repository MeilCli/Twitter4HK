package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getLong
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.putLong
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.putString
import org.json.JSONObject

class UserMentionEntity : Entity {

    val id: Long
    val screenName: String
    val name: String

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) : super(obj) {
        id = getLong(obj, "id")
        screenName = getString(obj, "screen_name")
        name = getString(obj, "name")
    }

    override fun toString(): String {
        return super.toString() + " UserMentionEntity{" + "Id=" + id + ", ScreenName='" + screenName + '\'' + ", Name='" + name + '\'' + '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserMentionEntity) return false
        if (!super.equals(other)) return false

        if (id != other.id) return false
        if (name != other.name) return false
        if (screenName != other.screenName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (id xor (id.ushr(32))).toInt()
        result = 31 * result + screenName.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }


}
