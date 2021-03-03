package com.android.greenlight.viewmodel.zone

import com.android.greenlight.common.utility.IBasePresenter

interface IZoneViewModel<V> : IBasePresenter<V> {

    fun getAllParticipants(formId: String)

}