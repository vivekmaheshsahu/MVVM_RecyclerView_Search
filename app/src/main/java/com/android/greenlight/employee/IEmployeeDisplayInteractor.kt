package com.android.greenlight.employee

import android.database.Cursor

interface IEmployeeDisplayInteractor {

   fun fetchAllParticipants(formId:String): Cursor?

}