package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject

class Place {

    val country: String
    val countryCode: String
    val fullName: String
    val id: String
    val name: String
    val placeType: String

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        country = getString(obj, "country")
        countryCode = getString(obj, "country_code")
        fullName = getString(obj, "full_name")
        id = getString(obj, "id")
        name = getString(obj, "name")
        placeType = getString(obj, "place_type")
    }

    override fun toString(): String {
        return "Place{Country='$country', CountryCode='$countryCode', FullName='$fullName', Id='$id', Name='$name', PlaceType='$placeType'}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Place) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

}
