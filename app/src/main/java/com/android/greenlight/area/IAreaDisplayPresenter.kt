package com.android.greenlight.area

import com.android.greenlight.utility.IBasePresenter

interface IAreaDisplayPresenter<V>:IBasePresenter<V> {

    fun getAllParticipants(formId: String)

}