package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONArray
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import org.json.JSONObject
import java.util.*

class Entities{

    var URL: Array<URLEntity>
    var Media: Array<MediaEntity>
    var Hashtag: Array<HashtagEntity>
    var Symbol: Array<SymbolEntity>
    var UserMention: Array<UserMentionEntity>

    constructor() {
        URL = emptyArray()
        Media = emptyArray()
        Hashtag = emptyArray()
        Symbol = emptyArray()
        UserMention = emptyArray()
    }

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        if (obj.isNull("urls")) {
            URL = emptyArray()
        } else {
            val ar = getJSONArray(obj, "urls")
            val size = ar.length()
            URL = Array(size, { i -> URLEntity(getJSONObject(ar, i)) })
        }
        if (obj.isNull("media")) {
            Media = emptyArray()
        } else {
            val ar = getJSONArray(obj, "media")
            val size = ar.length()
            Media = Array(size, { i -> MediaEntity(getJSONObject(ar, i)) })
        }
        if (obj.isNull("hashtags")) {
            Hashtag = emptyArray()
        } else {
            val ar = getJSONArray(obj, "hashtags")
            val size = ar.length()
            Hashtag = Array(size, { i -> HashtagEntity(getJSONObject(ar, i)) })
        }
        if (obj.isNull("symbols")) {
            Symbol = emptyArray()
        } else {
            val ar = getJSONArray(obj, "symbols")
            val size = ar.length()
            Symbol = Array(size, { i -> SymbolEntity(getJSONObject(ar, i)) })
        }
        if (obj.isNull("user_mentions")) {
            UserMention = emptyArray()
        } else {
            val ar = getJSONArray(obj, "user_mentions")
            val size = ar.length()
            UserMention = Array(size, { i -> UserMentionEntity(getJSONObject(ar, i)) })
        }
    }

    override fun toString(): String {
        return "Entities{" + "URL=" + Arrays.toString(URL) + ", Media=" + Arrays.toString(Media) + ", Hashtag=" + Arrays.toString(Hashtag) + ", Symbol=" + Arrays.toString(Symbol) + ", UserMention=" + Arrays.toString(UserMention) + '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Entities) return false

        if (!Arrays.equals(Hashtag, other.Hashtag)) return false
        if (!Arrays.equals(Media, other.Media)) return false
        if (!Arrays.equals(Symbol, other.Symbol)) return false
        if (!Arrays.equals(URL, other.URL)) return false
        if (!Arrays.equals(UserMention, other.UserMention)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(URL)
        result = 31 * result + Arrays.hashCode(Media)
        result = 31 * result + Arrays.hashCode(Hashtag)
        result = 31 * result + Arrays.hashCode(Symbol)
        result = 31 * result + Arrays.hashCode(UserMention)
        return result
    }

}
