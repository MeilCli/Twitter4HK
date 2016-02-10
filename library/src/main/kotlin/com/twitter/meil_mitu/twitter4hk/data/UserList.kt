package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getBoolean
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getDate
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getInt
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getLong
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject
import java.util.*

class UserList {

    val slug: String
    val name: String
    val mode: String
    val description: String
    val createdAt: Date?
    val subscriberCount: Int
    val memberCount: Int
    val id: Long
    val isFollowing: Boolean
    val user: User

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        slug = getString(obj, "slug")
        name = getString(obj, "name")
        createdAt = getDate(obj, "created_at")
        subscriberCount = getInt(obj, "subscriber_count")
        memberCount = getInt(obj, "member_count")
        mode = getString(obj, "mode")
        id = getLong(obj, "id")
        description = getString(obj, "description")
        isFollowing = getBoolean(obj, "following")
        if (obj.isNull("user")) {
            throw Twitter4HKException("user is null")
        } else {
            user = User(getJSONObject(obj, "user"))
        }
    }

    override fun toString(): String {
        return "UserList{Slug='$slug', Name='$name', Mode='$mode', Description='$description', CreatedAt=$createdAt, SubscriberCount=$subscriberCount, MemberCount=$memberCount, Id=$id, IsFollowing=$isFollowing, User=$user}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserList) return false

        if (id != other.id) return false
        if (user != other.user) return false

        return true
    }

    override fun hashCode(): Int {
        var result = (id xor (id.ushr(32))).toInt()
        result = 31 * result + user.hashCode()
        return result
    }

}
