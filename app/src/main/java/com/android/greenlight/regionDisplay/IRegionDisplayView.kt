package com.android.greenlight.regionDisplay

import android.content.Context
import com.android.greenlight.data.model.completeFiledForm

interface IRegionDisplayView {

    fun getContext(): Context

    fun setAdapter(mWomenList : List<completeFiledForm>)


}