package com.example.levelty.presenter.adapters.parent

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R

class OrderStringAdapter (private val listString: List<String>) : RecyclerView.Adapter<OrderStringAdapter.ViewHolder>() {

    var checkedPosition = -1
    interface ShortOnClickListener {
        fun ShortClick(item: String)
    }

    var shortOnClickListener: ShortOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.order_string_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(listString[position])
        holder.checkedField.isChecked = (checkedPosition == position)
        holder.itemView.setOnClickListener {
            shortOnClickListener?.ShortClick(listString[position])
            this.checkedPosition = holder.absoluteAdapterPosition
            notifyDataSetChanged()
        }



//        if (holder.checkedField.isChecked){
//            checkedText = holder.textListItem.text.toString()
//
//        }
        Log.d("MyLog", "checkedVariant = ${holder.checkedField.isChecked}")

    }

    override fun getItemCount(): Int {
        return listString.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val textListItem: TextView = itemView.findViewById(R.id.tv_order_string_list_item)
        val checkedField: CheckBox = itemView.findViewById(R.id.cb_order_string_list_item)

        fun onBind(item: String){

            textListItem.text = item


        }

    }
}