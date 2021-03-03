package com.android.greenlight.model.area

import android.content.Context
import android.database.Cursor
import com.android.greenlight.common.database.DBHelper

class AreaModel() : IAreaDisplayModel {

    private var mContext: Context? = null
    private var dbHelper: DBHelper? = null

    constructor(mContext: Context) : this() {
        this.mContext = mContext
        dbHelper = DBHelper(mContext)
    }

    override fun fetchAllParticipants(formId: String): Cursor? {
        return dbHelper?.fetchArea()
    }
}