package com.twitter.meil_mitu.twitter4hk.converter

import com.squareup.okhttp.Response
import com.twitter.meil_mitu.twitter4hk.ResponseData
import com.twitter.meil_mitu.twitter4hk.ResponseList
import com.twitter.meil_mitu.twitter4hk.exception.Twitter4HKException
import org.json.JSONObject

interface IUserListConverter<TUserList> : IJsonConverter {
    @Throws(Twitter4HKException::class)
    fun toUserList(res: Response): TUserList

    @Throws(Twitter4HKException::class)
    fun toUserList(obj: JSONObject): TUserList

    @Throws(Twitter4HKException::class)
    fun toUserListResponseData(res: Response): ResponseData<TUserList>

    @Throws(Twitter4HKException::class)
    fun toUserListResponseList(res: Response): ResponseList<TUserList>
}