package com.twitter.meil_mitu.twitter4hk.data

class UserEntitySupport(urlString: String, descriptionString: String, entities: UserEntities) {

    val url: EntitySupport
    val description: EntitySupport

    init {
        url = EntitySupport(urlString, entities.url)
        description = EntitySupport(descriptionString, entities.description)
    }

    override fun toString(): String {
        return "UserEntitySupport{Url=$url, Description=$description}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserEntitySupport) return false

        if (description != other.description) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = url.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }
}
