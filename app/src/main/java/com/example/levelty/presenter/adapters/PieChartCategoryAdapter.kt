package com.example.levelty.presenter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.levelty.R
import com.github.mikephil.charting.data.PieEntry


class PieChartCategoryAdapter(val pieDataList: List<PieEntry>, val colors: List<Int>): RecyclerView.Adapter<PieChartCategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.pie_charts_legend_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(pieDataList[position], colors[position])
    }

    override fun getItemCount(): Int {
        return pieDataList.size
    }

   inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

        val colorIcon: ImageView = itemView.findViewById(R.id.iv_pie_charts_legend)
        val textItem: TextView = itemView.findViewById(R.id.tv_pie_charts_legend)



        fun onBind(pieEntry: PieEntry, color: Int){
            textItem.text = pieEntry.label
            val colorLegend = R.drawable.round
            colorIcon.setColorFilter(color)

            Glide.with(itemView.context).load(colorLegend)
                .override(16, 16)
                .centerCrop()
                .circleCrop()
                .into(colorIcon)

        }
    }
}