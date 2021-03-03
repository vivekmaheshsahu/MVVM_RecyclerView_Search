package com.android.greenlight.view.zone

import android.content.Context
import com.android.greenlight.common.data.model.completeFiledForm

interface IZoneView {

    fun getContext(): Context

    fun setAdapter(mWomenList: List<completeFiledForm>)

}