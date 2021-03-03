package com.android.greenlight.model.region

import android.database.Cursor

interface IRegionDisplayModel {

   fun fetchAllParticipants(formId:String): Cursor?

}