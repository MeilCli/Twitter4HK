package com.twitter.meil_mitu.twitter4hk.api.statuses

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IStatusConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Retweet<TStatus>(
        oauth: AbsOauth,
        protected val json: IStatusConverter<TStatus>,
        private val id: Long) : AbsPost<TStatus>(oauth) {

    /**
     * must not use in JsonConverter for User
     */
    var trimUser: Boolean? by booleanParam("trim_user")
    override val url = "https://api.twitter.com/1.1/statuses/retweet/$id.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): TStatus {
        return json.toStatus(oauth.post(this))
    }
}
