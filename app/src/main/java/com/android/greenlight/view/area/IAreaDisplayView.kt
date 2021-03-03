package com.android.greenlight.view.area

import android.content.Context
import com.android.greenlight.common.data.model.completeFiledForm

interface IAreaDisplayView {

    fun getContext(): Context

    fun setAdapter(mWomenList: List<completeFiledForm>)


}