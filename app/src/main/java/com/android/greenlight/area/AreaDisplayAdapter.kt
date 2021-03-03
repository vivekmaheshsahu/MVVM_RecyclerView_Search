package com.android.greenlight.area

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.greenlight.data.model.completeFiledForm
import com.android.greenlight.R

class AreaDisplayAdapter() : RecyclerView.Adapter<AreaDisplayAdapter.ViewHolder>() {
    var mContext :  Context ? = null
    var formId =""
    private lateinit var mOnItemClickListener: OnItemClickListener
    var mWomenList :  List<completeFiledForm>? = null

    constructor(mContext: Context, mWomenList: List<completeFiledForm>, formID: String, mOnItemClickListener: OnItemClickListener):this(){
      this.mContext = mContext
      this.mWomenList = mWomenList
      this.formId = formID
      this.mOnItemClickListener = mOnItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(uniqueId: String,name : String, form_id: String)
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
                    mOnItemClickListener.onItemClick(listModel!!.unique_id, listModel.name, formId)
                }  }
        }

        /*override fun onClick(v: View) {
            val intent = Intent(mContext, CompletedFormDetails::class.java)
            if (clickListener != null) {
                clickListener!!.itemClicked(v, position)
                val i = mWomenList.size
                val u_id = mWomenList[position].unique_id
                val name = mWomenList[position].name
                intent.putExtra("unique_id", u_id)
                intent.putExtra("name", name)
                intent.putExtra("form_id", Integer.valueOf(formId))
            }
            mContext.startActivity(intent)
        }*/

        init {
            textViewName = itemView.findViewById(R.id.textview_name)
        }
    }
}
