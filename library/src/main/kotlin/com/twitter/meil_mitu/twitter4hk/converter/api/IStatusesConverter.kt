package com.twitter.meil_mitu.twitter4hk.converter.api

import com.twitter.meil_mitu.twitter4hk.converter.ICursorIDsConverter
import com.twitter.meil_mitu.twitter4hk.converter.IOembedStatusConverter
import com.twitter.meil_mitu.twitter4hk.converter.IStatusConverter

interface IStatusesConverter<TCursorIDs, TOembedStatus, TStatus> :
        ICursorIDsConverter<TCursorIDs>,
        IOembedStatusConverter<TOembedStatus>,
        IStatusConverter<TStatus> {
}