package com.example.levelty.presenter.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.domain.models.DateTask


class PickerAdapter(
    private val context: Context,
    private var dataList: List<DateTask>,
    private val recyclerView: RecyclerView?
) :
    RecyclerView.Adapter<PickerAdapter.ViewHolder>() {

    interface PickerDateListener{
        fun onItemClick(position: Int, dateTask: DateTask)
    }
    var pickerDateListener: PickerDateListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View
        val inflater = LayoutInflater.from(context)
        view = inflater.inflate(R.layout.date_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

      holder.onBind(dataList[position])
      holder.numberText.setOnClickListener { recyclerView?.smoothScrollToPosition(position) }
      holder.itemView.setOnClickListener {
          pickerDateListener?.onItemClick(position, dataList[position])

      }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val numberText: TextView = itemView.findViewById(R.id.tv_date_item_number)
        val monthText: TextView = itemView.findViewById(R.id.tv_date_item_month)
        val yearText: TextView = itemView.findViewById(R.id.tv_date_item_year)

        fun onBind(dateTask: DateTask){
            numberText.text = dateTask.day
            monthText.text = dateTask.month
            yearText.text = dateTask.year
        }
    }
}
