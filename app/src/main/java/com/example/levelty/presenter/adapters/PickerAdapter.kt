package com.example.levelty.presenter.adapters

import android.content.Context
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View
        val inflater = LayoutInflater.from(context)
        view = inflater.inflate(R.layout.date_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
  //      holder.pickerTxt.text = dataList[position]
      holder.onBind(dataList[position])


        holder.numbetText.setOnClickListener { recyclerView?.smoothScrollToPosition(position) }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun swapData(newData: List<DateTask>) {
        dataList = newData
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var pickerTxt: TextView
//
//        init {
//            pickerTxt = itemView.findViewById<View>(R.id.picker_item) as TextView
//        }

        val numbetText: TextView = itemView.findViewById(R.id.tv_date_item_number)
        val monthText: TextView = itemView.findViewById(R.id.tv_date_item_month)

        fun onBind(dateTask: DateTask){
            numbetText.text = dateTask.day
            monthText.text = dateTask.month
        }
    }
}
