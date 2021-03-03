package com.android.greenlight.employee

import android.content.Context
import com.android.greenlight.data.model.completeFiledForm

interface IEmployeeDisplayView {

    fun getContext(): Context

    fun setAdapter(mEmployeeList : ArrayList<String>)


}