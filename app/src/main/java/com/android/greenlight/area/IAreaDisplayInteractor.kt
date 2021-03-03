package com.android.greenlight.area

import android.database.Cursor

interface IAreaDisplayInteractor {

   fun fetchAllParticipants(formId:String): Cursor?

}