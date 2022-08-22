package com.example.levelty.presenter.helpers

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView
import com.example.levelty.R
import com.google.android.material.appbar.CollapsingToolbarLayout


//class MyBehavior() : CoordinatorLayout.Behavior<TextView>() {
//
//
//    constructor(context: Context?, attrs: AttributeSet?) : this()
//    var beginChildPosition = 0f
//
//    override fun onDependentViewChanged(
//        parent: CoordinatorLayout,
//        child: TextView,
//        dependency: View
//    ): Boolean {
//
//            beginChildPosition = child.y
//       return super.onDependentViewChanged(parent, child, dependency)
//    }
//
//    override fun onStartNestedScroll(
//        coordinatorLayout: CoordinatorLayout,
//        child: TextView,
//        directTargetChild: View,
//        target: View,
//        axes: Int,
//        type: Int
//    ): Boolean {
//
//        Log.d("MyLog","1 beginPosition = ${beginChildPosition}")
//        return child.id == R.id.tv_kid_detail_fragment_level && axes == View.SCROLL_AXIS_VERTICAL
//    }
//
//    override fun onNestedPreScroll(
//        coordinatorLayout: CoordinatorLayout,
//        child: TextView,
//        target: View,
//        dx: Int,
//        dy: Int,
//        consumed: IntArray,
//        type: Int
//    ) {
//
//        val nestedScrollTop = target.findViewById<NestedScrollView>(R.id.nsv_scroll_view)
//        val nsvTop = nestedScrollTop.top
//        val collapsingToolbar = coordinatorLayout.findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
//
////        val toolbar = coordinatorLayout.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
////        bottomToolbar = toolbar.bottom.toFloat()
//        collapsingToolbar.setCollapsedTitleTextColor(Color.BLACK)
//
//
//        child.y = 0.9305f*nsvTop - 55.294f
//        child.setBackgroundResource(0)
//        child.animate()
//
//        val colorIndicator =  (child.y - 84f) * (255f/700f)
//
//        child.setTextColor(Color.rgb(colorIndicator.toInt(),colorIndicator.toInt(),colorIndicator.toInt()))
//
//        if (child.y > 780F) {
//            child.setBackgroundResource(R.drawable.level_background)
//        }
//    }
//
//    override fun onStopNestedScroll(
//        coordinatorLayout: CoordinatorLayout,
//        child: TextView,
//        target: View,
//        type: Int
//    ) {
//        val collapsToolbar = coordinatorLayout.findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
//    //    Log.d("MyLog", "cool = ${collapsToolbar.height}")
//        if (child.y > collapsToolbar.height/2){ //435f) {
//            Log.d("MyLog","2 beginPosition = ${beginChildPosition}")
//            child.y = beginChildPosition //785f //collapsBottom - 220f //785
//        }
//        if (child.y >  780F) {
//            child.setTextColor(Color.WHITE)
//            child.setBackgroundResource(R.drawable.level_background)
//        }
//        if (child.y < collapsToolbar.height/2){ //435f) {
//            child.y = 89f
//        }
//
//    }
//}






