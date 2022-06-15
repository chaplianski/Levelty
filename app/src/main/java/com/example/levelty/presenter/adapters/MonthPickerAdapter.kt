package com.example.levelty.presenter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R

class MonthPickerAdapter(
    private val context: Context,
    private var monthList: List<String>,
    private val recyclerView: RecyclerView?
) :
    RecyclerView.Adapter<MonthPickerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View
        val inflater = LayoutInflater.from(context)
        view = inflater.inflate(R.layout.fragment_date_choose_month_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //      holder.pickerTxt.text = dataList[position]
        holder.onBind(monthList[position])
        holder.monthText.setOnClickListener { recyclerView?.smoothScrollToPosition(position) }
    }

    override fun getItemCount(): Int {
        return monthList.size
    }

    fun swapData(newData: List<String>) {
        monthList = newData
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val monthText: TextView = itemView.findViewById(R.id.tv_fragment_date_choose_month)

        fun onBind(month: String) {
            monthText.text =  month

        }
    }


}