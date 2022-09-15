package com.example.levelty.presenter.adapters.parent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.domain.models.ParentProcessedTask

class CategoryFragmentAdapter(val taskList: List<ParentProcessedTask>): RecyclerView.Adapter<CategoryFragmentAdapter.ViewHolder>() {

    interface CategoryClickListener{
        fun onItemClick(parentProcessedTask: ParentProcessedTask)
    }

    var categoryClickListener: CategoryClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

                val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_categories_task_item, parent, false)
                return  ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

                holder.onBind(taskList[position])
                holder.taskNumber.text = "Task ${position + 1}"
                holder.itemView.setOnClickListener {
                    categoryClickListener?.onItemClick(taskList[position])
//                    val navController = holder.itemView.let { Navigation.findNavController(it) }
//                    navController.navigate(R.id.action_categoryFragment_to_editTaskFragment)
                }
    }


    override fun getItemCount(): Int {
        return taskList.size
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        val taskName: TextView = itemView.findViewById(R.id.tv_categories_fragment_task_name)
        val taskCoins: TextView = itemView.findViewById(R.id.tv_categories_fragment_task_coins)
        val taskNumber: TextView = itemView.findViewById(R.id.tv_categories_fragment_task_number)

        fun onBind(task: ParentProcessedTask){
            taskName.text = task.title
            taskCoins.text = "${task.cost} coins"


        }
    }



}