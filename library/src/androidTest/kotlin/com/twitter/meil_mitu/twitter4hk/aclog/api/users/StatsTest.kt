package com.twitter.meil_mitu.twitter4hk.aclog.api.users

import com.twitter.meil_mitu.twitter4hk.aclog
import com.twitter.meil_mitu.twitter4hk.testTargetUserId
import com.twitter.meil_mitu.twitter4hk.testTargetUserScreenName
import junit.framework.TestCase

class StatsTest : TestCase(){

    @Throws(Exception::class)
    fun testStats(){
        val stats = aclog.users.stats().apply {
            this.authorization=true
            this.screenName=testTargetUserScreenName
        }
        val result = stats.call()
        assertEquals(result.id,testTargetUserId)
    }

}