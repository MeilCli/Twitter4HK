package com.twitter.meil_mitu.twitter4hk.util

import android.util.Base64
import com.twitter.meil_mitu.twitter4hk.exception.IncorrectException
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import java.io.File
import java.io.FileInputStream
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


// http://javatechnology.net/java/url-encode/
// not encode '-'
internal fun String.urlEncode(): String {
    return try {
        val encoded = URLEncoder.encode(this, "UTF-8")
        val sb = StringBuilder(encoded.length)
        for (c in encoded.toCharArray()) {
            when (c) {
                '+' -> sb.append("%20")
                '*' -> sb.append("%2A")
                else -> sb.append(c)
            }
        }
        sb.toString()
    } catch (e: UnsupportedEncodingException) {
        this
    }
}

internal fun LongArray.toStringParam(): String {
    val sb = StringBuilder()
    for (l in this) {
        if (sb.length > 0) {
            sb.append(',')
        }
        sb.append(l)
    }
    return sb.toString()
}

internal fun String.toLongArrayFromParam(): LongArray {
    return split(",").map { x -> x.toLong() }.toLongArray()
}

internal fun Array<String>.toStringParam(): String {
    val sb = StringBuilder()
    for (s in this) {
        if (sb.length > 0) {
            sb.append(',')
        }
        sb.append(s)
    }
    return sb.toString()
}

internal fun String.toArrayFromParam(): Array<String> {
    return split(",").toTypedArray()
}

internal fun String.replaceLine(): String {
    val sb = StringBuilder()
    for (c in toCharArray()) {
        if (c != '\n') {
            sb.append(c)
        }
    }
    return sb.toString()
}

internal fun ByteArray.base64Encode(): String {
    // https://android.googlesource.com/platform/frameworks/base/+/refs/heads/master/core/java/android/util/Base64.java
    // Line 674
    return String(Base64.encode(this, Base64.DEFAULT)).replaceLine()
}

@Throws(Twitter4HKException::class)
internal fun readByte(file: File, size: Int, off: Long): ByteArray {
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

@Throws(Twitter4HKException::class)
internal fun <T> tryAndThrow(f: () -> T): T {
    return try {
        f()
    } catch(e: Exception) {
        e.printStackTrace()
        throw Twitter4HKException(e.message)
    }
}

@Throws(IncorrectException::class)
internal fun requirePermission(oauthType: Int, vararg require: Int) {
    if (require.map { Pair(oauthType, it) }.all { it.first and it.second == 0 }) {
        throw IncorrectException("do not allow OauthType")
    }
}

internal fun includePermission(oauthType: Int, vararg permission: Int): Boolean {
    return permission.map { Pair(oauthType, it) }.any { it.first and it.second != 0 }
}

