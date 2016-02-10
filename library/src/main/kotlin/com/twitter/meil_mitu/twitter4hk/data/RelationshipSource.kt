package com.twitter.meil_mitu.twitter4hk.data

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getBoolean
import org.json.JSONObject

class RelationshipSource : RelationshipTarget {

    val canDm: Boolean
    val isBlocking: Boolean
    val isBlockedBy: Boolean
    val isMuting: Boolean
    val wantRetweets: Boolean
    val isMarkedSpam: Boolean

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) : super(obj) {
        canDm = getBoolean(obj, "can_dm")
        isBlocking = getBoolean(obj, "blocking")
        isBlockedBy = getBoolean(obj, "blocked_by")
        isMuting = getBoolean(obj, "muting")
        wantRetweets = getBoolean(obj, "want_retweets")
        isMarkedSpam = getBoolean(obj, "marked_spam")
    }

    override fun toString(): String {
        return super.toString() + " RelationshipSource{" + "CanDm=" + canDm + ", IsBlocking=" + isBlocking + ", IsBlockedBy=" + isBlockedBy + ", IsMuting=" + isMuting + ", WantRetweets=" + wantRetweets + ", IsMarkedSpam=" + isMarkedSpam + '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RelationshipSource) return false
        if (!super.equals(other)) return false

        if (canDm != other.canDm) return false
        if (isBlockedBy != other.isBlockedBy) return false
        if (isBlocking != other.isBlocking) return false
        if (isMarkedSpam != other.isMarkedSpam) return false
        if (isMuting != other.isMuting) return false
        if (wantRetweets != other.wantRetweets) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (if (canDm) 1 else 0)
        result = 31 * result + (if (isBlocking) 1 else 0)
        result = 31 * result + (if (isBlockedBy) 1 else 0)
        result = 31 * result + (if (isMuting) 1 else 0)
        result = 31 * result + (if (wantRetweets) 1 else 0)
        result = 31 * result + (if (isMarkedSpam) 1 else 0)
        return result
    }
}
