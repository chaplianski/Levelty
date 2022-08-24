package com.example.levelty.presenter.adapters.kid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.domain.models.Goal
import com.example.levelty.domain.models.Task

class KidGoalsWheelAdapter(
val goalList: List<Goal>, private val recyclerView: RecyclerView):
    RecyclerView.Adapter<KidGoalsWheelAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_fragment_kid_goals, parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(goalList[position])
        holder.itemView.setOnClickListener { recyclerView.smoothScrollToPosition(position) }
    }

    override fun getItemCount(): Int {
        return goalList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val goalImage: ImageView = itemView.findViewById(R.id.iv_item_fragment_kid_goals)
        val goalName: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_goals_name)
        val goalCost: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_goals_reward)
        val getButton: Button = itemView.findViewById(R.id.bt_item_fragment_kid_goals_get)
        val changeButton: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_goals_change)

        fun onBind(goal: Goal){

            goalName.text = goal.goalName
            goalCost.text = goal.goalValue.toString()
        }
    }
}