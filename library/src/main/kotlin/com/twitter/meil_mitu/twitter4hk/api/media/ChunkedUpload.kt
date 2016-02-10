package com.twitter.meil_mitu.twitter4hk.api.media

import com.twitter.meil_mitu.twitter4hk.AbsOauth
import com.twitter.meil_mitu.twitter4hk.AbsPost
import com.twitter.meil_mitu.twitter4hk.OauthType
import com.twitter.meil_mitu.twitter4hk.converter.IMediaConverter
import com.twitter.meil_mitu.twitter4hk.data.Media
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import java.io.File

class ChunkedUpload<TMedia>(
        oauth: AbsOauth,
        protected val json: IMediaConverter<TMedia>,
        media: File,
        mediaType:String) : AbsPost<TMedia>(oauth) {

    public var media: File
    public var mediaType: String
    internal val initCommand = "INIT"
    internal val appendCommand = "APPEND"
    internal val finalizeCommand="FINALIZE"
    internal val maxSeparateByte = 5*1024*1024
    override val url = "https://upload.twitter.com/1.1/media/upload.json"
    override val allowOauthType = OauthType.oauth1
    override val isAuthorization = true

    init {
        this.media = media
        this.mediaType=mediaType
    }

    @Throws(Twitter4HKException::class)
    override fun call(): TMedia {
        paramMap["command"] = initCommand
        paramMap["media_type"] = mediaType
        paramMap["total_bytes"] = media.length().toString()
        val initResult = Media(json.toJSONObject(json.toString(oauth.post(this).body())))
        paramMap.remove("command")
        paramMap.remove("media_type")
        paramMap.remove("total_bytes")

        val totalByte = media.length()
        val separateCount = totalByte/maxSeparateByte + (if(totalByte%maxSeparateByte>0) 1 else 0)
        paramMap["command"] = appendCommand
        paramMap["media_id"] = initResult.mediaId.toString()
        fileMap["media"]=media
        for(i in 0..separateCount-1){
            paramMap["segment_index"] = i.toString()
            val startByte = i*maxSeparateByte
            val size = if((i+1)*maxSeparateByte<=totalByte)
                maxSeparateByte.toInt() else
                (totalByte-startByte).toInt()
            separateFileMap["media"] = Pair(startByte,startByte+size)
            json.toString(oauth.post(this).body())
        }
        paramMap.remove("command")
        paramMap.remove("media_id")
        fileMap.remove("media")
        paramMap.remove("segment_index")
        separateFileMap.remove("media")

        paramMap["command"] = finalizeCommand
        paramMap["media_id"] = initResult.mediaId.toString()
        val finalizeResult = json.toMedia(oauth.post(this))
        paramMap.remove("command")
        paramMap.remove("media_id")
        return finalizeResult
    }
}