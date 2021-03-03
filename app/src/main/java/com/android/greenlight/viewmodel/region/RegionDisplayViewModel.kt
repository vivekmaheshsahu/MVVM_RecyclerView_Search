package com.android.greenlight.viewmodel.region

import com.android.greenlight.common.data.model.completeFiledForm
import com.android.greenlight.model.region.IRegionDisplayModel
import com.android.greenlight.model.region.RegionDisplayModel
import com.android.greenlight.view.region.IRegionDisplayView
import java.util.*

class RegionDisplayViewModel : IRegionDisplayViewModel<IRegionDisplayView> {

    var view : IRegionDisplayView? = null
    var model : IRegionDisplayModel? = null

    override fun getAllParticipants(formId: String) {
        val womenList: MutableList<completeFiledForm> = ArrayList()
        val cursor = model?.fetchAllParticipants(formId)
        if (cursor != null && cursor.moveToFirst()) do {
            womenList.add(completeFiledForm(cursor.getString(cursor.getColumnIndex("region_name")), cursor.getString(cursor.getColumnIndex("region_id"))))
        } while (cursor.moveToNext())
        view?.setAdapter(womenList)
    }

    override fun attachView(view: IRegionDisplayView) {
        this.view = view
        model = RegionDisplayModel(view.getContext())
    }

    override fun detch() {
        view = null
    }
}