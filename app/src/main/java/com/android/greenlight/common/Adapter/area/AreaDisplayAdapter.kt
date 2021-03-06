package com.android.greenlight.common.Adapter.area

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.greenlight.R
import com.android.greenlight.common.data.model.completeFiledForm

// Area adapter class is used to display data of different area in recycler view

class AreaDisplayAdapter() : RecyclerView.Adapter<AreaDisplayAdapter.ViewHolder>() {
    var mContext: Context? = null
    var formId = ""
    private lateinit var mOnItemClickListener: OnItemClickListener
    var mWomenList: List<completeFiledForm>? = null

    constructor(mContext: Context, mWomenList: List<completeFiledForm>, formID: String, mOnItemClickListener: OnItemClickListener) : this() {
        this.mContext = mContext
        this.mWomenList = mWomenList
        this.formId = formID
        this.mOnItemClickListener = mOnItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(uniqueId: String, name: String, form_id: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_incomplete_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.bindData(mWomenList!![i])
    }

    override fun getItemCount(): Int {
        return mWomenList!!.size
    }

    fun swapDataList(womenList: List<completeFiledForm>) {
        mWomenList = womenList
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView
        fun bindData(listModel: completeFiledForm?) {
            if (listModel != null) {
                textViewName.text = listModel.name
                textViewName.setOnClickListener {
                    mOnItemClickListener.onItemClick(listModel.unique_id, listModel.name, formId)
                }
            }
        }

        init {
            textViewName = itemView.findViewById(R.id.textview_name)
        }
    }
}
