package com.twitter.meil_mitu.twitter4hk.api.lists

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.converter.IStatusConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Statuses<TStatus> : AbsGet<ResponseList<TStatus>> {

    protected val json: IStatusConverter<TStatus>
    var listId: Long? by longParam("list_id")
    var slug: String? by stringParam("slug")
    var ownerScreenName: String? by stringParam("owner_screen_name")
    var ownerId: Long? by longParam("owner_id")
    var sinceId: Long? by longParam("since_id")
    var maxId: Long? by longParam("max_id")
    var count: Int? by intParam("count")
    var includeEntities: Boolean? by booleanParam("include_entities")
    var includeRts: Boolean? by booleanParam("include_rts")
    override val url = "https://api.twitter.com/1.1/lists/statuses.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    constructor(
            oauth: AbsOauth,
            json: IStatusConverter<TStatus>,
            listId: Long) : super(oauth) {
        this.json = json
        this.listId = listId
    }

    constructor(
            oauth: AbsOauth,
            json: IStatusConverter<TStatus>,
            slug: String) : super(oauth) {
        this.json = json
        this.slug = slug
    }

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseList<TStatus> {
        return json.toStatusResponseList(oauth.get(this))
    }
}
