package com.android.greenlight.view.employee

import android.content.Context

interface IEmployeeDisplayView {

    fun getContext(): Context

    fun setAdapter(mEmployeeList: ArrayList<String>)


}