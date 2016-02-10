package com.twitter.meil_mitu.twitter4hk.data

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.putString
import org.json.JSONObject

class HashtagEntity : Entity {

    val text: String

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) : super(obj) {
        text = getString(obj, "text")
    }

    override fun toString(): String {
        return super.toString() + " HashtagEntity{" + "Text='" + text + '\'' + '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is HashtagEntity) return false
        if (!super.equals(other)) return false

        if (text != other.text) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + text.hashCode()
        return result
    }
}
