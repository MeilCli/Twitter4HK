package com.twitter.meil_mitu.twitter4hk.data

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getDate
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getLong
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject
import java.util.*

class DirectMessage {

    val createdAt: Date?
    val entities: Entities
    val id: Long
    val recipient: User
    val sender: User
    val text: String
    val entitySupport: EntitySupport

    @Throws(Twitter4HKException::class)
    constructor(obj: JSONObject) {
        createdAt = getDate(obj, "created_at")
        if (obj.isNull("entities")) {
            entities = Entities()
        } else {
            entities = Entities(getJSONObject(obj, "entities"))
        }
        id = getLong(obj, "id")
        if (obj.isNull("recipient")) {
            throw Twitter4HKException("recipient is null")
        } else {
            recipient = User(getJSONObject(obj, "recipient"))
        }
        if (obj.isNull("sender")) {
            throw Twitter4HKException("sender is null")
        } else {
            sender = User(getJSONObject(obj, "sender"))
        }
        text = getString(obj, "text")
        entitySupport = EntitySupport(text, entities)
    }

    override fun toString(): String {
        return "DirectMessage{CreatedAt=$createdAt, Entities=$entities, Id=$id, Recipient=$recipient, Sender=$sender, Text='$text', EntitySupport=$entitySupport}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DirectMessage) return false

        if (id != other.id) return false
        if (recipient != other.recipient) return false
        if (sender != other.sender) return false

        return true
    }

    override fun hashCode(): Int {
        var result = (id xor (id.ushr(32))).toInt()
        result = 31 * result + recipient.hashCode()
        result = 31 * result + sender.hashCode()
        return result
    }

}
