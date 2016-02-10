package com.twitter.meil_mitu.twitter4hk.converter.api

import com.twitter.meil_mitu.twitter4hk.converter.IConfigurationResultConverter
import com.twitter.meil_mitu.twitter4hk.converter.ILanguageConverter
import com.twitter.meil_mitu.twitter4hk.converter.IPrivacyResultConverter
import com.twitter.meil_mitu.twitter4hk.converter.ITosResultConverter

interface IHelpConverter<TConfigurationResult, TLanguage, TPrivacyResult, TTosResult> :
        IConfigurationResultConverter<TConfigurationResult>,
        ILanguageConverter<TLanguage>,
        IPrivacyResultConverter<TPrivacyResult>,
        ITosResultConverter<TTosResult> {
}