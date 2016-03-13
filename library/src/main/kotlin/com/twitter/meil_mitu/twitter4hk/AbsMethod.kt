package com.twitter.meil_mitu.twitter4hk

import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.util.toArrayFromParam
import com.twitter.meil_mitu.twitter4hk.util.toLongArrayFromParam
import com.twitter.meil_mitu.twitter4hk.util.toStringParam
import java.util.*

abstract class AbsMethod<T> {

    val paramMap = HashMap<String, String>()
    val param: Set<Map.Entry<String, String>> = paramMap.entries
    val size = paramMap.size
    abstract val method: String
    abstract val url: String
    abstract val allowOauthType: Int
    abstract val isAuthorization: Boolean

    protected fun stringParam(key: String)
            = Param<AbsMethod<T>, String, String>(paramMap, key, { x -> x }, { x -> x })

    protected fun stringArrayParam(key: String)
            = Param<AbsMethod<T>, Array<String>, String>(paramMap, key, { x -> x.toStringParam() }, { x -> x.toArrayFromParam() })

    protected fun booleanParam(key: String)
            = Param<AbsMethod<T>, Boolean, String>(paramMap, key, { x -> x.toString() }, { x -> x.toBoolean() })

    protected fun intParam(key: String)
            = Param<AbsMethod<T>, Int, String>(paramMap, key, { x -> x.toString() }, { x -> x.toInt() })

    protected fun longParam(key: String)
            = Param<AbsMethod<T>, Long, String>(paramMap, key, { x -> x.toString() }, { x -> x.toLong() })

    protected fun longArrayParam(key: String)
            = Param<AbsMethod<T>, LongArray, String>(paramMap, key, { x -> x.toStringParam() }, { x -> x.toLongArrayFromParam() })

    protected fun floatParam(key: String)
            = Param<AbsMethod<T>, Float, String>(paramMap, key, { x -> x.toString() }, { x -> x.toFloat() })

    @Throws(Twitter4HKException::class)
    abstract fun call(): T
}
