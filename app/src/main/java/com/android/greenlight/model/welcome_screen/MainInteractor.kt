package com.android.greenlight.model.welcome_screen

import android.content.ContentValues
import android.content.Context
import com.android.greenlight.common.data.model.Employee
import com.android.greenlight.common.data.model.Zone
import com.android.greenlight.common.data.retrofit.RemoteDataSource
import com.android.greenlight.common.database.DBHelper
import com.android.greenlight.common.database.DatabaseContract.*
import com.android.greenlight.common.utility.Utility
import com.android.greenlight.model.welcome_screen.IMainInteractor.OnMainFinished

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
        values.put(EmployeeTable.COLUMN_EMP_ID, emp.empId)
        values.put(EmployeeTable.COLUMN_EMP_NAME, emp.empName)
        values.put(EmployeeTable.COLUMN_EMP_AREA, emp.empArea)
        values.put(EmployeeTable.COLUMN_EMP_TERRITORY, emp.empTerritory)
        uti.getDatabase().insert(EmployeeTable.TABLE_NAME, null, values)
    }

    override fun saveZone(zon: Zone) {
        val values = ContentValues()
        values.put(ZoneTable.COLUMN_ZONE_ID, zon.zoneId)
        values.put(ZoneTable.COLUMN_ZONE_NAME, zon.zoneName)
        values.put(ZoneTable.COLUMN_ZONE_TERRITORY, zon.zoneTerritory)
        uti.getDatabase().insert(ZoneTable.TABLE_NAME, null, values)
    }

    override fun saveRel(zon: Zone) {
        val values = ContentValues()
        values.put(RegionTable.COLUMN_REGION_ID, zon.zoneId)
        values.put(RegionTable.COLUMN_REGION_NAME, zon.zoneName)
        values.put(RegionTable.COLUMN_REGION_TERRITORY, zon.zoneTerritory)
        uti.getDatabase().insert(RegionTable.TABLE_NAME, null, values)
    }

    override fun saveArea(zon: Zone) {
        val values = ContentValues()
        values.put(AreaTable.COLUMN_AREA_ID, zon.zoneId)
        values.put(AreaTable.COLUMN_AREA_NAME, zon.zoneName)
        values.put(AreaTable.COLUMN_AREA_TERRITORY, zon.zoneTerritory)
        uti.getDatabase().insert(AreaTable.TABLE_NAME, null, values)
    }

}