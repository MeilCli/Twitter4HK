package com.twitter.meil_mitu.twitter4hk.api.help

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IHelpConverter

class HelpAPI<TConfigurationResult, TLanguage, TPrivacyResult, TTosResult>(
        oauth: AbsOauth,
        protected val json: IHelpConverter
        <TConfigurationResult, TLanguage, TPrivacyResult, TTosResult>) :
        AbsAPI(oauth) {

    fun configuration() = Configuration(oauth, json)

    fun languages() = Languages(oauth, json)

    fun privacy() = Privacy(oauth, json)

    fun tos() = Tos(oauth, json)

}
