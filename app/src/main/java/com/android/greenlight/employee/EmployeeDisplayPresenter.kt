package com.android.greenlight.employee

import com.android.greenlight.data.model.completeFiledForm
import java.util.*

class EmployeeDisplayPresenter : IEmployeeDisplayPresenter<IEmployeeDisplayView> {

    var view : IEmployeeDisplayView ? = null
    var interactor : IEmployeeDisplayInteractor ? = null

    override fun getAllParticipants(formId: String) {
        val womenList: MutableList<completeFiledForm> = ArrayList()
        val cursor = interactor?.fetchAllParticipants(formId)
        if (cursor != null && cursor.moveToFirst()) do {
            womenList.add(completeFiledForm(cursor.getString(cursor.getColumnIndex("area_name")), cursor.getString(cursor.getColumnIndex("area_id"))))
        } while (cursor.moveToNext())
        var employeeFilterList = ArrayList<String>()
        if (cursor != null && cursor.moveToFirst()) do {
            employeeFilterList.add(cursor.getString(cursor.getColumnIndex("area_name")))
        } while (cursor.moveToNext())
        view?.setAdapter(employeeFilterList)
    }

    override fun attachView(view: IEmployeeDisplayView) {
        this.view = view
        interactor = EmployeeDisplayInteractor(view.getContext())
    }

    override fun detch() {
        view = null
    }
}