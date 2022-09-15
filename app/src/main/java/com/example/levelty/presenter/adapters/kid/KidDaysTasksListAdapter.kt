package com.example.levelty.presenter.adapters.kid

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.domain.models.KidProcessedTask
import com.example.levelty.domain.models.ParentProcessedTask
import com.example.levelty.presenter.adapters.FragmentDayPersonalTasksAdapter
import java.lang.ref.WeakReference

class KidDaysTasksListAdapter (
    private val dayTasksList: List<KidProcessedTask>
    ) : RecyclerView.Adapter<KidDaysTasksListAdapter.ViewHolder>(){

        interface ShortOnClickListener {
            fun ShortClick(task: KidProcessedTask)
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

            val taskName: TextView =
                itemView.findViewById(R.id.tv_fragment_day_personal_tasks_item_task)
            val taskCoins: TextView =
                itemView.findViewById(R.id.tv_fragment_day_personal_tasks_item_coins)
            val tasksMoney: TextView =
                itemView.findViewById(R.id.tv_fragment_day_personal_tasks_item_money)
            val taskStatus: TextView =
                itemView.findViewById(R.id.tv_fragment_day_personal_tasks_item_condition)

            //      val taskApprove: Chip = itemView.findViewById(R.id.chip_tv_fragment_day_personal_tasks_item_approve)
            val view = WeakReference(itemView)


            fun onBind(task: KidProcessedTask) {
                taskName.text = task.title
//            tasksMoney.text = "${(task.taskPoints*10).toString()}$ "
                taskCoins.text = "${task.cost} coins"
                taskStatus.text = task.choreStatus
                Log.d("MyLog", "task status = ${task.choreStatus}")
                Log.d("MyLog", "task = ${task}")

                when (task.choreStatus) {
                    "done" -> taskStatus.setTextColor(Color.GREEN)
                    "waiting_for_approval" -> taskStatus.setTextColor(Color.BLUE)
                    "rejected" -> taskStatus.setTextColor(Color.GRAY)
                    "refused" -> taskStatus.setTextColor(Color.RED)
                    else -> taskStatus.setTextColor(Color.GRAY)
                }
                taskStatus.text = when (task.choreStatus) {
                    "done" -> "Approved"
                    "waiting_for_approval" -> "Waiting for approval"
                    "rejected" -> "Skipped"
                    "refused" -> "Declined"
                    else -> "Pending"
                }
            }
        }
    }