package com.example.levelty.presenter.adapters.parent

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.google.android.material.chip.Chip

class SampleStringChipsAdapter(val itemsList: List<String>, beginPosition: Int): RecyclerView.Adapter<SampleStringChipsAdapter.ViewHolder>() {

    var checkedPosition = beginPosition

    interface ChipClickListener{
        fun clickChip(item: String, position: Int)
    }

    var chipClickListener: ChipClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_string_chip, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (checkedPosition == position){
            holder.itemName.chipBackgroundColor =
                holder.itemView.resources.getColorStateList(R.color.graphic_accent)
            holder.itemName.setTextColor(Color.WHITE)

        } else {
            holder.itemName.chipBackgroundColor =
                holder.itemView.resources.getColorStateList(R.color.background_secondary)
            holder.itemName.setTextColor(Color.BLACK)
        }


        holder.onBind(itemsList[position])
        holder.itemName.setOnClickListener {
            this.checkedPosition = holder.absoluteAdapterPosition
            notifyDataSetChanged()
            chipClickListener?.clickChip(itemsList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val itemName: Chip = itemView.findViewById(R.id.chip_item_string_chip)

        fun onBind(item: String){
            itemName.text = item
        }

    }
}