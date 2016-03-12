package com.twitter.meil_mitu.twitter4hk.util

import android.util.Base64
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import java.io.File
import java.io.FileInputStream

import java.io.UnsupportedEncodingException
import java.net.URLEncoder

object Utils {

    // http://javatechnology.net/java/url-encode/
    // not encode '-'
    fun urlEncode(string: String): String {
        try {
            val encoded = URLEncoder.encode(string, "UTF-8")
            val sb = StringBuilder(encoded.length)
            for (c in encoded.toCharArray()) {
                when (c) {
                    '+' -> sb.append("%20")
                    '*' -> sb.append("%2A")
                    else -> sb.append(c)
                }
            }
            return sb.toString()
        } catch (e: UnsupportedEncodingException) {
            return string
        }

    }

    fun toString(ls: LongArray): String {
        val sb = StringBuilder()
        for (l in ls) {
            if (sb.length > 0) {
                sb.append(',')
            }
            sb.append(l)
        }
        return sb.toString()
    }

    fun toLongArray(s: String): LongArray {
        return s.split(",").map { x -> x.toLong() }.toLongArray()
    }

    fun toString(ss: Array<String>): String {
        val sb = StringBuilder()
        for (s in ss) {
            if (sb.length > 0) {
                sb.append(',')
            }
            sb.append(s)
        }
        return sb.toString()
    }

    fun toArray(s: String): Array<String> {
        return s.split(",").toTypedArray()
    }

    fun replaceLine(s: String): String {
        val sb = StringBuilder()
        for (c in s.toCharArray()) {
            if (c != '\n') {
                sb.append(c)
            }
        }
        return sb.toString()
    }

    fun base64Encode(bs: ByteArray): String {
        // https://android.googlesource.com/platform/frameworks/base/+/refs/heads/master/core/java/android/util/Base64.java
        // Line 674
        return replaceLine(String(Base64.encode(bs, Base64.DEFAULT)))
    }

    @Throws(Twitter4HKException::class)
    fun readByte(file: File, size: Int, off: Long): ByteArray {
        val input = FileInputStream(file)
        val result = ByteArray(size)
        try {
            input.skip(off)
            input.read(result)
        } catch (e: Exception) {
            throw Twitter4HKException(e.message)
        } finally {
            input.close()
        }
        return result
    }

}
