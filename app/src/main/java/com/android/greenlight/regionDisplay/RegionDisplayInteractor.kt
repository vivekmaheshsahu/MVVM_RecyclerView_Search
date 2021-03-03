package com.android.greenlight.regionDisplay

import android.content.Context
import android.database.Cursor
import com.android.greenlight.database.DBHelper

class RegionDisplayInteractor() : IRegionDisplayInteractor {

    private var mContext: Context? = null
    private var dbHelper: DBHelper? = null

   constructor(mContext: Context):this() {
        this.mContext = mContext
        dbHelper = DBHelper(mContext)
    }

    override fun fetchAllParticipants(formId: String): Cursor? {
        return dbHelper?.fetchRegion()
    }
}