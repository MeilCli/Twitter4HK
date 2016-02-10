package com.twitter.meil_mitu.twitter4hk.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import org.json.JSONObject

interface IUserConverter<TUser> : IJsonConverter {

    @Throws(Twitter4HKException::class)
    fun toUser(res: Response): TUser

    @Throws(Twitter4HKException::class)
    fun toUser(obj: JSONObject): TUser

    @Throws(Twitter4HKException::class)
    fun toUserResponseData(res: Response): ResponseData<TUser>

    @Throws(Twitter4HKException::class)
    fun toUserResponseList(res: Response): ResponseList<TUser>

}