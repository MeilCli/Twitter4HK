package com.twitter.meil_mitu.twitter4hk.converter.api

import com.twitter.meil_mitu.twitter4hk.converter.ITrendPlaceConverter
import com.twitter.meil_mitu.twitter4hk.converter.ITrendResultConverter

interface ITrendsConverter<TTrendPlace, TTrendResult> :
        ITrendPlaceConverter<TTrendPlace>,
        ITrendResultConverter<TTrendResult> {
}