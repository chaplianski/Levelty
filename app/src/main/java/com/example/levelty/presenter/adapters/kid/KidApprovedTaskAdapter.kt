package com.example.levelty.presenter.adapters.kid

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.domain.models.KidProcessedTask
import com.google.android.material.chip.Chip

class KidApprovedTaskAdapter(private val taskList: List<KidProcessedTask>): RecyclerView.Adapter<KidApprovedTaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_approved_task, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val taskText: Chip = itemView.findViewById(R.id.chip_kid_approved_task)

        fun onBind(task: KidProcessedTask){

            val taskValue = "${task.title}   +${task.cost} coins"
            val taskTitle = task.title

            val taskCost = SpannableString(taskValue)
            taskTitle?.length?.let {
                taskCost.setSpan(
                    ForegroundColorSpan(Color.parseColor("#806D51FF")),
                    it,
                    taskValue.length,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            }
            taskText.text = taskCost
        }
    }
}