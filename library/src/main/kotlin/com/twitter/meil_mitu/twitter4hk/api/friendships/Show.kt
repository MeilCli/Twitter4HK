package com.twitter.meil_mitu.twitter4hk.api.friendships

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.converter.IRelationshipConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Show<TRelationship>(
        oauth: AbsOauth,
        protected val json: IRelationshipConverter<TRelationship>) :
        AbsGet<ResponseData<TRelationship>>(oauth) {

    public var sourceId: Long? by longParam("source_id")
    public var sourceScreenName: String? by stringParam("source_screen_name")
    public var targetId: Long? by longParam("target_id")
    public var targetScreenName: String? by stringParam("target_screen_name")
    override val url = "https://api.twitter.com/1.1/friendships/show.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseData<TRelationship> {
        return json.toRelationshipResponseData(oauth.get(this))
    }
}
