package com.twitter.meil_mitu.twitter4hk.streaming

import com.twitter.meil_mitu.twitter4hk.exception.IncorrectException
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection

class Stream(private val param: IStreamParam) : Thread() {

    private var input: InputStream? = null
    private var br: BufferedReader? = null
    var isClose: Boolean = false
        private set
    var isConnect: Boolean = false
        private set
    private var sleepTime: Long = 0
    private var retryCount: Int = 0

    init {
        this.isClose = false
        super.start()
    }

    fun closeStream(){
        if (br != null) {
            try {
                br!!.close()
                br=null
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (input != null) {
            try {
                input!!.close()
                input=null
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun run() {
        var line: String?
        while (isClose == false) {
            try {
                this.input = param.inputStream
                this.br = BufferedReader(InputStreamReader(input))
                if (param.streamListener != null) {
                    param.streamListener!!.onConnect()
                }
                isConnect = true
                retryCount = 0
                sleepTime = 0
                while (isClose == false) {
                    line = br!!.readLine()
                    if (line != null && line.length > 0) {
                        try {
                            param.onLine(line)
                        } catch (e: Exception) {
                            if (param.streamListener != null) {
                                param.streamListener!!.onException(e)
                            }
                        }
                    }
                }
            } catch (e: Twitter4HKException) {
                e.printStackTrace()
                if (param.streamListener != null) {
                    param.streamListener!!.onException(e)
                }
                if (isReconnectionCode(e.httpStatusCode) == false) {
                    isClose = true
                }
                sleepTime = (10 * 1000).toLong()
            } catch (e: IncorrectException) {
                e.printStackTrace()
                if (param.streamListener != null) {
                    param.streamListener!!.onException(e)
                }
                isClose = true
            } catch (e: IOException) {
                e.printStackTrace()
                if (param.streamListener != null) {
                    param.streamListener!!.onException(e)
                }
                sleepTime = 250
            }

            if (retryCount >= retryLimit) {
                isClose = true
            }
            if (isClose == true) {
                break
            }
            if (sleepTime == 0L) {
                sleepTime = (5 * 1000).toLong()
            }
            closeStream()
            if (param.streamListener != null) {
                param.streamListener!!.onDisConnect()
            }
            isConnect = false
            retryCount++
            try {
                Thread.sleep(sleepTime)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }

        closeStream()
        if (param.streamListener != null) {
            param.streamListener!!.onDisConnect()
            param.streamListener!!.onClose()
        }
        isConnect = false

    }

    fun close() {
        this.isClose = true
    }

    @Synchronized override fun start() {
        throw IncorrectException("Stream not allow start by user")
    }

    companion object {
        private val retryLimit = 5

        private fun isReconnectionCode(code: Int): Boolean {
            when (code) {
                HttpURLConnection.HTTP_UNAUTHORIZED,
                HttpURLConnection.HTTP_FORBIDDEN,
                HttpURLConnection.HTTP_NOT_FOUND,
                HttpURLConnection.HTTP_NOT_ACCEPTABLE,
                HttpURLConnection.HTTP_ENTITY_TOO_LARGE,
                420-> return false
            }
            return true
        }
    }
}
