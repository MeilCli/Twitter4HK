package com.twitter.meil_mitu.twitter4hk

import java.io.File
import java.util.*

abstract class AbsPost<T>(protected var oauth: AbsOauth) : AbsMethod<T>() {

    internal  val fileMap = HashMap<String, File>()
    val fileParam: Set<Map.Entry<String, File>> = fileMap.entries
    val fileSize = fileMap.size
    // pair first is startByte, second is endByte
    val separateFileMap = HashMap<String,Pair<Long,Long>>()

    override val method = "POST"

    init {
    }

    protected fun fileParam(key: String)
            = Param<AbsMethod<T>, File, File>(fileMap, key, { x -> x }, { x -> x })

}
