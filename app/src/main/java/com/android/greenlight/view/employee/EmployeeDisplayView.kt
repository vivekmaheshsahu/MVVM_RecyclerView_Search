package com.android.greenlight.view.employee

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.greenlight.R
import com.android.greenlight.common.Adapter.employee.EmployeeDisplayAdapter
import com.android.greenlight.viewmodel.Employee.EmployeeDisplayViewModel
import com.android.greenlight.viewmodel.Employee.IEmployeeDisplayViewModel
import com.android.greenlight.common.utility.Utility
import java.util.*


class EmployeeDisplayView : IEmployeeDisplayView, AppCompatActivity(), SearchView.OnQueryTextListener {

    var viewModel: IEmployeeDisplayViewModel<*>? = null
    private var mSearchView: SearchView? = null
    var adapter: EmployeeDisplayAdapter? = null
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
        setContentView(R.layout.employee_display_layout)
        var tex : TextView = findViewById(R.id.toolbar_title)
        tex.setText("Metrics")
        var text : TextView = findViewById(R.id.textView)
        text.setText("Montreal Performance")
        var texview : TextView = findViewById(R.id.title)
        texview.setText("Name")
        mRecyclerView = findViewById(R.id.recycler_view123)
        emptyLayout = findViewById(R.id.empty_midline_list)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        var itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        mRecyclerView?.addItemDecoration(itemDecoration)
        mRecyclerView?.setLayoutManager(layoutManager)
        mSearchView = findViewById(R.id.searchview_childlist)
        mSearchView?.setOnQueryTextListener(this)
        mSearchView?.setOnClickListener(View.OnClickListener
        {
            mSearchView?.setIconified(false)
        })
        viewModel = EmployeeDisplayViewModel()
        (viewModel as EmployeeDisplayViewModel).attachView(this)

     }

    override fun setAdapter(mEmployeeList: ArrayList<String>) {
        if (mEmployeeList == null || mEmployeeList.size < 1) {
            emptyLayout!!.visibility = View.VISIBLE
            return
        }
        if (adapter == null) {
            adapter = EmployeeDisplayAdapter(getContext(), mEmployeeList)
            mRecyclerView?.setAdapter(adapter)
        }
    }
    override fun getContext(): Context {
        return this
    }

    override fun onResume() {
        super.onResume()
        viewModel?.getAllParticipants("0")
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        adapter?.getFilter()?.filter(query);
        return false;
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        adapter?.getFilter()?.filter(newText);
        return false;
    }

    override fun onBackPressed() {
        // close search view on back button pressed
        if (!mSearchView?.isIconified()!!) {
            mSearchView?.setIconified(true)
            return
        }
        super.onBackPressed()
    }
}

