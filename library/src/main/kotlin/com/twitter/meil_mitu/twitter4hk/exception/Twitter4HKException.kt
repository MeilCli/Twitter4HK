package com.twitter.meil_mitu.twitter4hk.exception

class Twitter4HKException : Exception {

    var httpStatusCode: Int = -1
        private set
    var errorCode: Int = -1
        private set
    var httpStatusMessage: String? = null
        private set
    var errorMessage: String? = null
        private set

    constructor(message: String?) : super(message) {
    }

    constructor(message: String?, httpStatusCode: Int, errorCode: Int) : super(message) {
        this.httpStatusCode = httpStatusCode
        this.errorCode = errorCode
        this.httpStatusMessage = toHttpStatusCodeSubMessage(httpStatusCode)
        this.errorMessage = toErrorCodeSubMessage(errorCode)
    }

    constructor(message: String?, httpStatusCode: Int, errorMessage: String) : super(message) {
        this.httpStatusCode = httpStatusCode
        this.httpStatusMessage = toHttpStatusCodeSubMessage(httpStatusCode)
        this.errorMessage = errorMessage
    }

    companion object {

        fun toHttpStatusCodeSubMessage(httpStatusCode: Int): String? {
            when (httpStatusCode) {
                200 -> return "Success!"
                304 -> return "There was no new data to return."
                400 -> return "The request was invalid or cannot be otherwise served. An accompanying error message will explain further. In API v1.1, requests without authentication are considered invalid and will yield this response."
                401 -> return "Authentication credentials were missing or incorrect.\nAlso returned in other circumstances, for example all calls to API v1 endpoints now return 401 (use API v1.1 instead)."
                403 -> return "The request is understood, but it has been refused or access is not allowed. An accompanying error message will explain why. This code is used when requests are being denied due to update limits."
                404 -> return "The URI requested is invalid or the resource requested, such as a user, does not exists. Also returned when the requested format is not supported by the requested method."
                406 -> return "Returned by the Search API when an invalid format is specified in the request."
                410 -> return "This resource is gone. Used to indicate that an API endpoint has been turned off. For example: \"The Twitter REST API v1 will soon stop functioning. Please migrate to API v1.1.\""
                420 -> return "Returned by the version 1 Search and Trends APIs when you are being rate limited."
                422 -> return "Returned when an image uploaded to POST account/update_profile_banner is unable to be processed."
                429 -> return "Returned in API v1.1 when a request cannot be served due to the application's rate limit having been exhausted for the resource. See Rate Limiting in API v1.1."
                500 -> return "Something is broken. Please post to the group so the Twitter team can investigate."
                502 -> return "Twitter is down or being upgraded."
                503 -> return "The Twitter servers are up, but overloaded with requests. Try again later."
                504 -> return "The Twitter servers are up, but the request couldn't be serviced due to some failure within our stack. Try again later."
                else -> return null
            }
        }

        fun toErrorCodeSubMessage(errorCode: Int): String? {
            when (errorCode) {
                32 -> return "Your call could not be completed as dialed."
                34 -> return "Corresponds with an HTTP 404 - the specified resource was not found."
                64 -> return "Corresponds with an HTTP 403 — the access token being used belongs to a suspended user and they can't complete the action you're trying to take"
                68 -> return "Corresponds to a HTTP request to a retired v1-era URL."
                88 -> return "The request limit for this resource has been reached for the current rate limit window."
                89 -> return "The access token used in the request is incorrect or has expired. Used in API v1.1"
                92 -> return "Only SSL connections are allowed in the API, you should update your request to a secure connection. See how to connect using SSL"
                130 -> return "Corresponds with an HTTP 503 - Twitter is temporarily over capacity."
                131 -> return "Corresponds with an HTTP 500 - An unknown internal error occurred."
                135 -> return "Corresponds with a HTTP 401 - it means that your oauth_timestamp is either ahead or behind our acceptable range"
                161 -> return "Corresponds with HTTP 403 — thrown when a user cannot follow another user due to some kind of limit"
                179 -> return "Corresponds with HTTP 403 — thrown when a Tweet cannot be viewed by the authenticating user, usually due to the tweet's author having protected their tweets."
                185 -> return "Corresponds with HTTP 403 — thrown when a tweet cannot be posted due to the user having no allowance remaining to post. Despite the text in the error message indicating that this error is only thrown when a daily limit is reached, this error will be thrown whenever a posting limitation has been reached. Posting allowances have roaming windows of time of unspecified duration."
                187 -> return "The status text has been Tweeted already by the authenticated account."
                215 -> return "Typically sent with 1.1 responses with HTTP code 400. The method requires authentication but it was not presented or was wholly invalid."
                226 -> return "We constantly monitor and adjust our filters to block spam and malicious activity on the Twitter platform. These systems are tuned in real-time. If you get this response our systems have flagged the Tweet or DM as possibly fitting this profile. If you feel that the Tweet or DM you attempted to create was flagged in error, please report the details around that to us by filing a ticket at https://support.twitter.com/forms/platform."
                231 -> return "Returned as a challenge in xAuth when the user has login verification enabled on their account and needs to be directed to twitter.com to generate a temporary password."
                251 -> return "Corresponds to a HTTP request to a retired URL."
                261 -> return "Corresponds with HTTP 403 — thrown when the application is restricted from POST, PUT, or DELETE actions. See How to appeal application suspension and other disciplinary actions."
                271 -> return "Corresponds with HTTP 403. The authenticated user account cannot mute itself."
                272 -> return "Corresponds with HTTP 403. The authenticated user account is not muting the account a call is attempting to unmute."
                else -> return null
            }
        }
    }
}
