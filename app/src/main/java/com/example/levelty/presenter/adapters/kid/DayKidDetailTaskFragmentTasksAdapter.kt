package com.example.levelty.presenter.adapters.kid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.domain.models.Task

class DayKidDetailTaskFragmentTasksAdapter (
    val taskList: List<Task>, private val recyclerView: RecyclerView): RecyclerView.Adapter<DayKidDetailTaskFragmentTasksAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_day_kid_detail_tasks_item, parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(taskList[position])
        holder.taskNameText.setOnClickListener { recyclerView.smoothScrollToPosition(position) }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val taskNameText: TextView = itemView.findViewById(R.id.tv_fragment_day_kid_detail_tasks_name)
        val rewardValueText: TextView = itemView.findViewById(R.id.tv_fragment_day_kid_detail_tasks_reward)

        fun onBind(task: Task){

            taskNameText.text = task.taskName
            rewardValueText.text = if (task.taskPoints == 1){
                "${task.taskPoints} coin"
            }else{
                "${task.taskPoints} coins"
            }
        }

    }
}