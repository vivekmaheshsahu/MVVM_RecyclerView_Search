package com.android.greenlight.viewmodel.Employee

import com.android.greenlight.common.data.model.completeFiledForm
import com.android.greenlight.model.employee.EmployeeDisplayModel
import com.android.greenlight.model.employee.IEmployeeDisplayModel
import com.android.greenlight.view.employee.IEmployeeDisplayView
import java.util.*

class EmployeeDisplayViewModel : IEmployeeDisplayViewModel<IEmployeeDisplayView> {

    var view : IEmployeeDisplayView? = null
    var model : IEmployeeDisplayModel? = null

    override fun getAllParticipants(formId: String) {
        val womenList: MutableList<completeFiledForm> = ArrayList()
        val cursor = model?.fetchAllParticipants(formId)
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
        model = EmployeeDisplayModel(view.getContext())
    }

    override fun detch() {
        view = null
    }
}