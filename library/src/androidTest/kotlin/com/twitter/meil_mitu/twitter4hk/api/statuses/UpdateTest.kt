package com.twitter.meil_mitu.twitter4hk.api.statuses

import com.twitter.meil_mitu.twitter4hk.twitter
import junit.framework.TestCase

class UpdateTest : TestCase(){

    @Throws(Exception::class)
    fun testUpdate(){
        val text = "api test in ${System.currentTimeMillis()}"
        val update = twitter.statuses.update(text)
        val result = update.call()
        assertEquals(result.text,text)
    }

}