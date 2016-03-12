package com.twitter.meil_mitu.twitter4hk.streaming.filter

interface IFilterStreamListener<TStatus> {

    fun onUnknown(line: String)

    fun onStatus(status: TStatus)

    fun onDeleteStatus(userId: Long, id: Long)

}
