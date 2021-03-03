package com.android.greenlight.view.region

import android.content.Context
import com.android.greenlight.common.data.model.completeFiledForm

interface IRegionDisplayView {

    fun getContext(): Context

    fun setAdapter(mWomenList : List<completeFiledForm>)


}