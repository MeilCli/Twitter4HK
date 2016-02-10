package com.twitter.meil_mitu.twitter4hk.data

class SourceSupport(source: String) {

    var url: String? = null
    var client: String = source

    init {
        if (source[0] == '<') {
            run {
                val start = source.indexOf('"', 0)
                val end = source.indexOf('"', start + 1)
                if (start != -1 && end != -1) {
                    url = source.substring(start + 1, end)
                } else {
                    url = null
                }
            }
            run {
                val start = source.indexOf('>', 0)
                val end = source.indexOf('<', start + 1)
                if (start != -1 && end != -1) {
                    client = source.substring(start + 1, end)
                } else {
                    client = ""
                }
            }
        } else {
            url = null
            client = source
        }
    }

    override fun toString(): String {
        return "SourceSupport{Url='$url', Client='$client'}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SourceSupport) return false

        if (client != other.client) return false
        if (if (url != null) url != other.url else other.url != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = if (url != null) url!!.hashCode() else 0
        result = 31 * result + client.hashCode()
        return result
    }
}
