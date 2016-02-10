package com.twitter.meil_mitu.twitter4hk.streaming.filter

open class FilterStreamListener<TStatus> : IFilterStreamListener<TStatus> {


    override fun onUnknown(line: String) {

    }

    override fun onStatus(status: TStatus) {

    }

    override fun onDeleteStatus(userId: Long, id: Long) {

    }
}
