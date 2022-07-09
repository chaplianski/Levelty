package com.example.levelty.presenter.helpers

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.example.levelty.R

class ProfileFragmentBehavior(): CoordinatorLayout.Behavior<FrameLayout>() {

    constructor(context: Context?, attrs: AttributeSet?) : this()

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FrameLayout,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return child.id == R.id.fl_profile_fragment_top_info && axes == View.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FrameLayout,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        val oldTranslation = child.translationY
        val newTranslation = oldTranslation + dy

        when {
            newTranslation > child.height -> child.translationY = child.height.toFloat()
            newTranslation < 0 -> child.translationY = 0f
            else -> child.translationY = newTranslation
        }


   //     ViewCompat.offsetTopAndBottom(child, dy)
    }

}