package com.twitter.meil_mitu.twitter4hk.aclog.data

import net.meilcli.hkjson.HKJson
import net.meilcli.hkjson.IJson
import net.meilcli.hkjson.objects.BooleanJson
import net.meilcli.hkjson.objects.IntJson
import net.meilcli.hkjson.objects.LongJson
import org.json.JSONObject

class AclogStats(json: JSONObject? = null) : IJson by HKJson(json) {

    val id by LongJson.json("id")//UserId
    val reactionsCount by IntJson.json("reactions_count")
    val isRegistered by BooleanJson.json("registered")

    init {
        clearJsonCache()
    }

    override fun toString(): String {
        return "AclogStats{Id=$id, ReactionsCount=$reactionsCount, IsRegistered=$isRegistered}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AclogStats) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return (id xor (id.ushr(32))).toInt()
    }

}
