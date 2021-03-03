package com.android.greenlight.interviewBeneficaryModule

import com.android.greenlight.utility.IBasePresenter

interface IInterviewPresenter<V>:IBasePresenter<V> {

    fun getAllParticipants(formId: String)

}