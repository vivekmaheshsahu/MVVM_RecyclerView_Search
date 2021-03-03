package com.android.greenlight.employee

import android.content.Context
import android.database.Cursor
import com.android.greenlight.database.DBHelper

class EmployeeDisplayInteractor() : IEmployeeDisplayInteractor {

    private var mContext: Context? = null
    private var dbHelper: DBHelper? = null

   constructor(mContext: Context):this() {
        this.mContext = mContext
        dbHelper = DBHelper(mContext)
    }

    override fun fetchAllParticipants(formId: String): Cursor? {
        return dbHelper?.fetchArea()
    }
}