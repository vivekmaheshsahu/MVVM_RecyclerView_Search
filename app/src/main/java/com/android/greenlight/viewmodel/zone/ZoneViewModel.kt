package com.android.greenlight.viewmodel.zone

import com.android.greenlight.common.data.model.completeFiledForm
import com.android.greenlight.model.zone.IZoneDetails
import com.android.greenlight.model.zone.ZoneDetails
import com.android.greenlight.view.zone.IZoneView
import java.util.*

class ZoneViewModel : IZoneViewModel<IZoneView> {

    var view: IZoneView? = null
    var interactor: IZoneDetails? = null

    override fun getAllParticipants(formId: String) {
        val womenList: MutableList<completeFiledForm> = ArrayList()
        val cursor = interactor?.fetchAllParticipants(formId)
        if (cursor != null && cursor.moveToFirst()) do {
            womenList.add(completeFiledForm(cursor.getString(cursor.getColumnIndex("zone_name")), cursor.getString(cursor.getColumnIndex("zone_id"))))
        } while (cursor.moveToNext())
        view?.setAdapter(womenList)
    }

    override fun attachView(view: IZoneView) {
        this.view = view
        interactor = ZoneDetails(view.getContext())
    }

    override fun detch() {
        view = null
    }
}