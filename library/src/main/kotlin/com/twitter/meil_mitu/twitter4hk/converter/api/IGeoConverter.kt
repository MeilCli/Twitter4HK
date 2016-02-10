package com.twitter.meil_mitu.twitter4hk.converter.api

import com.twitter.meil_mitu.twitter4hk.converter.IPlaceConverter
import com.twitter.meil_mitu.twitter4hk.converter.IPlaceQueryConverter

interface IGeoConverter<TPlace, TPlaceQuery> :
        IPlaceConverter<TPlace>,
        IPlaceQueryConverter<TPlaceQuery> {
}