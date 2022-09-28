package com.example.levelty.presenter.adapters.parent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.domain.models.GoalsItem
import com.google.android.material.chip.Chip

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
            if (goals[position].status == "waiting_for_approval"){
                shortOnClickListener?.ShortClick(goals[position])
            }

        }


    }

    override fun getItemCount(): Int {
        return goals.size
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        val goalNameText: TextView = itemView.findViewById(R.id.tv_item_parent_kid_goal_name)
        val goalCoinsText: TextView = itemView.findViewById(R.id.tv_item_parent_kid_goal_coins)
        val goalHelpLayout = itemView.findViewById<TextView>(R.id.layout_item_fragment_kid_goals)
        val goalStatus = itemView.findViewById<Chip>(R.id.chip_item_parent_kid_goal_status)
        val goalCoinsImage = itemView.findViewById<ImageView>(R.id.iv_item_parent_kid_goal_coins)
//        val isApprovalButton: Chip = itemView.findViewById(R.id.chip_fragment_kid_goal_approval)
//        val goalImage: ImageView = itemView.findViewById(R.id.iv_fragment_profile_goal_image)

        fun onBind(goal: GoalsItem){
            goalNameText.text = goal.title
            goalCoinsText.text = goal.price.toString()

       when (goal.status){
           "waiting_for_approval" -> {
               val param = goalNameText.layoutParams as ViewGroup.MarginLayoutParams
               param.topMargin = 50
               goalNameText.layoutParams = param
//               goalStatus.layoutParams = param

//               goalHelpLayout.visibility = View.VISIBLE
               goalStatus.visibility = View.VISIBLE
               goalCoinsText.visibility = View.INVISIBLE
               goalCoinsImage.visibility = View.INVISIBLE
           }
           else -> {
//               goalHelpLayout.visibility = View.INVISIBLE
//               goalStatus.visibility = View.INVISIBLE
               goalCoinsText.visibility = View.VISIBLE
               goalCoinsImage.visibility = View.VISIBLE
           }
       }
//            val firstColor = ContextCompat.getColor(itemView.context, R.color.purple_500)
//            val secondColor = ResourcesCompat.getColor(Resources.getSystem(), R.color.purple_700, null)
//            val colorA = Color.parseColor("#2D98FB")
//            goalLayout.setBackgroundColor(Color.BLUE)

   //         val gr = GradientDrawable(GradientDrawable.OVAL, intArrayOf(firstColor, secondColor))
  //          gr.cornerRadius = 0f

 //           goalLayout.setBackgroundDrawable(gr)

//
//            Glide.with(itemView.context).load(R.drawable.ic_coins)
//                .override(50, 120)
//                .centerCrop()
//                .circleCrop()
//                .into(goalCoinsImage)
        }
    }
}