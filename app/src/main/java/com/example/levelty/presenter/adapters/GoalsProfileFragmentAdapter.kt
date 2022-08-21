package com.example.levelty.presenter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.domain.models.Goal
import com.google.android.material.chip.Chip

class GoalsProfileFragmentAdapter(val goals: List<Goal>): RecyclerView.Adapter<GoalsProfileFragmentAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_kids_goals_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(goals[position])
    }

    override fun getItemCount(): Int {
        return goals.size
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        val goalNameText: TextView = itemView.findViewById(R.id.tv_fragment_kid_goal_name)
        val goalRewardText: TextView = itemView.findViewById(R.id.tv_fragment_kid_goal_reward)
//        val isApprovalButton: Chip = itemView.findViewById(R.id.chip_fragment_profile_goal_approval)
//        val goalImage: ImageView = itemView.findViewById(R.id.iv_fragment_profile_goal_image)

        fun onBind(goal: Goal){
            goalNameText.text = goal.goalName
            goalRewardText.text = goal.goalValue.toString()
//            Glide.with(itemView.context).load(R.drawable.kid_goal_image_1)
//                .override(68, 68)
//                .centerCrop()
//                .circleCrop()
//                .into(goalImage)
        }
    }
}