package com.android.greenlight.main

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import kotlin.jvm.Throws
import com.android.greenlight.data.model.Employee
import com.android.greenlight.data.model.Zone

/**
 * @author Vivek
 */

interface IMainInteractor {

    fun NetworkCall(onMainFinished: OnMainFinished, context: Context)

    fun userAlreadyLoggedIn(): Boolean

    interface OnMainFinished {
        @Throws(JSONException::class)
        fun onSuccess(jsonObject: JSONObject?)
        fun onFailure(message: String)
    }

    fun saveEmp(emp : Employee)
    fun saveZone(zon : Zone)
    fun saveRel(zon : Zone)
    fun saveArea(zon : Zone)
}