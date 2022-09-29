package com.example.levelty.presenter.adapters.parent

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R

class RepeatChooseFragmentAdapter(private val listRepeatString: List<String>) : RecyclerView.Adapter<RepeatChooseFragmentAdapter.ViewHolder>() {

    var checkedPosition = -1

    interface ShortOnClickListener {
        fun ShortClick(item: String, isLast: Boolean)
    }

    var shortOnClickListener: ShortOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.order_string_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var isLast = false
        holder.onBind(listRepeatString[position])
        holder.checkedField.isChecked = (checkedPosition == position)
        holder.itemView.setOnClickListener {
            if (position == listRepeatString.size-1) {
                isLast = true
            }
            shortOnClickListener?.ShortClick(listRepeatString[position], isLast)
            this.checkedPosition = holder.absoluteAdapterPosition
            notifyDataSetChanged()
        }


//        Log.d("MyLog", "checkedVariant = ${holder.checkedField.isChecked}")

    }

    override fun getItemCount(): Int {
        return listRepeatString.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val textListItem: TextView = itemView.findViewById(R.id.tv_order_string_list_item)
        val checkedField: CheckBox = itemView.findViewById(R.id.cb_order_string_list_item)

        fun onBind(item: String){
            textListItem.text = item
        }

    }


}