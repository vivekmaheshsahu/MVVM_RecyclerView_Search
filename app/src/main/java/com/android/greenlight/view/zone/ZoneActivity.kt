package com.android.greenlight.view.zone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.greenlight.R
import com.android.greenlight.common.Adapter.zone.ZoneDetails
import com.android.greenlight.common.data.model.completeFiledForm
import com.android.greenlight.common.utility.Utility
import com.android.greenlight.view.region.RegionDisplayView
import com.android.greenlight.viewmodel.zone.IZoneViewModel
import com.android.greenlight.viewmodel.zone.ZoneViewModel
import java.util.*


class ZoneActivity : IZoneView, AppCompatActivity() {

    var presenter: IZoneViewModel<*>? = null
    var zoneDetails: ZoneDetails? = null
    var emptyLayout: ConstraintLayout? = null
    var toolbar: Toolbar? = null
    var utility = Utility()
    var projectDetails = ArrayList<String>()
    var checkedItem = 0
    var selectedProjectId: Int = 0
    var title = ""
    var formId: String = ""
    var mRecyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.common_layout)
        var tex: TextView = findViewById(R.id.toolbar_title)
        tex.text = "Metrics"
        var text: TextView = findViewById(R.id.textView)
        text.text = "France Performance"
        var texview: TextView = findViewById(R.id.title)
        texview.text = "Zone"
        val actionBar: ActionBar? = null
        actionBar?.setDisplayHomeAsUpEnabled(true)

        mRecyclerView = findViewById(R.id.recycler_view123)
        emptyLayout = findViewById(R.id.empty_midline_list)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        var itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        mRecyclerView?.addItemDecoration(itemDecoration)
        mRecyclerView?.layoutManager = layoutManager

        presenter = ZoneViewModel()
        (presenter as ZoneViewModel).attachView(this)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setAdapter(mWomenList: List<completeFiledForm>) {
        if (mWomenList == null || mWomenList.size < 1) {
            emptyLayout!!.visibility = View.VISIBLE
            return
        }
        if (zoneDetails == null) {
            zoneDetails = ZoneDetails(getContext(), mWomenList, formId, object : ZoneDetails.OnItemClickListener {
                override fun onItemClick(uniqueId: String, name: String, form_id: String) {
                    val intent = Intent(this@ZoneActivity, RegionDisplayView::class.java)
                    startActivity(intent)
                }
            })
            mRecyclerView?.adapter = zoneDetails
        } else {
            zoneDetails!!.swapDataList(mWomenList)
            zoneDetails!!.notifyDataSetChanged()
        }
    }

    override fun getContext(): Context {
        return this
    }

    override fun onResume() {
        super.onResume()
        presenter?.getAllParticipants("0")
    }

}