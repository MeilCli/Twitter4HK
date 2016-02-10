package com.twitter.meil_mitu.twitter4hk.streaming.sample

open class SampleStreamListener<TStatus> : ISampleStreamListener<TStatus> {

    override fun onUnknown(line: String) {

    }

    override fun onStatus(status: TStatus) {

    }

    override fun onDeleteStatus(userId: Long, id: Long) {

    }

}
