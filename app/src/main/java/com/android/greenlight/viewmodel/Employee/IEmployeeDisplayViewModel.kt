package com.android.greenlight.viewmodel.Employee

import com.android.greenlight.common.utility.IBasePresenter

interface IEmployeeDisplayViewModel<V>:IBasePresenter<V> {

    fun getAllParticipants(formId: String)

}