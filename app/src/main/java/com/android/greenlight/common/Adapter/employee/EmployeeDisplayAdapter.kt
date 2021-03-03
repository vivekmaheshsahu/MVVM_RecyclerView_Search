package com.android.greenlight.common.Adapter.employee

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.greenlight.R
import kotlin.collections.ArrayList

class EmployeeDisplayAdapter(var context: Context, private var countryList: ArrayList<String>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var countryFilterList = ArrayList<String>()

    lateinit var mcontext: Context

    class CountryHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        countryFilterList = countryList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val countryListView =
                LayoutInflater.from(parent.context).inflate(R.layout.activity_incomplete_list, parent, false)
        val sch = CountryHolder(countryListView)
        mcontext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return countryFilterList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var textViewName: TextView
        textViewName = holder.itemView.findViewById(R.id.textview_name)
        textViewName.text = countryFilterList[position]

        holder.itemView.setOnClickListener {
            Log.d("Selected:", countryFilterList[position])
        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    countryFilterList = countryList
                } else {
                    val resultList = ArrayList<String>()
                    for (row in countryList) {
                        if (row.toLowerCase().contains(charSearch.toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    countryFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryFilterList = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }

        }
    }

}


/*

class EmployeeDisplayAdapter() : RecyclerView.Adapter<EmployeeDisplayAdapter.ViewHolder>(), Filterable {
    var mContext: Context? = null
    var formId = ""
    private lateinit var mOnItemClickListener: OnItemClickListener
    var mWomenList: ArrayList<String>? = null
    var mWomenListFilterList: List<completeFiledForm>? = null

    constructor(mContext: Context, mWomenList: ArrayList<String>, formID: String, mOnItemClickListener: OnItemClickListener) : this() {
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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, i: Int) {
       // holder.bindData(mWomenList!![i])
        var textViewName: TextView
        holder.itemView.textViewName.text = mWomenList!![i]

    }


    override fun getItemCount(): Int {
        return mWomenList!!.size
    }

    fun swapDataList(womenList: ArrayList<String>) {
        mWomenList = womenList
    }
*/
/*

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView
        fun bindData(listModel: completeFiledForm?) {
            if (listModel != null) {
                textViewName.text = listModel.name
            }
        }
*//*


        */
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
        }*//*


        init {
            textViewName = itemView.findViewById(R.id.textview_name)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    mWomenListFilterList = mWomenList
                }else {
                    val resultList = ArrayList<String>()
                    for (row in mWomenList!!) {
                        if (row.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    mWomenListFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = mWomenListFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                mWomenListFilterList = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }

        }
    }
}
*/
