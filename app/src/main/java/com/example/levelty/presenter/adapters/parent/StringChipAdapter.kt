package com.example.levelty.presenter.adapters.parent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.google.android.material.chip.Chip

class StringChipAdapter (
//    val taskList: List<ParentProcessedTask>
    val taskList: List<String>
    ): RecyclerView.Adapter<StringChipAdapter.ViewHolder>() {


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

//        fun onBind(task: ParentProcessedTask){
//            taskName.text = task.title
//        }

        fun onBind(task: String){
            taskName.text = task
        }
    }
}