package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.keys
import org.json.JSONObject
import java.util.*

class RateLimitResult {

    val resources: HashMap<String, HashMap<String, RateLimit>>

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        var _obj = obj
        if (_obj.isNull("resources")) {
            throw Twitter4HKException("resources is null")
        } else {
            _obj = getJSONObject(_obj, "resources")
        }
        resources = HashMap<String, HashMap<String, RateLimit>>()
        var api: String
        var apiObject: JSONObject
        var map: HashMap<String, RateLimit>
        var endpoints: Iterator<String>
        var endpoint: String
        val apis = keys(_obj)
        while (apis.hasNext()) {
            api = apis.next()
            apiObject = getJSONObject(_obj, api)
            map = HashMap<String, RateLimit>()
            endpoints = keys(apiObject)
            while (endpoints.hasNext()) {
                endpoint = endpoints.next()
                map.put(endpoint, RateLimit(getJSONObject(apiObject, endpoint)))
            }
            resources.put(api, map)
        }
    }

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is RateLimitResult) return false

        if (resources != other.resources) return false

        return true
    }

    override fun hashCode(): Int{
        return resources.hashCode()
    }


}
