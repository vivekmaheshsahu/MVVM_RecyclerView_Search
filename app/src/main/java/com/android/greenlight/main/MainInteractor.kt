package com.android.greenlight.main

import android.content.ContentValues
import android.content.Context
import com.android.greenlight.data.model.Employee
import com.android.greenlight.data.model.Zone
import com.android.greenlight.data.retrofit.RemoteDataSource
import com.android.greenlight.database.DBHelper
import com.android.greenlight.database.DatabaseContract
import com.android.greenlight.utility.Constants
import com.android.greenlight.utility.Utility
import org.json.JSONException
import org.json.JSONObject
import kotlin.jvm.Throws
import com.android.greenlight.main.IMainInteractor.OnMainFinished
import com.android.greenlight.database.DatabaseContract.EmployeeTable
import com.android.greenlight.database.DatabaseContract.ZoneTable
import com.android.greenlight.database.DatabaseContract.RegionTable
import com.android.greenlight.database.DatabaseContract.AreaTable
import java.time.ZoneId

/**
 *  @author Vivek
 */

class MainInteractor internal constructor(private val mcontext: Context) : IMainInteractor {
    private var dbHelper: DBHelper? = null
    var uti = Utility()

    override fun NetworkCall(onMain: OnMainFinished, context: Context) {
        var remoteDataSource = RemoteDataSource.getInstance()
        var fetchService = remoteDataSource.dataService
        fetchService.getAuthentication(onMain, context)
    }

    override fun userAlreadyLoggedIn(): Boolean {
        var cursor = uti.getDatabase().rawQuery("SELECT * FROM zone", null)
        return cursor.moveToFirst()
   return true
    }

    override fun saveEmp(emp: Employee) {
        val values = ContentValues()
        values.put(EmployeeTable.COLUMN_EMP_ID,emp.empId)
        values.put(EmployeeTable.COLUMN_EMP_NAME, emp.empName)
        values.put(EmployeeTable.COLUMN_EMP_AREA, emp.empArea)
        values.put(EmployeeTable.COLUMN_EMP_TERRITORY,emp.empTerritory)
        uti.getDatabase().insert(EmployeeTable.TABLE_NAME, null, values)
    }

    override fun saveZone(zon: Zone) {
        val values = ContentValues()
        values.put(ZoneTable.COLUMN_ZONE_ID,zon.zoneId)
        values.put(ZoneTable.COLUMN_ZONE_NAME, zon.zoneName)
        values.put(ZoneTable.COLUMN_ZONE_TERRITORY, zon.zoneTerritory)
        uti.getDatabase().insert(ZoneTable.TABLE_NAME, null, values)
    }

    override fun saveRel(zon: Zone) {
        val values = ContentValues()
        values.put(RegionTable.COLUMN_REGION_ID,zon.zoneId)
        values.put(RegionTable.COLUMN_REGION_NAME, zon.zoneName)
        values.put(RegionTable.COLUMN_REGION_TERRITORY, zon.zoneTerritory)
        uti.getDatabase().insert(RegionTable.TABLE_NAME, null, values)
    }

    override fun saveArea(zon: Zone) {
        val values = ContentValues()
        values.put(AreaTable.COLUMN_AREA_ID,zon.zoneId)
        values.put(AreaTable.COLUMN_AREA_NAME, zon.zoneName)
        values.put(AreaTable.COLUMN_AREA_TERRITORY, zon.zoneTerritory)
        uti.getDatabase().insert(AreaTable.TABLE_NAME, null, values)
    }

}