package com.twitter.meil_mitu.twitter4hk.data


import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import org.json.JSONObject

class Relationship {

    val source: RelationshipSource
    val target: RelationshipTarget

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        var _obj = obj
        if (_obj.isNull("relationship")) {
            throw Twitter4HKException("relationship is null")
        } else {
            _obj = getJSONObject(_obj, "relationship")
        }
        if (_obj.isNull("source")) {
            throw Twitter4HKException("source is null")
        }
        source = RelationshipSource(getJSONObject(_obj, "source"))
        if (_obj.isNull("target")) {
            throw Twitter4HKException("target is null")
        }
        target = RelationshipTarget(getJSONObject(_obj, "target"))
    }

    override fun toString(): String {
        return "Relationship{Source=$source, Target=$target}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Relationship) return false

        if (source != other.source) return false
        if (target != other.target) return false

        return true
    }

    override fun hashCode(): Int {
        var result = source.hashCode()
        result = 31 * result + target.hashCode()
        return result
    }

}
