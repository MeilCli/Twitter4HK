package com.twitter.meil_mitu.twitter4hk.api.help

import com.twitter.meil_mitu.twitter4hk.AbsGet
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.converter.ILanguageConverter
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

class Languages<TLanguage>(
        oauth: AbsOauth,
        protected val json: ILanguageConverter<TLanguage>) :
        AbsGet<ResponseList<TLanguage>>(oauth) {

    override val url = "https://api.twitter.com/1.1/help/languages.json"
    override val allowOauthType = OauthType.oauth1 or OauthType.oauth2
    override val isAuthorization = true

    @Throws(Twitter4HKException::class)
    override fun call(): ResponseList<TLanguage> {
        return json.toLanguageResponseList(oauth.get(this))
    }
}
