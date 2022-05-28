package com.example.levelty.presenter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.domain.models.Purpose
import com.google.android.material.chip.Chip

class PurposeProfileFragmentAdapter(val purposeList: List<Purpose>): RecyclerView.Adapter<PurposeProfileFragmentAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_profile_purpose_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(purposeList[position])
    }

    override fun getItemCount(): Int {
        return purposeList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val purposeName: Chip = itemView.findViewById(R.id.chip_fragment_profile_purpose)

        fun onBind(purpose: Purpose){
            purposeName.text = purpose.purposeName
        }

    }
}