package com.android.greenlight.model.employee

import android.database.Cursor

interface IEmployeeDisplayModel {

   fun fetchAllParticipants(formId:String): Cursor?

}