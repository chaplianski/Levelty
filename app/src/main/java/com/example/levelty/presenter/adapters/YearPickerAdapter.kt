package com.example.levelty.presenter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.domain.models.DateTask

class YearPickerAdapter(
    private val context: Context,
    private var yearList: List<Int>,
    private val recyclerView: RecyclerView?
) :
    RecyclerView.Adapter<YearPickerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View
        val inflater = LayoutInflater.from(context)
        view = inflater.inflate(R.layout.fragment_date_choose_year_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //      holder.pickerTxt.text = dataList[position]
        holder.onBind(yearList[position])


        holder.yearText.setOnClickListener { recyclerView?.smoothScrollToPosition(position) }
    }

    override fun getItemCount(): Int {
        return yearList.size
    }

    fun updateList(newList: List<Int>){
        yearList = newList
        notifyDataSetChanged()
    }

    fun swapData(newData: List<Int>) {
        yearList = newData
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val yearText: TextView = itemView.findViewById(R.id.tv_fragment_date_choose_year)

        fun onBind(year: Int) {
            if (year.toString().length == 1){
                yearText.text = String.format("%02d", year)
            } else
            yearText.text = year.toString()

        }
    }
}
