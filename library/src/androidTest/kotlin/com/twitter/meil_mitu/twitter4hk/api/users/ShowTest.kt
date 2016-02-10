package com.twitter.meil_mitu.twitter4hk.api.users

import com.twitter.meil_mitu.twitter4hk.testTargetUserScreenName
import com.twitter.meil_mitu.twitter4hk.twitter
import com.twitter.meil_mitu.twitter4hk.twitter2
import junit.framework.TestCase

class ShowTest : TestCase(){

    @Throws(Exception::class)
    fun testShow(){
        val show =twitter.users.show(testTargetUserScreenName).apply {
            this.includeEntities=true
        }
        val result = show.call()
        assertEquals(result.response.screenName,testTargetUserScreenName)
    }

    @Throws(Exception::class)
    fun testShow2(){
        val show =twitter2.users.show(testTargetUserScreenName).apply {
            this.includeEntities=true
        }
        val result = show.call()
        assertEquals(result.response.screenName,testTargetUserScreenName)
    }

}