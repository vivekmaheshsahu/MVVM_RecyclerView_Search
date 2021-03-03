package com.android.greenlight.interviewBeneficaryModule

import android.database.Cursor

interface IInterviewInteractor {

   fun fetchAllParticipants(formId:String): Cursor?

}