package com.android.greenlight.interviewBeneficaryModule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.greenlight.R
import com.android.greenlight.data.model.completeFiledForm
import com.android.greenlight.regionDisplay.RegionDisplayActivity
import com.android.greenlight.utility.Utility
import java.util.*


class InterviewActivity :IInterviewView, AppCompatActivity() {

    var presenter: IInterviewPresenter<*>? = null
    var adapter: InterviewAdapter? = null
    var emptyLayout: ConstraintLayout? = null
    var toolbar: Toolbar? = null
    var utility = Utility()
    var projectDetails = ArrayList<String>()
    var checkedItem = 0
    var selectedProjectId:Int = 0
    var title = ""
    var formId: String = ""
    var mRecyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.midline_interview_layout)
        var tex : TextView = findViewById(R.id.toolbar_title)
        tex.setText("Metrics")
        var text : TextView = findViewById(R.id.textView)
        text.setText("France Performance")
        var texview : TextView = findViewById(R.id.title)
        texview.setText("Zone")
        val actionBar: ActionBar ?= null
        actionBar?.setDisplayHomeAsUpEnabled(true)

        mRecyclerView = findViewById(R.id.recycler_view123)
        emptyLayout = findViewById(R.id.empty_midline_list)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        var itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        mRecyclerView?.addItemDecoration(itemDecoration)
        mRecyclerView?.setLayoutManager(layoutManager)

        presenter = InterviewPresenter()
        (presenter as InterviewPresenter).attachView(this)

     }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
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
        if (adapter == null) {
            adapter = InterviewAdapter(getContext(), mWomenList, formId, object : InterviewAdapter.OnItemClickListener {
                override fun onItemClick(uniqueId: String, name: String, form_id: String) {
                    val intent = Intent(this@InterviewActivity, RegionDisplayActivity::class.java)
                    startActivity(intent)
                }
            })
            mRecyclerView?.setAdapter(adapter)
        } else {
            adapter!!.swapDataList(mWomenList)
            adapter!!.notifyDataSetChanged()
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