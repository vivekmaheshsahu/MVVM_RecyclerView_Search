package com.android.greenlight.data.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Headers
import com.android.greenlight.data.Url.Companion.AUTHENTICATE
import retrofit2.http.GET

/**
 * @author Vivek
 */

interface NetworkCallServiceAPI {

    @Headers("Content-Type: application/json")
    @GET(AUTHENTICATE)
    fun getAuthentication(): Call<ResponseBody>
}