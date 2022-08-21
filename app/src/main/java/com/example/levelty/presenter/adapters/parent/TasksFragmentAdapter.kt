package com.example.levelty.presenter.adapters.parent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.levelty.R
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel


class TasksFragmentAdapter (val tasksList: List<String>): RecyclerView.Adapter<TasksFragmentAdapter.ViewHolder>() {


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
        holder.itemView.setOnClickListener {
            if (position == 0){
                val navController = holder.itemView.let { Navigation.findNavController(it) }
                navController.navigate(R.id.action_tasksFragment_to_newTaskFragment)
            } else {
                val bundle = Bundle()
                bundle.getString("category", tasksList[position])
                val navController = holder.itemView.let { Navigation.findNavController(it) }
                navController.navigate(R.id.action_tasksFragment_to_categoryFragment)
            }
        }

    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val taskNameText: TextView = itemView.findViewById(R.id.tv_fragment_tasks_item_name)
        val taskRewardText: TextView = itemView.findViewById(R.id.tv_fragment_tasks_item_reward)

        fun onBind(category: String){

            taskNameText.text = category
//            taskNameText.text = task.taskName
//            taskRewardText.text = task.taskCategory

        }

    }

    companion object {
        private const val TYPE_FULL = 0
        private const val TYPE_HALF = 1
        private const val TYPE_QUARTER = 2
    }

    fun getColor (): Int {
        val colorList = listOf<Int>(
            R.color.red_1, R.color.red_2, R.color.violet_1, R.color.violet_2,
            R.color.green_1, R.color.green_2, R.color.blue_1, R.color.blue_2
        )
        return colorList.random()
    }

}