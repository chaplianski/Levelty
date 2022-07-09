package com.example.levelty.presenter.helpers

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.example.levelty.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

class LevelButtonBehavior(context: Context, attrs: AttributeSet): CoordinatorLayout.Behavior<TextView>(context, attrs){


    private lateinit var levelView: View
    private lateinit var collapsingView: CollapsingToolbarLayout
    private var levelHight: Int = 0
    private var parentHight: Int = 0
    private var totalDy: Int = 0
    private var lastScale: Float = 0F
    private var lastBottom: Int = 0
    private var isStoped: Boolean = false

//    override fun layoutDependsOn(
//        parent: CoordinatorLayout,
//        child: TextView,
//        dependency: View
//    ): Boolean {
//        if (child.id == R.id.tv_kid_detail_fragment_level){
//            Log.d("MyLog", "layoutDependsOn")
//            return dependency.id == R.id.appbar//super.layoutDependsOn(parent, child, dependency)
//        }else return false
//    }
//
//    override fun onDependentViewChanged(
//        parent: CoordinatorLayout,
//        child: TextView,
//        dependency: View
//    ): Boolean {
//        return if (dependency is Toolbar && dependency.translationY > 0){
//            animate(child, dependency)
//            true
//        }else false
//
//
//    }
//
//    private fun animate(child: TextView, dependency: Toolbar) {
//        Log.d("MyLog", "animate onDependentViewChanged")
//    }
//
//    override fun onLayoutChild(
//        parent: CoordinatorLayout,
//        abl: AppBarLayout,
//        layoutDirection: Int
//    ): Boolean {
//        val superLayout = super.onLayoutChild(parent, abl, layoutDirection)
//        if (!::levelView.isInitialized)
//            initialize(abl)
//        return superLayout
//    }

//    override fun onStartNestedScroll(
//        parent: CoordinatorLayout,
//        child: AppBarLayout,
//        directTargetChild: View,
//        target: View,
//        nestedScrollAxes: Int,
//        type: Int
//    ): Boolean {
//        Log.d("MyLog", "onStartNestedScroll")
//        isStoped = false
//        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
//    }
//
//    fun onStopNestedScroll(
//        coordinatorLayout: CoordinatorLayout,
//        abl: AppBarLayout,
//        target: View,
//        type: Int
//    ) {
//        Log.d("MyLog", "onStopNestedScroll")
//        isStoped = true
//  //      restore(abl)
//        super.onStopNestedScroll(coordinatorLayout, abl, target, type)
//    }

//
//    private fun initialize(abl: AppBarLayout) {
//        levelView = abl.findViewById(R.id.tv_kid_detail_fragment_level)
//        collapsingView = abl.getChildAt(0) as CollapsingToolbarLayout
//        parentHight = abl.height
//        levelHight = levelView.height
//    }
//
//    private fun restore(abl: AppBarLayout) {
//        if (totalDy > 0) {
//            totalDy = 0
//            val anim = ValueAnimator.ofFloat(lastScale, 1f)
//            anim.addUpdateListener {
//                val value = it.animatedValue as Float
//                levelView.scaleX = value
//                levelView.scaleY = value
//                val bottomValue = (lastBottom - (lastBottom - parentHight)*it.animatedFraction).toInt()
//                abl.bottom = bottomValue
//                collapsingView.bottom = bottomValue
//            }
//            anim.start()
//        }
//    }


}