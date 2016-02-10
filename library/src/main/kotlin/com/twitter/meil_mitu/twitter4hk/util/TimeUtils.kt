package com.twitter.meil_mitu.twitter4hk.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {

    private val s:Long = 1000
    private val m:Long = 60 * s
    private val h:Long = 60 * m
    private val d:Long = 24 * h
    private val y:Long = 365 * d

    fun toTime(date: Date): String {
        return toTime(date.time)
    }

    @SuppressLint("SimpleDateFormat")
    fun toTime(date: Long): String {
        val dtime = System.currentTimeMillis() - date
        val format: String
        if (dtime > y) {
            format = "yyyy/MM/dd"
        } else if (dtime > d) {
            format = "MM/dd HH:mm"
        } else {
            format = "HH:mm:ss"
        }
        val sdf1 = SimpleDateFormat(format)
        return sdf1.format(date)
    }

    fun toDetailTime(date: Date): String {
        return toDetailTime(date.time)
    }

    @SuppressLint("SimpleDateFormat")
    fun toDetailTime(date: Long): String {
        val dtime = System.currentTimeMillis() - date
        val format: String
        if (dtime > y) {
            format = "yyyy/MM/dd HH:mm:ss"
        } else if (dtime > d) {
            format = "MM/dd HH:mm:ss"
        } else {
            format = "HH:mm:ss"
        }
        val sdf1 = SimpleDateFormat(format)
        return sdf1.format(date)
    }
}
