package com.android.greenlight.interviewBeneficaryModule

import android.content.Context
import com.android.greenlight.data.model.completeFiledForm

interface IInterviewView {

    fun getContext(): Context

    fun setAdapter(mWomenList : List<completeFiledForm>)

}