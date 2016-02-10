package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getBoolean
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getLong
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject

open class RelationshipTarget {

    val id: Long
    val screenName: String
    val isFollowing: Boolean
    val isFollowedBy: Boolean
    val isFollowingReceived: Boolean
    val isFollowingRequested: Boolean

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        id = getLong(obj, "id")
        screenName = getString(obj, "screen_name")
        isFollowing = getBoolean(obj, "following")
        isFollowedBy = getBoolean(obj, "followed_by")
        isFollowingReceived = getBoolean(obj, "following_received")
        isFollowingRequested = getBoolean(obj, "following_requested")
    }

    override fun toString(): String {
        return "RelationshipTarget{Id=$id, ScreenName='$screenName', IsFollowing=$isFollowing, IsFollowedBy=$isFollowedBy, IsFollowingReceived=$isFollowingReceived, IsFollowingRequested=$isFollowingRequested}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RelationshipTarget) return false

        if (id != other.id) return false
        if (isFollowedBy != other.isFollowedBy) return false
        if (isFollowing != other.isFollowing) return false
        if (isFollowingReceived != other.isFollowingReceived) return false
        if (isFollowingRequested != other.isFollowingRequested) return false
        if (screenName != other.screenName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = (id xor (id.ushr(32))).toInt()
        result = 31 * result + screenName.hashCode()
        result = 31 * result + (if (isFollowing) 1 else 0)
        result = 31 * result + (if (isFollowedBy) 1 else 0)
        result = 31 * result + (if (isFollowingReceived) 1 else 0)
        result = 31 * result + (if (isFollowingRequested) 1 else 0)
        return result
    }

}
