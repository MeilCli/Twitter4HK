package com.twitter.meil_mitu.twitter4hk.api.friendships

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IRelationshipConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Update<TRelationship>(
        oauth: AbsOauth,
        protected val json: IRelationshipConverter<TRelationship>) :
        AbsPost<TRelationship>(oauth) {

    var screenName: String? by stringParam("screen_name")
    var userId: Long? by longParam("user_id")
    var device: Boolean? by booleanParam("device")
    var retweets: Boolean? by booleanParam("retweets")
    override val url = "https://api.twitter.com/1.1/friendships/update.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): TRelationship {
        return json.toRelationship(oauth.post(this))
    }
}
