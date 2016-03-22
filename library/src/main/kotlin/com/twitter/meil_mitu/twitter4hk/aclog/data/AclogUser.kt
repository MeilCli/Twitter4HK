package com.twitter.meil_mitu.twitter4hk.aclog.data


import net.meilcli.hkjson.HKJson
import net.meilcli.hkjson.IJson
import net.meilcli.hkjson.objects.IntJson
import net.meilcli.hkjson.objects.LongJson
import org.json.JSONObject

class AclogUser(json: JSONObject? = null) : IJson by HKJson(json) {

    val count by IntJson.json("count")
    val id by LongJson.json("id")

    init {
        clearJsonCache()
    }

    override fun toString(): String {
        return "AclogUser{Count=$count, Id=$id}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AclogUser) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return (id xor (id.ushr(32))).toInt()
    }

}
