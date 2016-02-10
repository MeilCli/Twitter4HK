package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject

class TosResult {

    val tos: String

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        tos = getString(obj, "tos")
    }

    override fun toString(): String {
        return "TosResult{Tos='$tos'}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TosResult) return false

        if (tos != other.tos) return false

        return true
    }

    override fun hashCode(): Int {
        return tos.hashCode()
    }
}
