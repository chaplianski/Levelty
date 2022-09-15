package com.example.levelty.presenter.adapters.kid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.domain.models.CreatedTasksItem
import com.example.levelty.domain.models.KidProcessedTask
import com.example.levelty.domain.models.Task
import com.example.levelty.presenter.adapters.kid.KidGoalsWheelAdapter.Companion.DECLINED_STATUS
import com.example.levelty.presenter.adapters.kid.KidGoalsWheelAdapter.Companion.NORMAL_STATUS
import com.example.levelty.presenter.adapters.kid.KidGoalsWheelAdapter.Companion.WRONG_STATUS
import kotlinx.coroutines.NonDisposableHandle.parent

class KidDayTasksFragmentAdapter (
//    val taskList: List<KidProcessedTask>, private val recyclerView: RecyclerView): RecyclerView.Adapter<KidDayTasksFragmentAdapter.ViewHolder>() {
    val taskList: List<KidProcessedTask>, private val recyclerView: RecyclerView): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    interface CheckTaskElementListener {
        fun clickOnDoneButton(task: KidProcessedTask)
        fun clickOnSkipButton(task: KidProcessedTask)
        fun clickOnCancelTaskButton(task: KidProcessedTask)
        fun clickOnRedoButton(task: KidProcessedTask)
    }

    var checkTaskElementListener: CheckTaskElementListener? = null

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = when (viewType){
            NORMAL_STATUS -> R.layout.item_fragment_kid_day_task
            WAITING_FOR_APPROVAL_STATUS -> R.layout.item_fragment_kid_day_task_wait_approval
            APPROVED_STATUS -> R.layout.item_fragment_kid_day_task_approved
            SKIPPED_STATUS -> R.layout.item_fragment_kid_day_task_skipped
            DECLINED_STATUS -> R.layout.item_fragment_kid_day_task_declined
            else -> R.layout.item_fragment_kid_day_task
        }
        val v = LayoutInflater.from(parent.context).inflate(layout, parent,false)
//        return ViewHolder(v)
        return when (viewType){
            NORMAL_STATUS -> NormalStatusViewHolder(v)
            WAITING_FOR_APPROVAL_STATUS -> WaitApprovalStatusViewHolder(v)
            APPROVED_STATUS -> ApprovedStatusViewHolder(v)
            SKIPPED_STATUS -> SkippedStatusViewHolder(v)
            DECLINED_STATUS -> DeclinedStatusViewHolder(v)
            else -> NormalStatusViewHolder(v)
        }



//        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_fragment_kid_day_task, parent,false)
//        return ViewHolder(v)
    }

//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (taskList[position].choreStatus){
            "pending" -> {
                (holder as NormalStatusViewHolder).onBind(taskList[position])
                holder.taskNameText.setOnClickListener { recyclerView.smoothScrollToPosition(position) }
                holder.doneButton.setOnClickListener {
                    checkTaskElementListener?.clickOnDoneButton(taskList[position])
                }
                holder.skipButton.setOnClickListener {
                    checkTaskElementListener?.clickOnSkipButton(taskList[position])
                }
            }
            "waiting_for_approval" -> {
                (holder as WaitApprovalStatusViewHolder).onBind(taskList[position])
                holder.cancelButton.setOnClickListener {
                    checkTaskElementListener?.clickOnCancelTaskButton(taskList[position])
                }

            }
            "done" -> {
                (holder as ApprovedStatusViewHolder).onBind(taskList[position])
            }
            "rejected" -> {
                (holder as SkippedStatusViewHolder).onBind(taskList[position])
                holder.redoButton.setOnClickListener {
                    checkTaskElementListener?.clickOnRedoButton(taskList[position])
                }
            }
            "refused" -> {
                (holder as DeclinedStatusViewHolder).onBind(taskList[position])
                holder.redoButton.setOnClickListener {
                    checkTaskElementListener?.clickOnRedoButton(taskList[position])
                }
            }
            else -> {}
        }


