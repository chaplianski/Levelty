package com.example.levelty.presenter.adapters.kid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.levelty.R
import com.example.levelty.domain.models.GoalsItem

class KidSetGoalsAdapter(private val listGoals: List<GoalsItem>): RecyclerView.Adapter<KidSetGoalsAdapter.ViewHolder>() {

    interface OnClickGoalLiastener{
        fun onClickAddGoal()
        fun onClickGoal(goalsItem: GoalsItem)
    }
    var onclickGoalListener: OnClickGoalLiastener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_kid_set_goal_square, parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(listGoals[position])
        holder.itemView.setOnClickListener {
            if (position == 0){
                onclickGoalListener?.onClickAddGoal()
            } else{
                onclickGoalListener?.onClickGoal(listGoals[position])
            }

        }
    }

    override fun getItemCount(): Int {
        return listGoals.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val goalName = itemView.findViewById<TextView>(R.id.tv_item_kid_set_goal_name)
        val goalImage = itemView.findViewById<ImageView>(R.id.iv_item_kid_set_goal)

        fun onBind(goalsItem: GoalsItem){
            goalName.text = goalsItem.title

            if (goalsItem.title == "Add yours"){
                Glide.with(itemView.context).load(R.drawable.ic_big_plus)
                    .override(68, 68)
                    .centerCrop()
                    .circleCrop()
                    .into(goalImage)
            } else {
                Glide.with(itemView.context).load(R.drawable.saly12)
                    .override(68, 68)
                    .centerCrop()
                    .circleCrop()
                    .into(goalImage)
            }

        }
    }
}