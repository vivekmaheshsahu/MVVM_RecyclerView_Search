package com.android.greenlight.model.zone

import android.content.Context
import android.database.Cursor
import com.android.greenlight.common.database.DBHelper

class ZoneDetails() : IZoneDetails {

    private var mContext: Context? = null
    private var dbHelper: DBHelper? = null

    constructor(mContext: Context) : this() {
        this.mContext = mContext
        dbHelper = DBHelper(mContext)
    }

    override fun fetchAllParticipants(formId: String): Cursor? {
        return dbHelper?.fetchAll()
    }
}