//        holder.onBind(taskList[position])
//        holder.taskNameText.setOnClickListener { recyclerView.smoothScrollToPosition(position) }

    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (taskList[position].choreStatus){
            "pending" -> NORMAL_STATUS
            "waiting_for_approval" -> WAITING_FOR_APPROVAL_STATUS
            "done" -> APPROVED_STATUS
            "rejected" -> SKIPPED_STATUS
            "refused" -> DECLINED_STATUS
            else -> WRONG_STATUS
        }
    }

//    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//
//        val taskNameText: TextView = itemView.findViewById(R.id.tv_fragment_day_kid_detail_tasks_name)
//        val rewardValueText: TextView = itemView.findViewById(R.id.tv_fragment_day_kid_detail_tasks_reward)
//        val doneButton: Button = itemView.findViewById(R.id.bt_fragment_day_kid_detail_tasks_done)
//        val skipButton: TextView = itemView.findViewById(R.id.tv_fragment_day_kid_detail_tasks_skip)
//
//        fun onBind(task: Task){
//
//            taskNameText.text = task.taskName
//            rewardValueText.text = if (task.taskPoints == 1){
//                "${task.taskPoints} coin"
//            }else{
//                "${task.taskPoints} coins"
//            }
//        }
//
//    }

    class NormalStatusViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val taskNameText: TextView = itemView.findViewById(R.id.tv_fragment_day_kid_detail_tasks_name)
        val costValueText: TextView = itemView.findViewById(R.id.tv_fragment_day_kid_detail_tasks_reward)
        val doneButton: Button = itemView.findViewById(R.id.bt_fragment_day_kid_detail_tasks_done)
        val skipButton: TextView = itemView.findViewById(R.id.tv_fragment_day_kid_detail_tasks_skip)

        fun onBind(task: KidProcessedTask){

            taskNameText.text = task.title
            costValueText.text = if (task.cost == 1){
                "${task.cost} coin"
            }else{
                "${task.cost} coins"
            }
        }
    }

    class WaitApprovalStatusViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val taskNameText: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_day_task_wait_approval_name)
        val costValueText: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_day_task_wait_approval_price)
        val cancelButton: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_day_task_wait_approval_cancel)

        fun onBind(task: KidProcessedTask){

            taskNameText.text = task.title
            costValueText.text = if (task.cost == 1){
                "${task.cost} coin"
            }else{
                "${task.cost} coins"
            }
        }
    }

    class ApprovedStatusViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val taskNameText: TextView = itemView.findViewById(R.id.tv_item_fragment_day_kid_approved_name)
        val costValueText: TextView = itemView.findViewById(R.id.tv_item_fragment_day_kid_approved_cost)

        fun onBind(task: KidProcessedTask){

            taskNameText.text = task.title
            costValueText.text = if (task.cost == 1){
                "${task.cost} coin"
            }else{
                "${task.cost} coins"
            }
        }
    }

    class SkippedStatusViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val taskNameText: TextView = itemView.findViewById(R.id.tv_item_fragment_day_kid_tasks_skipped_name)
        val costValueText: TextView = itemView.findViewById(R.id.tv_item_fragment_day_kid_tasks_skipped_cost)
        val redoButton: TextView = itemView.findViewById(R.id.tv_item_fragment_day_kid_tasks_skipped_redo)

        fun onBind(task: KidProcessedTask){

            taskNameText.text = task.title
            costValueText.text = if (task.cost == 1){
                "${task.cost} coin"
            }else{
                "${task.cost} coins"
            }
        }
    }

    class DeclinedStatusViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val taskNameText: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_day_task_declined_name)
        val costValueText: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_day_task_declined_cost)
        val redoButton: TextView = itemView.findViewById(R.id.tv_item_fragment_kid_day_task_declined_redo)

        fun onBind(task: KidProcessedTask){

            taskNameText.text = task.title
            costValueText.text = if (task.cost == 1){
                "${task.cost} coin"
            }else{
                "${task.cost} coins"
            }
        }
    }


    companion object {
        val NORMAL_STATUS = 0
        val WAITING_FOR_APPROVAL_STATUS = 1
        val APPROVED_STATUS = 2
        val SKIPPED_STATUS = 3
        val DECLINED_STATUS = 4
        val WRONG_STATUS = 5
    }
}