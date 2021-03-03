package com.android.greenlight.area

import android.content.Context
import com.android.greenlight.data.model.completeFiledForm

interface IAreaDisplayView {

    fun getContext(): Context

    fun setAdapter(mWomenList : List<completeFiledForm>)


}