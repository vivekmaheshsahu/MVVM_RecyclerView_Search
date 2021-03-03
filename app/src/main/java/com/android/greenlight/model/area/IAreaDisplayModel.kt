package com.android.greenlight.model.area

import android.database.Cursor

interface IAreaDisplayModel {

   fun fetchAllParticipants(formId:String): Cursor?

}