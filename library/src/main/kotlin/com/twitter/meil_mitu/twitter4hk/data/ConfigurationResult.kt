package com.twitter.meil_mitu.twitter4hk.data

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getInt
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONArray
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject
import java.util.*

class ConfigurationResult {

    val charactersReservedPerMedia: Int
    val shortUrlLength: Int
    val shortUrlLengthHttps: Int
    val nonUsernamePaths: Array<String>

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        charactersReservedPerMedia = getInt(obj, "characters_reserved_per_media")
        shortUrlLength = getInt(obj, "short_url_length")
        shortUrlLengthHttps = getInt(obj, "short_url_length_https")
        if (obj.isNull("non_username_paths")) {
            throw Twitter4HKException("non_username_paths is null")
        } else {
            val ar = getJSONArray(obj, "non_username_paths")
            val size = ar.length()
            nonUsernamePaths = Array(size, { i -> getString(ar, i) })
        }
    }

    override fun toString(): String {
        return "Configuration{" + "CharactersReservedPerMedia=" + charactersReservedPerMedia + ", ShortUrlLength=" + shortUrlLength + ", ShortUrlLengthHttps=" + shortUrlLengthHttps + ", NonUsernamePaths=" + Arrays.toString(nonUsernamePaths) + '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ConfigurationResult) return false

        if (charactersReservedPerMedia != other.charactersReservedPerMedia) return false
        if (shortUrlLength != other.shortUrlLength) return false
        if (shortUrlLengthHttps != other.shortUrlLengthHttps) return false
        if (!Arrays.equals(nonUsernamePaths, other.nonUsernamePaths)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = charactersReservedPerMedia
        result = 31 * result + shortUrlLength
        result = 31 * result + shortUrlLengthHttps
        result = 31 * result + Arrays.hashCode(nonUsernamePaths)
        return result
    }
}
