package com.twitter.meil_mitu.twitter4hk.aclog

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import com.twitter.meil_mitu.twitter4hk.oauth.IOauthEchoCheck
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getJSONObject
import com.twitter.meil_mitu.twitter4hk.util.JsonUtils.getString
import org.json.JSONObject


class AclogOauthEchoCheck : IOauthEchoCheck {

    @Throws(Twitter4HKException::class)
    override fun checkError(res: Response) {
        if (res.isSuccessful == false) {
            try {
                var obj = JSONObject(res.body().string())
                obj = getJSONObject(obj, "error")
                throw Twitter4HKException(getString(obj, "message"))
            } catch (e: Exception) {
                e.printStackTrace()
                throw Twitter4HKException(e.message)
            }

        }
    }
}
