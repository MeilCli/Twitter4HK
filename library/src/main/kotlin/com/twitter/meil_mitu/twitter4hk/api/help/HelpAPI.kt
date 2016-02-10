package com.twitter.meil_mitu.twitter4hk.api.help

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.api.IHelpConverter

class HelpAPI<TConfigurationResult, TLanguage, TPrivacyResult, TTosResult>(
        oauth: AbsOauth,
        protected val json: IHelpConverter
        <TConfigurationResult, TLanguage, TPrivacyResult, TTosResult>) :
        AbsAPI(oauth) {

    fun configuration(): Configuration<TConfigurationResult> {
        return Configuration(oauth, json)
    }

    fun languages(): Languages<TLanguage> {
        return Languages(oauth, json)
    }

    fun privacy(): Privacy<TPrivacyResult> {
        return Privacy(oauth, json)
    }

    fun tos(): Tos<TTosResult> {
        return Tos(oauth, json)
    }

}
