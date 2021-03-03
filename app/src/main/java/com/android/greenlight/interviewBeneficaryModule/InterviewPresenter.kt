package com.android.greenlight.interviewBeneficaryModule

import com.android.greenlight.data.model.completeFiledForm
import java.util.*

class InterviewPresenter : IInterviewPresenter<IInterviewView> {

    var view : IInterviewView ? = null
    var interactor : IInterviewInteractor ? = null

    override fun getAllParticipants(formId: String) {
        val womenList: MutableList<completeFiledForm> = ArrayList()
        val cursor = interactor?.fetchAllParticipants(formId)
        if (cursor != null && cursor.moveToFirst()) do {
            womenList.add(completeFiledForm(cursor.getString(cursor.getColumnIndex("zone_name")), cursor.getString(cursor.getColumnIndex("zone_id"))))
        } while (cursor.moveToNext())
        view?.setAdapter(womenList)
    }

    override fun attachView(view: IInterviewView) {
        this.view = view
        interactor = InterviewInteractor(view.getContext())
    }

    override fun detch() {
        view = null
    }
}