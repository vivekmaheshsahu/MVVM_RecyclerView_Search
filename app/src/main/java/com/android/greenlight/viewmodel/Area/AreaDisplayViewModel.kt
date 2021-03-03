package com.android.greenlight.viewmodel.Area

import com.android.greenlight.model.area.AreaModel
import com.android.greenlight.model.area.IAreaDisplayModel
import com.android.greenlight.common.data.model.completeFiledForm
import com.android.greenlight.view.area.IAreaDisplayView
import java.util.*

class AreaDisplayViewModel : IAreaDisplayViewModel<IAreaDisplayView> {

    var view : IAreaDisplayView? = null
    var model : IAreaDisplayModel? = null

    override fun getAllParticipants(formId: String) {
        val womenList: MutableList<completeFiledForm> = ArrayList()
        val cursor = model?.fetchAllParticipants(formId)
        if (cursor != null && cursor.moveToFirst()) do {
            womenList.add(completeFiledForm(cursor.getString(cursor.getColumnIndex("area_name")), cursor.getString(cursor.getColumnIndex("area_id"))))
        } while (cursor.moveToNext())
        view?.setAdapter(womenList)
    }

    override fun attachView(view: IAreaDisplayView) {
        this.view = view
        model = AreaModel(view.getContext())
    }

    override fun detch() {
        view = null
    }
}