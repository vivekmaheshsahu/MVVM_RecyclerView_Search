package com.android.greenlight.data.service

import android.content.Context
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import com.android.greenlight.main.IMainInteractor.OnMainFinished
import com.android.greenlight.R

/**
 * @author Vivek
 */
class NetworkService(private val loginServiceAPI: NetworkCallServiceAPI) {
   fun getAuthentication(onLoginFinished: OnMainFinished, context: Context){
           var responseBodyCall : Call<ResponseBody> = loginServiceAPI.getAuthentication()
           responseBodyCall.enqueue(object : Callback<ResponseBody> {
               override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                   try {
                       var loginJsonResponse: String? = null
                       if (response.body() != null)
                           loginJsonResponse = response.body().string()
                       else if (response.errorBody() != null)
                           loginJsonResponse = response.errorBody().string()

                       var loginJsonObject: JSONObject = JSONObject(loginJsonResponse)
                       onLoginFinished.onSuccess(loginJsonObject)
                   } catch (e: IOException) {
                       e.printStackTrace()
                       onLoginFinished.onFailure(context.getString(R.string.input_output_error_occured))
                   }
                   catch (e: JSONException) {
                       e.printStackTrace()
                       onLoginFinished.onFailure(context.getString(R.string.input_output_error_occured))
                   }
               }

               override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                   onLoginFinished.onFailure(context.getString(R.string.oops_some_thing_happened_wrong))
               }

           })
       }
   }
