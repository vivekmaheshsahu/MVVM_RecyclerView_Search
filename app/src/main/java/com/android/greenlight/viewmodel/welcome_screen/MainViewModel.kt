package com.android.greenlight.viewmodel.welcome_screen

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.greenlight.R
import com.android.greenlight.common.data.model.Employee
import com.android.greenlight.common.data.model.Zone
import com.android.greenlight.common.database.DBHelper
import com.android.greenlight.common.database.DatabaseContract
import com.android.greenlight.common.database.DatabaseManager
import com.android.greenlight.common.utility.Utility
import com.android.greenlight.model.welcome_screen.IMainInteractor
import com.android.greenlight.model.welcome_screen.MainInteractor
import com.android.greenlight.view.welcome_screen.IMainView
import org.json.JSONObject
import java.util.*

/**
 * @author Vivek
 */

class MainViewModel : IMainViewModel<IMainView>, IMainInteractor.OnMainFinished {

    var iMainview: IMainView? = null
    var iMainInteractor: IMainInteractor? = null
    var uti = Utility()
    private val checkLoginStatus: Boolean? = null
    private val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.READ_PHONE_STATE)

    
    
    override fun attachView(view: IMainView?) {
        this.iMainview = view
        iMainInteractor = MainInteractor(iMainview!!.context)
        if (checkPermissions()){
            initializeDBHelper()
            fetchdetails()
    }
    }


    override fun detch() {
        iMainview = null
    }

    override fun initializeDBHelper() {
        var dbHelper = DBHelper(iMainview!!.context.applicationContext)
        DatabaseManager.initializeInstance(dbHelper)
        DatabaseManager.getInstance().openDatabase()
    }

    override fun checkPermissions(): Boolean {
        val listPermissionsNeeded = ArrayList<String>()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(iMainview!!.context, permission) != PackageManager.PERMISSION_GRANTED
            ) {
                listPermissionsNeeded.add(permission)
            }
        }
        return if (!listPermissionsNeeded.isEmpty()) {
            getPermissions(listPermissionsNeeded)
            false
        } else {
            true
        }
    }

    override fun getPermissions(listPermissionsNeeded: List<String>) {
        ActivityCompat.requestPermissions((iMainview!!.context as Activity),
                listPermissionsNeeded.toTypedArray(), 1)
    }

    override fun createRequestBody() {
    }

    override fun checkIfUserAlreadyLoggedIn() {
       /* try {
            if (iMainInteractor!!.userAlreadyLoggedIn()) {
            }
        } catch (e: IllegalStateException) {
            checkPermissions()
            initializeDBHelper()
            checkIfUserAlreadyLoggedIn()
        }*/
    }

    override fun deleteUserDetails() {
        uti.getDatabase().delete(DatabaseContract.ZoneTable.TABLE_NAME, null, null)
        uti.getDatabase().delete(DatabaseContract.AreaTable.TABLE_NAME, null, null)
        uti.getDatabase().delete(DatabaseContract.EmployeeTable.TABLE_NAME, null, null)
        uti.getDatabase().delete(DatabaseContract.RegionTable.TABLE_NAME, null, null)
    }

    override fun onSuccess(JsonObject: JSONObject?) {
        var Response_Data = JsonObject?.getJSONObject("ResponseData")
        deleteUserDetails()
        // Storing Zone Details
        var zone_details = Response_Data?.getJSONArray("zone")
        val zone_length = zone_details?.length()
        for (i in 0 until zone_length!!) {
            val empData = zone_details?.getJSONObject(i)
            val zone_name = empData?.optString("zone")
            val zone_territory = empData?.optString("territory")
            val zone_id = i
            iMainInteractor?.saveZone(Zone(zone_id, zone_name, zone_territory))
        }
        //Storing Region
        var region_details = Response_Data?.getJSONArray("region")
        val region_length = region_details?.length()
        for (i in 0 until region_length!!) {
            val relData = region_details?.getJSONObject(i)
            val rel_name = relData?.optString("region")
            val rel_territory = relData?.optString("territory")
            val rel_id = i
            iMainInteractor?.saveRel(Zone(rel_id, rel_name, rel_territory))
        }
        //Storing Area
        var area_details = Response_Data?.getJSONArray("area")
        val area_length = area_details?.length()
        for (i in 0 until area_length!!) {
            val AreaData = area_details?.getJSONObject(i)
            val area_name = AreaData?.optString("area")
            val area_territory = AreaData?.optString("territory")
            val area_id = i
            iMainInteractor?.saveArea(Zone(area_id, area_name, area_territory))
        }
        //Storing emp Details
        var employee_details = Response_Data?.getJSONArray("employee")
        val length = employee_details?.length()
        for (i in 0 until length!!) {
            val empData = employee_details?.getJSONObject(i)
            val emp_area = empData?.optString("area")
            val emp_name = empData?.optString("name")
            val emp_territory = empData?.optString("territory")
            val emp_id = i
            iMainInteractor?.saveEmp(Employee(emp_id, emp_name, emp_area, emp_territory))
            iMainview!!.hideProgressBar()
        }

    }

    override fun onFailure(message: String) {
        iMainview!!.hideProgressBar()
        //  iLoginview!!.showDialog(iLoginview!!.context.getString(R.string.error), message)
    }

    override fun validateCredentials(username: String?, password: String?) {
        TODO("Not yet implemented")
    }

    override fun fetchdetails() {
        if (uti.hasInternetConnectivity(iMainview?.context!!)) {
            iMainview?.showProgressBar()

                iMainInteractor?.NetworkCall(this, iMainview?.context!!)
                   } else {

            val title = iMainview!!.context.getString(R.string.no_internet)
            val message = iMainview!!.context.getString(R.string.no_internet_connection)
            iMainview!!.showDialog(title, message)
        }
    }

}
