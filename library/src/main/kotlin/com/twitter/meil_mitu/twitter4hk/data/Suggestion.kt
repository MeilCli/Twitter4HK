package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getInt
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject

open class Suggestion {

    val name: String
    val slug: String
    val size: Int

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        name = getString(obj, "name")
        slug = getString(obj, "slug")
        size = getInt(obj, "size")
    }


    override fun toString(): String {
        return "Suggestion{Name='$name', Slug='$slug', Size=$size}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Suggestion) return false

        if (size != other.size) return false
        if (name != other.name) return false
        if (slug != other.slug) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + slug.hashCode()
        result = 31 * result + size
        return result
    }

}
