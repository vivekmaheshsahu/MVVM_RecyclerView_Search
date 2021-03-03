package com.android.greenlight.area

import com.android.greenlight.data.model.completeFiledForm
import java.util.*

class AreaDisplayPresenter : IAreaDisplayPresenter<IAreaDisplayView> {

    var view : IAreaDisplayView ? = null
    var interactor : IAreaDisplayInteractor ? = null

    override fun getAllParticipants(formId: String) {
        val womenList: MutableList<completeFiledForm> = ArrayList()
        val cursor = interactor?.fetchAllParticipants(formId)
        if (cursor != null && cursor.moveToFirst()) do {
            womenList.add(completeFiledForm(cursor.getString(cursor.getColumnIndex("area_name")), cursor.getString(cursor.getColumnIndex("area_id"))))
        } while (cursor.moveToNext())
        view?.setAdapter(womenList)
    }

    override fun attachView(view: IAreaDisplayView) {
        this.view = view
        interactor = AreaDisplayInteractor(view.getContext())
    }

    override fun detch() {
        view = null
    }
}