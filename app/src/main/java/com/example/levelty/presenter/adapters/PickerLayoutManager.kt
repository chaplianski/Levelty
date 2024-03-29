package com.example.levelty.presenter.adapters

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class PickerLayoutManager(context: Context?, orientation: Int, reverseLayout: Boolean) :
    LinearLayoutManager(context, orientation, reverseLayout) {

    var scaleDownBy = 0.66f
    var scaleDownDistance = 1.8f
    var isChangeAlpha = true

    // ***** Enable / Disable Scroll
    private var scrollable = true

    fun enableScrolling() {
        scrollable = true
    }

    fun disableScrolling() {
        scrollable = false
    }

//    override fun canScrollVertically() =
//        super.canScrollVertically() && scrollable


    override fun canScrollHorizontally() =
        scrollable
    //super.canScrollVertically() &&

// *********************************************************
    interface ScrollStopListener {
//        fun selectedView(view: View?)
        fun selectedView(view: View?)
    }

    private var onScrollStopListener: ScrollStopListener? = null

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        super.onLayoutChildren(recycler, state)
        scaleDownView()
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State
    ): Int {
        val orientation = orientation
        return if (orientation == HORIZONTAL) {
            val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
            scaleDownView()
            scrolled
        } else 0
    }

    private fun scaleDownView() {
        val mid = width / 2.0f  // центрирование вью
        val unitScaleDownDist = scaleDownDistance * mid   // дистанция между элементами
        for (i in 0 until childCount) {
            val child = getChildAt(i)

            val childMid = (getDecoratedLeft(child!!) + getDecoratedRight(child)) / 2.0f
            var scale = 0f
            when (i){
                0 -> {
                    scale = 0.85f
                    child.alpha = 0.65f
                }
                1 -> {
                    scale = 0.85f
                    child.alpha = 0.65f
                }
                2 -> {
                    scale = 1f
                    child.alpha = 1f
                }

                3 -> {
                    scale = 0.85f
                    child.alpha = 0.65f
                }
                4 -> {
                    scale = 0.85f
                    child.alpha = 0.65f
                }

            }



                // **** Scale with decreasing ******
//                val scale = 1.0f + -1 * scaleDownBy * Math.min(
//                    unitScaleDownDist,
//                    Math.abs(mid - childMid)
//                ) / unitScaleDownDist

                child.scaleX = scale
                child.scaleY = scale

        }
    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        if (state == 0) {
            if (onScrollStopListener != null) {
                var selected = 0
                var lastHeight = 0f
                for (i in 0 until childCount) {
                    if (lastHeight < getChildAt(i)!!.scaleY) {
                        lastHeight = getChildAt(i)!!.scaleY
                        selected = i
                    }
                }
                onScrollStopListener?.selectedView(getChildAt(selected))
            }
        }
    }

    fun setOnScrollStopListener(onScrollStopListener: ScrollStopListener?) {
        this.onScrollStopListener = onScrollStopListener
    }

}
