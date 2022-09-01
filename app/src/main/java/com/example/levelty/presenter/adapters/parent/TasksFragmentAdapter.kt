package com.example.levelty.presenter.adapters.parent

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.levelty.R
import com.example.levelty.domain.models.Category
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel


class TasksFragmentAdapter (val tasksList: List<Category>): RecyclerView.Adapter<TasksFragmentAdapter.ViewHolder>() {


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
//        val shapeAppearanceModelLL = ShapeAppearanceModel()
//            .toBuilder()
//            .setAllCorners(CornerFamily.ROUNDED, 50F)
//            .build()
//
//        val shapeDrawableLL = MaterialShapeDrawable(shapeAppearanceModelLL)
////        shapeDrawableLL.fillColor = ContextCompat.getColorStateList(v.context,getColor())
//
//        v.background = shapeDrawableLL


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
                bundle.getString("category", tasksList[position].title)
                val navController = holder.itemView.let { Navigation.findNavController(it) }
                navController.navigate(R.id.action_tasksFragment_to_categoryFragment, bundle)
            }
        }

    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val taskNameText: TextView = itemView.findViewById(R.id.tv_item_parent_tasks_fragment_name)
        val taskImage: ImageView = itemView.findViewById(R.id.iv_item_parent_tasks_fragment)
        val taskCard: CardView = itemView.findViewById(R.id.cardView_item_parent_tasks_fragment)

        fun onBind(category: Category){

            Log.d("MyLog", "category = ${category.title}")
//            val shapeAppearanceModelLL = ShapeAppearanceModel()
//                .toBuilder()
//                .setAllCorners(CornerFamily.ROUNDED, 50F)
//                .build()
//            val shapeDrawableLL = MaterialShapeDrawable(shapeAppearanceModelLL)

            if (category.id == null) {

                taskCard.setCardBackgroundColor("#2D98FB".toColorInt())
                Glide.with(itemView.context).load(R.drawable.ic_big_plus)
                    .override(30, 30)
//                    .centerCrop()
                    .circleCrop()
                    .into(taskImage)
            } else {

                taskNameText.text = category.title.toString()
                category.backgroundColor?.toColorInt()?.let { taskCard.setCardBackgroundColor(it) }
                Glide.with(itemView.context).load(category.image)
                    .override(68, 68)
//                    .centerCrop()
                    .circleCrop()
                    .into(taskImage)
            }
            taskNameText.text = category.title.toString()
//            taskNameText.text = task.taskName
//            taskRewardText.text = task.taskCategory




//            taskLayout.background = shapeDrawableLL

        }

    }

    companion object {
        private const val TYPE_FULL = 0
        private const val TYPE_HALF = 1
        private const val TYPE_QUARTER = 2
    }

    fun getColor (): Int {
        val colorList = listOf<Int>(
            R.color.gradient_red, R.color.red_2, R.color.violet_1, R.color.violet_2,
            R.color.green_1, R.color.green_2, R.color.blue_1, R.color.blue_2
        )
        return colorList.random()
    }

}