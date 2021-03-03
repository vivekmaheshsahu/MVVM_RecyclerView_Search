package com.android.greenlight.common.data.service

import com.android.greenlight.common.data.Url.Companion.AUTHENTICATE
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * @author Vivek
 */

interface NetworkCallServiceAPI {

    @Headers("Content-Type: application/json")
    @GET(AUTHENTICATE)
    fun getAuthentication(): Call<ResponseBody>
}