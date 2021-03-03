package com.android.greenlight.regionDisplay

import com.android.greenlight.data.model.completeFiledForm
import java.util.*

class RegionDisplayPresenter : IRegionDisplayPresenter<IRegionDisplayView> {

    var view : IRegionDisplayView ? = null
    var interactor : IRegionDisplayInteractor ? = null

    override fun getAllParticipants(formId: String) {
        val womenList: MutableList<completeFiledForm> = ArrayList()
        val cursor = interactor?.fetchAllParticipants(formId)
        if (cursor != null && cursor.moveToFirst()) do {
            womenList.add(completeFiledForm(cursor.getString(cursor.getColumnIndex("region_name")), cursor.getString(cursor.getColumnIndex("region_id"))))
        } while (cursor.moveToNext())
        view?.setAdapter(womenList)
    }

    override fun attachView(view: IRegionDisplayView) {
        this.view = view
        interactor = RegionDisplayInteractor(view.getContext())
    }

    override fun detch() {
        view = null
    }
}