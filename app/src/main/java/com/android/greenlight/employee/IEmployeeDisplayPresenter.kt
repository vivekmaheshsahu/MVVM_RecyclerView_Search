package com.android.greenlight.employee

import com.android.greenlight.utility.IBasePresenter

interface IEmployeeDisplayPresenter<V>:IBasePresenter<V> {

    fun getAllParticipants(formId: String)

}