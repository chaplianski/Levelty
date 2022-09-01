package com.example.levelty.presenter.adapters.parent

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.domain.models.Goal
import com.example.levelty.domain.models.GoalsItem

class KidGoalsFragmentAdapter  (val goals: List<GoalsItem>): RecyclerView.Adapter<KidGoalsFragmentAdapter.ViewHolder>() {

    interface ShortOnClickListener {
        fun ShortClick(goal: GoalsItem)
    }

    var shortOnClickListener: ShortOnClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_kids_goals_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(goals[position])

        holder.itemView.setOnClickListener {
            shortOnClickListener?.ShortClick(goals[position])
        }


    }

    override fun getItemCount(): Int {
        return goals.size
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        val goalNameText: TextView = itemView.findViewById(R.id.tv_fragment_kid_goal_name)
        val goalRewardText: TextView = itemView.findViewById(R.id.tv_fragment_kid_goal_reward)
        val goalLayout = itemView.findViewById<CardView>(R.id.cv_fragment_kid_goal_card)
//        val isApprovalButton: Chip = itemView.findViewById(R.id.chip_fragment_kid_goal_approval)
//        val goalImage: ImageView = itemView.findViewById(R.id.iv_fragment_profile_goal_image)

        fun onBind(goal: GoalsItem){
            goalNameText.text = goal.title
            goalRewardText.text = goal.price.toString()

//            val firstColor = ContextCompat.getColor(itemView.context, R.color.purple_500)
//            val secondColor = ResourcesCompat.getColor(Resources.getSystem(), R.color.purple_700, null)
//            val colorA = Color.parseColor("#2D98FB")
//            goalLayout.setBackgroundColor(Color.BLUE)

   //         val gr = GradientDrawable(GradientDrawable.OVAL, intArrayOf(firstColor, secondColor))
  //          gr.cornerRadius = 0f

 //           goalLayout.setBackgroundDrawable(gr)


//            Glide.with(itemView.context).load(R.drawable.kid_goal_image_1)
//                .override(68, 68)
//                .centerCrop()
//                .circleCrop()
//                .into(goalImage)
        }
    }
}