package com.example.levelty.presenter.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.google.android.material.chip.Chip

class InterestsProfileFragmentAdapter(private val interestsList: List<String>): RecyclerView.Adapter<InterestsProfileFragmentAdapter.ViewHolder>() {
//    class InterestsProfileFragmentAdapter(val interestsList: List<Interest>): RecyclerView.Adapter<InterestsProfileFragmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_profile_interest_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(interestsList[position])
    }

    override fun getItemCount(): Int {
        return interestsList.size
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val interestName: Chip = itemView.findViewById(R.id.chip_fragment_profile_interest)

//        fun onBind(interest: Interest){
//            interestName.text = interest.interestName
//        }

        fun onBind(interest: String){
            interestName.text = interest
        }
    }
}