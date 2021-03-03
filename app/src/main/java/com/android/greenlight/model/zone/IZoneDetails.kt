package com.android.greenlight.model.zone

import android.database.Cursor

interface IZoneDetails {

    fun fetchAllParticipants(formId: String): Cursor?

}