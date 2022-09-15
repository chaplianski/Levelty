package com.example.levelty.presenter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.domain.models.ParentProcessedTask
import java.lang.ref.WeakReference

class FragmentDayPersonalTasksAdapter (private val dayTasksList: List<ParentProcessedTask>, val checkedDay: String) : RecyclerView.Adapter<FragmentDayPersonalTasksAdapter.ViewHolder>(){



    interface ShortOnClickListener {
        fun ShortClick(task: ParentProcessedTask)
    }

    var shortOnClickListener: ShortOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_day_personal_tasks_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(dayTasksList[position])
        holder.itemView.setOnClickListener {
            shortOnClickListener?.ShortClick(dayTasksList[position])
        }
    }

//    private fun removeItem(viewHolder: RecyclerView.ViewHolder) {
//        tasksList.removeAt(viewHolder.position)
//        notifyItemRemoved(viewHolder.position)
//    }

    override fun getItemCount(): Int {
        return dayTasksList.size
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

        val taskName: TextView = itemView.findViewById(R.id.tv_fragment_day_personal_tasks_item_task)
        val taskCoins: TextView = itemView.findViewById(R.id.tv_fragment_day_personal_tasks_item_coins)
        val tasksMoney: TextView = itemView.findViewById(R.id.tv_fragment_day_personal_tasks_item_money)
        val taskStatus: TextView = itemView.findViewById(R.id.tv_fragment_day_personal_tasks_item_condition)
  //      val taskApprove: Chip = itemView.findViewById(R.id.chip_tv_fragment_day_personal_tasks_item_approve)
        val view = WeakReference(itemView)


        fun onBind(task: ParentProcessedTask){
            taskName.text = task.title
//            tasksMoney.text = "${(task.taskPoints*10).toString()}$ "
            taskCoins.text = "${task.cost} coins"
            taskStatus.text = task.choreStatus



        }
    }
}


