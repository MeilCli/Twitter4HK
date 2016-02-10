package com.twitter.meil_mitu.twitter4hk.data

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject

class Language {

    val code: String
    val name: String

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        code = getString(obj, "code")
        name = getString(obj, "name")
    }

    override fun toString(): String {
        return "Language{Code='$code', Name='$name'}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Language) return false

        if (code != other.code) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = code.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

}
