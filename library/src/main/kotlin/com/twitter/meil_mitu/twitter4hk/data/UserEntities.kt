package com.twitter.meil_mitu.twitter4hk.data

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import org.json.JSONObject

class UserEntities {

    val url: Entities
    val description: Entities

    constructor() {
        url = Entities()
        description = Entities()
    }

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        if (obj.isNull("url")) {
            url = Entities()
        } else {
            url = Entities(getJSONObject(obj, "url"))
        }
        if (obj.isNull("description")) {
            description = Entities()
        } else {
            description = Entities(getJSONObject(obj, "description"))
        }
    }

    override fun toString(): String {
        return "UserEntities{Url=$url, Description=$description}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserEntities) return false

        if (description != other.description) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = url.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }


}
