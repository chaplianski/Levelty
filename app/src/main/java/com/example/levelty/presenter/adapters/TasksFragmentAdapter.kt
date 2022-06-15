package com.example.levelty.presenter.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.levelty.R
import com.example.levelty.domain.models.Task
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel


class TasksFragmentAdapter (val tasksList: List<Task>): RecyclerView.Adapter<TasksFragmentAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_tasks_item, parent,false)

        v.viewTreeObserver
            .addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    val lp: ViewGroup.LayoutParams = v.layoutParams
                    if (lp is StaggeredGridLayoutManager.LayoutParams) {
                        val sglp = lp
                        when (viewType) {
                            TYPE_FULL -> sglp.isFullSpan = true
                            TYPE_HALF -> {
                                sglp.isFullSpan = false
                                sglp.width = v.getWidth()
                                sglp.setMargins(2,0,2,8)
                            }
                            TYPE_QUARTER -> {
                                sglp.isFullSpan = false
                                sglp.width = v.getWidth() /// 2
                                sglp.height = v.getHeight() / 2
                                sglp.setMargins(2,2,2,8)
                            }
                        }
                        v.setLayoutParams(sglp)
                        val lm =
                            (parent as RecyclerView).layoutManager as StaggeredGridLayoutManager?
                        lm!!.invalidateSpanAssignments()
                    }
                    v.getViewTreeObserver().removeOnPreDrawListener(this)
                    return true
                }
            })
        val shapeAppearanceModelLL = ShapeAppearanceModel()
            .toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, 20F)
            .build()

        val shapeDrawableLL = MaterialShapeDrawable(shapeAppearanceModelLL)
        shapeDrawableLL.fillColor = ContextCompat.getColorStateList(v.context,getColor())

        v.background = shapeDrawableLL


      return ViewHolder(v)
    }

    override fun getItemViewType(position: Int): Int {
        val modeEight = position % 10
        when (modeEight) {
            0, 3, 4, 5, 8, 9   -> return TYPE_HALF
            1, 2, 6, 7 -> return TYPE_QUARTER

        }
        return TYPE_FULL
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.onBind(tasksList[position])

    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val taskNameText: TextView = itemView.findViewById(R.id.tv_fragment_tasks_item_name)
        val taskRewardText: TextView = itemView.findViewById(R.id.tv_fragment_tasks_item_reward)

        fun onBind(task: Task){

            taskNameText.text = task.taskName
            taskRewardText.text = task.taskCategory

        }

    }

    companion object {
        private const val TYPE_FULL = 0
        private const val TYPE_HALF = 1
        private const val TYPE_QUARTER = 2
    }

    fun getColor (): Int {
        val colorList = listOf<Int>(R.color.purple_700, R.color.purple_200, R.color.dark_grey, R.color.teal_200,
            androidx.appcompat.R.color.material_blue_grey_800, R.color.purple_500, R.color.grey, R.color.black)
        return colorList.random()
    }

}