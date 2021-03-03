package com.android.greenlight.regionDisplay

import android.database.Cursor

interface IRegionDisplayInteractor {

   fun fetchAllParticipants(formId:String): Cursor?

}