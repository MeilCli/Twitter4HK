package com.twitter.meil_mitu.twitter4hk.aclog.api.tweets

import com.twitter.meil_mitu.twitter4hk.aclog
import com.twitter.meil_mitu.twitter4hk.testTargetUserScreenName
import junit.framework.TestCase

class UserTimelineTest : TestCase(){

    @Throws(Exception::class)
    fun testTimeLine(){
        val timeline = aclog.tweets.userTimeline().apply {
            this.authorization=true
            this.screenName=testTargetUserScreenName
            this.count=20
        }
        val result = timeline.call()
        assertEquals(result.size,20)
    }

}