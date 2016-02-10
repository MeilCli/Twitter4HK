package com.twitter.meil_mitu.twitter4hk.data

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getInt
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject

class TrendPlace {

    val country: String
    val countryCode: String
    val name: String
    val woeid: Int

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        country = getString(obj, "country", "")!!
        countryCode = getString(obj, "countryCode", "")!!
        name = getString(obj, "name", "")!!
        woeid = getInt(obj, "woeid")
    }

    override fun toString(): String {
        return "TrendPlace{Country='$country', CountryCode='$countryCode', Name='$name', Woeid=$woeid}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TrendPlace) return false

        if (woeid != other.woeid) return false
        if (country != other.country) return false
        if (countryCode != other.countryCode) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = country.hashCode()
        result = 31 * result + countryCode.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + woeid
        return result
    }
}
