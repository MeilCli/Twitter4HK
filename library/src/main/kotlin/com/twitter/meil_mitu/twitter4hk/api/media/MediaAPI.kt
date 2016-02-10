package com.twitter.meil_mitu.twitter4hk.api.media

import com.twitter.meil_mitu.twitter4hk.AbsAPI
import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.converter.IMediaConverter

import java.io.File

class MediaAPI<TMedia>(
        oauth: AbsOauth,
        protected val json: IMediaConverter<TMedia>) : AbsAPI(oauth) {

    fun upload(media: File): Upload<TMedia> {
        return Upload(oauth, json, media)
    }

    fun chunkedUpload(media:File,mediaType:String):ChunkedUpload<TMedia>{
        return ChunkedUpload(oauth,json,media,mediaType)
    }
}
