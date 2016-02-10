package com.twitter.meil_mitu.twitter4hk.util

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

object JsonUtils {

    @Throws(Twitter4HKException::class)
    fun get(obj: JSONObject, name: String): Any {
        try {
            return obj.get(name)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun getJSONObject(obj: JSONObject, name: String): JSONObject {
        try {
            return obj.getJSONObject(name)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun getJSONArray(obj: JSONObject, name: String): JSONArray {
        try {
            return obj.getJSONArray(name)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun getDate(obj: JSONObject, name: String): Date {
        return Date(getString(obj, name))
    }

    @Throws(Twitter4HKException::class)
    fun getString(obj: JSONObject, name: String): String {
        if (obj.isNull(name)) {
            throw Twitter4HKException(name + " is null")
        }
        try {
            return obj.getString(name)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun getString(obj: JSONObject, name: String, def: String?): String? {
        if (obj.isNull(name)) {
            return def
        }
        try {
            return obj.getString(name)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun getBoolean(obj: JSONObject, name: String): Boolean {
        if (obj.isNull(name)) {
            throw Twitter4HKException(name + " is null")
        }
        try {
            return obj.getBoolean(name)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun getBoolean(obj: JSONObject, name: String, def: Boolean): Boolean {
        if (obj.isNull(name)) {
            return def
        }
        try {
            return obj.getBoolean(name)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun getInt(obj: JSONObject, name: String): Int {
        if (obj.isNull(name)) {
            throw Twitter4HKException(name + " is null")
        }
        try {
            return obj.getInt(name)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun getInt(obj: JSONObject, name: String, def: Int): Int {
        if (obj.isNull(name)) {
            return def
        }
        try {
            return obj.getInt(name)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun getLong(obj: JSONObject, name: String): Long {
        if (obj.isNull(name)) {
            throw Twitter4HKException(name + " is null")
        }
        try {
            return obj.getLong(name)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun getLong(obj: JSONObject, name: String, def: Long): Long {
        if (obj.isNull(name)) {
            return def
        }
        try {
            return obj.getLong(name)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun putInt(obj: JSONObject, name: String, value: Int) {
        try {
            obj.put(name, value)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun putLong(obj: JSONObject, name: String, value: Long) {
        try {
            obj.put(name, value)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun putString(obj: JSONObject, name: String, value: String) {
        try {
            obj.put(name, value)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun putJSONObject(obj: JSONObject, name: String, value: JSONObject) {
        try {
            obj.put(name, value)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun putJSONArray(obj: JSONObject, name: String, value: JSONArray) {
        try {
            obj.put(name, value)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun putNull(obj: JSONObject, name: String) {
        try {
            obj.put(name, null)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    fun keys(obj: JSONObject): Iterator<String> {
        return obj.keys()
    }

    @Throws(Twitter4HKException::class)
    fun toJSONObject(obj: String): JSONObject {
        try {
            return JSONObject(obj)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException("${e.message} object: $obj")
        }

    }

    @Throws(Twitter4HKException::class)
    fun getJSONObject(ar: JSONArray, index: Int): JSONObject {
        try {
            return ar.getJSONObject(index)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun getString(ar: JSONArray, index: Int): String {
        try {
            return ar.getString(index)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun getInt(ar: JSONArray, index: Int): Int {
        try {
            return ar.getInt(index)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun getLong(ar: JSONArray, index: Int): Long {
        try {
            return ar.getLong(index)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun putInt(ar: JSONArray, index: Int, value: Int) {
        try {
            ar.put(index, value)
        } catch (e: JSONException) {
            e.printStackTrace()
            throw Twitter4HKException(e.message)
        }

    }

    @Throws(Twitter4HKException::class)
    fun putJSONObject(ar: JSONArray, value: JSONObject) {
        ar.put(value)
    }
}
