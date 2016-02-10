package com.twitter.meil_mitu.twitter4hk.converter

import com.squareup.okhttp.Response
import com.squareup.okhttp.ResponseBody
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.Twitter4HKConfig
import com.twitter.meil_mitu.twitter4hk.converter.api.ITwitterConverter
import com.twitter.meil_mitu.twitter4hk.data.*
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class TwitterConverter : ITwitterConverter<Banner, ConfigurationResult, CursorIDs, CursorLists,
        CursorUsers, DirectMessage, Friendship, IDs, Language, Media, OembedStatus, Place,
        PlaceQuery, PrivacyResult, RateLimitResult, Relationship, SavedSearch, SearchResult,
        Setting, Status, Suggestion, SuggestionUser, TosResult, TrendPlace, TrendResult,
        User, UserList> {

    @Throws(Twitter4HKException::class)
    override fun toJSONObject(res: String): JSONObject {
        try {
            return JSONObject(res)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    override fun toJSONArray(res: String): JSONArray {
        try {
            return JSONArray(res)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    override fun toString(body: ResponseBody): String {
        try {
            return body.string()
        } catch (e: IOException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    override fun toOauth2Token(res: Response): Oauth2Token {
        return Oauth2Token(toJSONObject(toString(res.body())))
    }

    @Throws(Twitter4HKException::class)
    override fun toOauth2Token(res: Response, tokenType: String): Oauth2Token {
        return Oauth2Token(toJSONObject(toString(res.body())), tokenType)
    }

    @Throws(Twitter4HKException::class)
    override fun toOauthRequestToken(res: Response): OauthRequestToken {
        return OauthRequestToken(toString(res.body()))
    }

    @Throws(Twitter4HKException::class)
    override fun toOauthToken(res: Response): OauthToken {
        return OauthToken(toString(res.body()))
    }

    fun toRateLimit(res: Response): RateLimit {
        return RateLimit(res)
    }

    @Throws(Twitter4HKException::class)
    override fun toRateLimitResultResponseData(res: Response): ResponseData<RateLimitResult> {
        return ResponseData(RateLimitResult(toJSONObject(toString(res.body()))), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toStatus(res: Response): Status {
        return Status(toJSONObject(toString(res.body())))
    }

    @Throws(Twitter4HKException::class)
    override fun toStatus(obj: JSONObject): Status {
        return Status(obj)
    }

    @Throws(Twitter4HKException::class)
    override fun toStatusResponseData(res: Response): ResponseData<Status> {
        return ResponseData(toStatus(res), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toStatusResponseList(res: Response): ResponseList<Status> {
        val ar = toJSONArray(toString(res.body()))
        val size = ar.length()
        val list = ResponseList<Status>(toRateLimit(res))
        for (i in 0..size - 1) {
            try {
                list.add(Status(getJSONObject(ar, i)))
            } catch (e: Twitter4HKException) {
                e.printStackTrace()
                if (Twitter4HKConfig.isDebug) {
                    throw e
                }
            }

        }
        return list
    }

    @Throws(Twitter4HKException::class)
    override fun toOembedStatusResponseData(res: Response): ResponseData<OembedStatus> {
        return ResponseData(OembedStatus(toJSONObject(toString(res.body()))), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toCursorIDsResponseData(res: Response): ResponseData<CursorIDs> {
        return ResponseData(CursorIDs(toJSONObject(toString(res.body()))), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toMedia(res: Response): Media {
        return Media(toJSONObject(toString(res.body())))
    }

    @Throws(Twitter4HKException::class)
    override fun toSearchResultResponseData(res: Response): ResponseData<SearchResult> {
        return ResponseData(SearchResult(toJSONObject(toString(res.body()))), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toDirectMessage(res: Response): DirectMessage {
        return DirectMessage(toJSONObject(toString(res.body())))
    }

    @Throws(Twitter4HKException::class)
    override fun toDirectMessage(obj: JSONObject): DirectMessage {
        return DirectMessage(obj)
    }

    @Throws(Twitter4HKException::class)
    override fun toDirectMessageResponseData(res: Response): ResponseData<DirectMessage> {
        return ResponseData(DirectMessage(toJSONObject(toString(res.body()))), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toDirectMessageResponseList(res: Response): ResponseList<DirectMessage> {
        val ar = toJSONArray(toString(res.body()))
        val size = ar.length()
        val list = ResponseList<DirectMessage>(toRateLimit(res))
        for (i in 0..size - 1) {
            try {
                list.add(DirectMessage(getJSONObject(ar, i)))
            } catch (e: Twitter4HKException) {
                e.printStackTrace()
                if (Twitter4HKConfig.isDebug) {
                    throw e
                }
            }

        }
        return list
    }

    @Throws(Twitter4HKException::class)
    override fun toIDsResponseData(res: Response): ResponseData<IDs> {
        return ResponseData(IDs(toJSONArray(toString(res.body()))), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toUser(res: Response): User {
        return User(toJSONObject(toString(res.body())))
    }

    @Throws(Twitter4HKException::class)
    override fun toUser(obj: JSONObject): User {
        return User(obj)
    }

    @Throws(Twitter4HKException::class)
    override fun toUserResponseData(res: Response): ResponseData<User> {
        return ResponseData(toUser(res), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toUserResponseList(res: Response): ResponseList<User> {
        val ar = toJSONArray(toString(res.body()))
        val size = ar.length()
        val list = ResponseList<User>(toRateLimit(res))
        for (i in 0..size - 1) {
            try {
                list.add(User(getJSONObject(ar, i)))
            } catch (e: Twitter4HKException) {
                e.printStackTrace()
                if (Twitter4HKConfig.isDebug) {
                    throw e
                }
            }

        }
        return list
    }

    @Throws(Twitter4HKException::class)
    override fun toRelationship(res: Response): Relationship {
        return Relationship(toJSONObject(toString(res.body())))
    }

    @Throws(Twitter4HKException::class)
    override fun toRelationshipResponseData(res: Response): ResponseData<Relationship> {
        return ResponseData(toRelationship(res), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toFriendshipResponseList(res: Response): ResponseList<Friendship> {
        val ar = toJSONArray(toString(res.body()))
        val size = ar.length()
        val list = ResponseList<Friendship>(toRateLimit(res))
        for (i in 0..size - 1) {
            try {
                list.add(Friendship(getJSONObject(ar, i)))
            } catch (e: Twitter4HKException) {
                e.printStackTrace()
                if (Twitter4HKConfig.isDebug) {
                    throw e
                }
            }

        }
        return list
    }

    @Throws(Twitter4HKException::class)
    override fun toCursorUsersResponseData(res: Response): ResponseData<CursorUsers> {
        return ResponseData(CursorUsers(toJSONObject(toString(res.body()))), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toSetting(res: Response): Setting {
        return Setting(toJSONObject(toString(res.body())))
    }

    @Throws(Twitter4HKException::class)
    override fun toSettingResponseData(res: Response): ResponseData<Setting> {
        return ResponseData(toSetting(res), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toBannerResponseData(res: Response): ResponseData<Banner> {
        return ResponseData(Banner(toJSONObject(toString(res.body()))), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toSuggestionResponseList(res: Response): ResponseList<Suggestion> {
        val ar = toJSONArray(toString(res.body()))
        val size = ar.length()
        val list = ResponseList<Suggestion>(toRateLimit(res))
        for (i in 0..size - 1) {
            try {
                list.add(Suggestion(getJSONObject(ar, i)))
            } catch (e: Twitter4HKException) {
                e.printStackTrace()
                if (Twitter4HKConfig.isDebug) {
                    throw e
                }
            }

        }
        return list
    }

    @Throws(Twitter4HKException::class)
    override fun toSuggestionUserResponseData(res: Response): ResponseData<SuggestionUser> {
        return ResponseData(SuggestionUser(toJSONObject(toString(res.body()))), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toUserList(res: Response): UserList {
        return UserList(toJSONObject(toString(res.body())))
    }

    @Throws(Twitter4HKException::class)
    override fun toUserList(obj: JSONObject): UserList {
        return UserList(obj)
    }

    @Throws(Twitter4HKException::class)
    override fun toUserListResponseData(res: Response): ResponseData<UserList> {
        return ResponseData(toUserList(res), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toUserListResponseList(res: Response): ResponseList<UserList> {
        val ar = toJSONArray(toString(res.body()))
        val size = ar.length()
        val list = ResponseList<UserList>(toRateLimit(res))
        for (i in 0..size - 1) {
            try {
                list.add(UserList(getJSONObject(ar, i)))
            } catch (e: Twitter4HKException) {
                e.printStackTrace()
                if (Twitter4HKConfig.isDebug) {
                    throw e
                }
            }

        }
        return list
    }

    @Throws(Twitter4HKException::class)
    override fun toCursorListsResponseData(res: Response): ResponseData<CursorLists> {
        return ResponseData(CursorLists(toJSONObject(toString(res.body()))), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toSavedSearch(res: Response): SavedSearch {
        return SavedSearch(toJSONObject(toString(res.body())))
    }

    @Throws(Twitter4HKException::class)
    override fun toSavedSearchResponseData(res: Response): ResponseData<SavedSearch> {
        return ResponseData(toSavedSearch(res), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toSavedSearchResponseList(res: Response): ResponseList<SavedSearch> {
        val ar = toJSONArray(toString(res.body()))
        val size = ar.length()
        val list = ResponseList<SavedSearch>(toRateLimit(res))
        for (i in 0..size - 1) {
            try {
                list.add(SavedSearch(getJSONObject(ar, i)))
            } catch (e: Twitter4HKException) {
                e.printStackTrace()
                if (Twitter4HKConfig.isDebug) {
                    throw e
                }
            }

        }
        return list
    }

    @Throws(Twitter4HKException::class)
    override fun toPlaceResponseData(res: Response): ResponseData<Place> {
        return ResponseData(Place(toJSONObject(toString(res.body()))), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toPlaceQueryResponseData(res: Response): ResponseData<PlaceQuery> {
        return ResponseData(PlaceQuery(toJSONObject(toString(res.body()))), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toTrendResultResponseData(res: Response): ResponseData<TrendResult> {
        return ResponseData(TrendResult(toJSONObject(toString(res.body()))), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toTrendPlaceResponseList(res: Response): ResponseList<TrendPlace> {
        val ar = toJSONArray(toString(res.body()))
        val size = ar.length()
        val list = ResponseList<TrendPlace>(toRateLimit(res))
        for (i in 0..size - 1) {
            try {
                list.add(TrendPlace(getJSONObject(ar, i)))
            } catch (e: Twitter4HKException) {
                e.printStackTrace()
                if (Twitter4HKConfig.isDebug) {
                    throw e
                }
            }

        }
        return list
    }

    @Throws(Twitter4HKException::class)
    override fun toConfigurationResultResponseData(res: Response): ResponseData<ConfigurationResult> {
        return ResponseData(ConfigurationResult(toJSONObject(toString(res.body()))), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toLanguageResponseList(res: Response): ResponseList<Language> {
        val ar = toJSONArray(toString(res.body()))
        val size = ar.length()
        val list = ResponseList<Language>(toRateLimit(res))
        for (i in 0..size - 1) {
            try {
                list.add(Language(getJSONObject(ar, i)))
            } catch (e: Twitter4HKException) {
                e.printStackTrace()
                if (Twitter4HKConfig.isDebug) {
                    throw e
                }
            }

        }
        return list
    }

    @Throws(Twitter4HKException::class)
    override fun toPrivacyResultResponseData(res: Response): ResponseData<PrivacyResult> {
        return ResponseData(PrivacyResult(toJSONObject(toString(res.body()))), toRateLimit(res))
    }

    @Throws(Twitter4HKException::class)
    override fun toTosResultResponseData(res: Response): ResponseData<TosResult> {
        return ResponseData(TosResult(toJSONObject(toString(res.body()))), toRateLimit(res))
    }

    companion object {

        private var _defaultConverter: TwitterConverter? = null

        fun getDefaultConverter(): TwitterConverter {
            if (_defaultConverter == null) {
                _defaultConverter = TwitterConverter()
            }
            return _defaultConverter!!
        }
    }
}
