package com.android.greenlight.viewmodel.region

import com.android.greenlight.common.utility.IBasePresenter

interface IRegionDisplayViewModel<V> : IBasePresenter<V> {

    fun getAllParticipants(formId: String)

}