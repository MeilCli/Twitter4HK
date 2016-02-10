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

class User {

    val createdAt: Date?
    val description: String
    val entities: UserEntities
    val favouritesCount: Int
    val followersCount: Int
    val friendsCount: Int
    val listedCount: Int
    val statusesCount: Int
    val isFollowRequestSent: Boolean
    val isFollowing: Boolean
    val isProtected: Boolean
    val isMuting: Boolean
    val isVerified: Boolean
    val id: Long
    val lang: String?
    val location: String
    val name: String
    val profileBackgroundImageUrl: String?
    val profileBannerUrl: String?
    val profileImageUrl: String
    val screenName: String
    val url: String
    val status: Status?
    val userEntitySupport: UserEntitySupport

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        createdAt = getDate(obj, "created_at")
        description = getString(obj, "description", "")!!
        if (obj.isNull("entities")) {
            entities = UserEntities()
        } else {
            entities = UserEntities(getJSONObject(obj, "entities"))
        }
        favouritesCount = getInt(obj, "favourites_count")
        isFollowRequestSent = getBoolean(obj, "follow_request_sent", false)
        isFollowing = getBoolean(obj, "following", false)
        followersCount = getInt(obj, "followers_count")
        friendsCount = getInt(obj, "friends_count")
        id = getLong(obj, "id")
        lang = getString(obj, "lang", null)
        listedCount = getInt(obj, "listed_count")
        location = getString(obj, "location", "")!!
        isMuting = getBoolean(obj, "muting", false)
        name = getString(obj, "name")
        profileBackgroundImageUrl = getString(obj, "profile_background_image_url", null)
        profileBannerUrl = getString(obj, "profile_banner_url", null)
        profileImageUrl = getString(obj, "profile_image_url")
        isProtected = getBoolean(obj, "protected")
        screenName = getString(obj, "screen_name")
        if (obj.isNull("status")) {
            status = null
        } else {
            status = Status(getJSONObject(obj, "status"))
        }
        statusesCount = getInt(obj, "statuses_count")
        url = getString(obj, "url", "")!!
        isVerified = getBoolean(obj, "verified")
        userEntitySupport = UserEntitySupport(url, description, entities)
    }


    override fun toString(): String {
        return "User{CreatedAt=$createdAt, Description='$description', Entities=$entities, FavouritesCount=$favouritesCount, FollowersCount=$followersCount, FriendsCount=$friendsCount, ListedCount=$listedCount, StatusesCount=$statusesCount, IsFollowRequestSent=$isFollowRequestSent, IsFollowing=$isFollowing, IsProtected=$isProtected, IsMuting=$isMuting, IsVerified=$isVerified, Id=$id, Lang='$lang', Location='$location', Name='$name', ProfileBackgroundImageUrl='$profileBackgroundImageUrl', ProfileBannerUrl='$profileBannerUrl', ProfileImageUrl='$profileImageUrl', ScreenName='$screenName', Url='$url', Status=$status, UserEntitySupport=$userEntitySupport}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return (id xor (id.ushr(32))).toInt()
    }

}
