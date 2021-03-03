package com.android.greenlight.regionDisplay

import com.android.greenlight.utility.IBasePresenter

interface IRegionDisplayPresenter<V>:IBasePresenter<V> {

    fun getAllParticipants(formId: String)

}