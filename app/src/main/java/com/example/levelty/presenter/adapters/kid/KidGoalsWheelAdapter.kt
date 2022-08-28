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
import com.example.levelty.domain.models.GoalsItem
import com.example.levelty.presenter.adapters.kid.KidDayTasksFragmentAdapter.Companion.DECLINED_STATUS
import com.example.levelty.presenter.adapters.kid.KidDayTasksFragmentAdapter.Companion.NORMAL_STATUS
import kotlinx.coroutines.NonDisposableHandle.parent

class KidGoalsWheelAdapter(
val goalList: List<Goal>, private val recyclerView: RecyclerView):
    RecyclerView.Adapter<KidGoalsWheelAdapter.ViewHolder>() {
//    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    interface GoalCardListener{
        fun onChangeButtonClick()
        fun onGetButtonClick()
        fun onCancelButtonClick(goal: GoalsItem)
        fun onChooseButtonClick()
    }

    val goalCardListener: GoalCardListener? = null
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = when (viewType){
            NORMAL_STATUS -> R.layout.item_fragment_kid_goals
            COMPLETED_STATUS -> R.layout.item_fragment_kid_goals_completed
            LOCKED_STATUS -> R.layout.item_fragment_kid_goals_locked
            WAITING_FOR_APPRUVAL_STATUS -> R.layout.item_fragment_kid_goals_wait_approval
            CHOOSE_GOAL_STATUS -> R.layout.item_fragment_kid_goals_choose
            DECLINED_STATUS -> R.layout.item_fragment_kid_goals_declined
            else -> R.layout.item_fragment_kid_goals
        }
//        val v = LayoutInflater.from(parent.context).inflate(layout, parent,false)
//        return when (viewType){
//            NORMAL_STATUS -> KidDayTasksFragmentAdapter.NormalStatusViewHolder(v)
//            COMPLETED_STATUS -> CompletedStatusViewHolder(v)
//            LOCKED_STATUS -> LockedStatusViewHolder(v)
//            WAITING_FOR_APPRUVAL_STATUS -> WaitingForApprovalStatusViewHolder(v)
//            CHOOSE_GOAL_STATUS -> ChooseGoalStatusViewHolder(v)
//            DECLINED_STATUS -> DeclineStatusViewHolder(v)
//            else -> KidDayTasksFragmentAdapter.NormalStatusViewHolder(v)
//        }



        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_fragment_kid_goals, parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        when (goalList[position].status){
//            "normal" -> {
//                (holder as KidDayTasksFragmentAdapter.NormalStatusViewHolder).onBind(goalList[position])
//                holder.changeButton.setOnClickListener {
//                    goalCardListener?.onChangeButtonClick()
//                }
//                holder.getButton.setOnClickListener {
//                    goalCardListener?.onGetButtonClick()
//                }
//            }
//            "completed" -> {
//                (holder as CompletedStatusViewHolder).onBind(goalList[position])
//            }
//            "locked" -> {
//                (holder as LockedStatusViewHolder).onBind(goalList[position])
//            }
//            "waiting for approval" -> {
//                (holder as WaitingForApprovalStatusViewHolder).onBind(goalList[position])
//                holder.cancelButton.setOnClickListener {
//                    goalCardListener?.onCancelButtonClick(goalList[position])
//                }
//            }
//            "choose_goal_status" -> {
//                (holder as ChooseGoalStatusViewHolder).chooseButton.setOnClickListener {
//                    goalCardListener?.onChooseButtonClick()
//                }
//            }
//            "declined" -> {
//                (holder as DeclineStatusViewHolder).onBind(goalList[position])
//                holder.chooseNewGoalButton.setOnClickListener {
//                    goalCardListener?.onChooseButtonClick()
//                }
//
//            }
//            else -> {}
//        }

        holder.onBind(goalList[position])
        holder.itemView.setOnClickListener { recyclerView.smoothScrollToPosition(position) }
        holder.changeButton.setOnClickListener {
        }
        holder.getButton.setOnClickListener {

        }

    }

    override fun getItemCount(): Int {
        return goalList.size
    }

//    override fun getItemViewType(position: Int): Int {
//        return when (goalList[position].status){
//            "normal" -> NORMAL_STATUS
//            "completed" -> COMPLETED_STATUS
//            "locked" -> LOCKED_STATUS
//            "waiting for approval" -> WAITING_FOR_APPRUVAL_STATUS
//            "choose_goal_status" -> CHOOSE_GOAL_STATUS
//            "declined" -> DECLINED_STATUS
//            else -> WRONG_STATUS
//        }
//    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val goalImage: ImageView = itemView.findViewById(R.id.iv_item_fragment_kid_goals)
        val goalName: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_goals_name)
        val goalCost: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_goals_cost)
        val getButton: Button = itemView.findViewById(R.id.bt_item_fragment_kid_goals_get)
        val changeButton: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_goals_change)

        fun onBind(goal: Goal){

            goalName.text = goal.goalName
            goalCost.text = goal.goalValue.toString()
        }
    }

    class NormalStatusViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        val goalImage: ImageView = itemView.findViewById(R.id.iv_item_fragment_kid_goals)
        val goalName: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_goals_name)
        val goalCost: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_goals_cost)
        val getButton: Button = itemView.findViewById(R.id.bt_item_fragment_kid_goals_get)
        val changeButton: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_goals_change)

        fun onBind(goal: GoalsItem){

            goalName.text = goal.title
            goalCost.text = goal.price.toString()
        }
    }

    class CompletedStatusViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        val goalImage: ImageView = itemView.findViewById(R.id.iv_item_fragment_kid_goals_completed)
        val goalName: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_goals_completed_name)
        val goalCost: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_goals_completed_cost)

        fun onBind(goal: GoalsItem){

            goalName.text = goal.title
            goalCost.text = goal.price.toString()
        }
    }

    class LockedStatusViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        val goalImage: ImageView = itemView.findViewById(R.id.iv_item_fragment_kid_goals_completed)
        val lockedMessage: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_goals_locked_message)

        fun onBind(goal: GoalsItem){
            lockedMessage.text = "Reach Level 3 to unlock the slot"
        }
    }

    class WaitingForApprovalStatusViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //        val goalImage: ImageView = itemView.findViewById(R.id.iv_item_fragment_kid_goals_completed)
        val goalName: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_goals_wait_approval_name)
        val cancelButton: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_goals_wait_approval_cancel)

        fun onBind(goal: GoalsItem){
            goalName.text = goal.title
        }
    }

    class ChooseGoalStatusViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //        val goalImage: ImageView = itemView.findViewById(R.id.iv_item_fragment_kid_goals_completed)
        val chooseButton: Button = itemView.findViewById(R.id.bt_item_fragment_kid_goals_choose)
     }

    class DeclineStatusViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //        val goalImage: ImageView = itemView.findViewById(R.id.iv_item_fragment_kid_goals_completed)
        val goalName: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_goals_declined_name)
        val chooseNewGoalButton: Button = itemView.findViewById(R.id.bt_item_fragment_kid_goals_declined)

        fun onBind(goal: GoalsItem){
            goalName.text = goal.title
        }
    }

    companion object {
        val NORMAL_STATUS = 0
        val COMPLETED_STATUS = 1
        val LOCKED_STATUS = 2
        val WAITING_FOR_APPRUVAL_STATUS = 3
        val CHOOSE_GOAL_STATUS = 4
        val DECLINED_STATUS = 5
        val WRONG_STATUS = 6
    }

}