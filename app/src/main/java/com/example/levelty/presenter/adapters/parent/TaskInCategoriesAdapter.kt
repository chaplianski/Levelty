package com.example.levelty.presenter.adapters.parent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.domain.models.Task

class TaskInCategoriesAdapter(val taskList: List<Task>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            R.layout.fragment_task_in_categories_item_add -> {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_task_in_categories_item_add, parent, false)
                TaskViewHolder(v)
            }
            else -> {
                val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_task_in_categories_item, parent, false)
                AddTaskViewHolder(v)
            }

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)){
            R.layout.fragment_task_in_categories_item_add -> {
                (holder as AddTaskViewHolder).itemView.setOnClickListener {
                    val navController = holder.itemView.let { Navigation.findNavController(it) }
                    navController.navigate(R.id.action_taskInCategoriesFragment_to_newTaskFragment)
                }
            }
            else -> {
                (holder as TaskViewHolder).onBind(taskList[position])
                holder.taskNumber.text = position.toString()
                holder.itemView.setOnClickListener {
                    val navController = holder.itemView.let { Navigation.findNavController(it) }
                    navController.navigate(R.id.action_taskInCategoriesFragment_to_tasksFragment)

                }

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position){
            0 -> R.layout.fragment_task_in_categories_item
            else -> R.layout.fragment_task_in_categories_item
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class TaskViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        val taskName: TextView = itemView.findViewById(R.id.tv_fragment_task_in_categories_task_name)
        val taskCoins: TextView = itemView.findViewById(R.id.tv_fragment_task_in_categories_coins)
        val taskNumber: TextView = itemView.findViewById(R.id.tv_fragment_task_in_categories_task_number)


        fun onBind(task: Task){
            taskName.text = task.taskName
            taskCoins.text = "${task.taskPoints} coins"

        }

    }

    class AddTaskViewHolder  (itemView: View): RecyclerView.ViewHolder(itemView){

    }
}