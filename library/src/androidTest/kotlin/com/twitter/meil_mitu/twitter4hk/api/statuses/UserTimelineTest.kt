package com.twitter.meil_mitu.twitter4hk.api.statuses

import com.twitter.meil_mitu.twitter4hk.testTargetUserScreenName
import com.twitter.meil_mitu.twitter4hk.twitter
import com.twitter.meil_mitu.twitter4hk.twitter2
import junit.framework.TestCase

class UserTimelineTest : TestCase(){

    @Throws(Exception::class)
    fun testTimeLine(){
        val timeline = twitter.statuses.userTimeline().apply {
            this.screenName=testTargetUserScreenName
            this.count = 20
            this.includeRts=true
        }
        val result = timeline.call()
        assertEquals(result.size,20)
        assertEquals(result[0].user!!.screenName, testTargetUserScreenName)
    }

    @Throws(Exception::class)
    fun testTimeLine2(){
        val timeline = twitter2.statuses.userTimeline().apply {
            this.screenName=testTargetUserScreenName
            this.count = 20
            this.includeRts=true
        }
        val result = timeline.call()
        assertEquals(result.size,20)
        assertEquals(result[0].user!!.screenName, testTargetUserScreenName)
    }

}