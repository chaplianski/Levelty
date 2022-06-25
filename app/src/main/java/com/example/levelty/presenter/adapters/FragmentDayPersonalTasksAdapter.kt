package com.example.levelty.presenter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.domain.models.Task
import java.lang.ref.WeakReference

class FragmentDayPersonalTasksAdapter (dayTasksList: List<Task>) : RecyclerView.Adapter<FragmentDayPersonalTasksAdapter.ViewHolder>(){

    val tasksList = dayTasksList as MutableList<Task>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_day_personal_tasks_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(tasksList[position])
    }

//    private fun removeItem(viewHolder: RecyclerView.ViewHolder) {
//        tasksList.removeAt(viewHolder.position)
//        notifyItemRemoved(viewHolder.position)
//    }

    override fun getItemCount(): Int {
        return tasksList.size
    }



    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

        val taskName: TextView = itemView.findViewById(R.id.tv_fragment_day_personal_tasks_item_task)
        val taskCoins: TextView = itemView.findViewById(R.id.tv_fragment_day_personal_tasks_item_coins)
        val tasksMoney: TextView = itemView.findViewById(R.id.tv_fragment_day_personal_tasks_item_money)
        val taskCondition: TextView = itemView.findViewById(R.id.tv_fragment_day_personal_tasks_item_condition)
  //      val taskApprove: Chip = itemView.findViewById(R.id.chip_tv_fragment_day_personal_tasks_item_approve)
        val view = WeakReference(itemView)


        fun onBind(task: Task){
            taskName.text = task.taskName
            tasksMoney.text = "${(task.taskPoints*10).toString()}$ "
            taskCoins.text = "${task.taskPoints.toString()} coins"

        }
    }
}


