package com.android.greenlight.viewmodel.Area

import com.android.greenlight.common.utility.IBasePresenter

interface IAreaDisplayViewModel<V>:IBasePresenter<V> {

    fun getAllParticipants(formId: String)

}