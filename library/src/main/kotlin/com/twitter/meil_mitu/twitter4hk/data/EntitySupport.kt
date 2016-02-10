package com.twitter.meil_mitu.twitter4hk.data

import android.text.SpannableStringBuilder

import com.twitter.meil_mitu.twitter4hk.util.EntityUtils

class EntitySupport(text: String, entities: Entities) {

    val spannableText: SpannableStringBuilder
    val plainText: String

    init {
        spannableText = EntityUtils.toLinkHtml(text, entities)
        plainText = EntityUtils.toLinkURL(text, entities)
    }

    override fun toString(): String {
        return "EntitySupport{SpannableText=$spannableText, PlainText='$plainText'}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is EntitySupport) return false

        if (plainText != other.plainText) return false
        if (spannableText != other.spannableText) return false

        return true
    }

    override fun hashCode(): Int {
        var result = spannableText.hashCode()
        result = 31 * result + plainText.hashCode()
        return result
    }
}
