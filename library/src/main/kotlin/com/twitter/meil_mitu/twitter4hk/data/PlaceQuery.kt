package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONArray
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import org.json.JSONObject
import java.util.*

class PlaceQuery {

    val places: Array<Place>

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        var _obj = obj
        if (_obj.isNull("result")) {
            throw Twitter4HKException("result is null")
        } else {
            _obj = getJSONObject(_obj, "result")
        }
        if (_obj.isNull("places")) {
            places = emptyArray()
        } else {
            val ar = getJSONArray(_obj, "places")
            val size = ar.length()
            places = Array(size, { i -> Place(getJSONObject(ar, i)) })
        }
    }

    override fun toString(): String {
        return "PlaceQuery{" + "Places=" + Arrays.toString(places) + '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PlaceQuery) return false

        if (!Arrays.equals(places, other.places)) return false

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(places)
    }

}
