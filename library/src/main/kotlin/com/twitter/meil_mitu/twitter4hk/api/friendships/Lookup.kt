package com.twitter.meil_mitu.twitter4hk.api.friendships

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.converter.IFriendshipConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Lookup<TFriendship>(
        oauth: AbsOauth,
        protected val  json: IFriendshipConverter<TFriendship>) :
        AbsGet<ResponseList<TFriendship>>(oauth) {

    public var screenName: Array<String>? by stringArrayParam("screen_name")
    public var userId: LongArray? by longArrayParam("user_id")
    override val url = "https://api.twitter.com/1.1/friendships/lookup.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseList<TFriendship> {
        return json.toFriendshipResponseList(oauth.get(this))
    }
}
