package com.example.levelty.presenter.adapters.parent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.domain.models.Task
import com.google.android.material.chip.Chip

class TasksProfileFragmentAdapter(val taskList: List<Task>): RecyclerView.Adapter<TasksProfileFragmentAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_profile_task_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val taskName: Chip = itemView.findViewById(R.id.chip_fragment_profile_task)

        fun onBind(task: Task){
            taskName.text = task.taskName
        }
    }
}