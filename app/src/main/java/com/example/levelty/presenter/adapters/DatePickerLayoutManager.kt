package com.example.levelty.presenter.adapters

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

class DatePickerLayoutManager (context: Context?, val dateOrientation: Int, reverseLayout: Boolean) :
    LinearLayoutManager(context, dateOrientation, reverseLayout) {

    private var scaleDownBy = 0.66f
    private var scaleDownDistance = 0.9f
    private var changeAlpha = true

    private var onScrollDataListener: onScrollStopDataListener? = null

//    fun PickerLayoutManager(context: Context?, orientation: Int, reverseLayout: Boolean) {
//        super(context, orientation, reverseLayout)
//    }

    override fun onLayoutChildren(recycler: Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
        scaleDownView()
    }

//    override fun scrollHorizontallyBy(
//        dx: Int,
//        recycler: Recycler?,
//        state: RecyclerView.State?
//    ): Int {
//        val orientation = orientation
//        return if (orientation == HORIZONTAL) {
//            val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
//            scaleDownView()
//            scrolled
//        } else 0
//    }

    override fun scrollVerticallyBy(dy: Int, recycler: Recycler?, state: RecyclerView.State?): Int {
        val orientation = dateOrientation
        return  if (orientation == VERTICAL) {
            val scrolled = super.scrollVerticallyBy(dy, recycler, state)
            scaleDownView()
            scrolled
        }else 0
    }

    private fun scaleDownView() {
        val mid = width / 2.0f
        val unitScaleDownDist = scaleDownDistance * mid

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childMid = (getDecoratedLeft(child!!) + getDecoratedRight(child)) / 2.0f

            var scale = 0f

            when (i){
                0 -> {
                    scale = 0.55f
                    child.alpha = 0.55f
                }
                1 -> {
                    scale = 0.75f
                    child.alpha = 0.75f
                }
                2 -> {
                    scale = 1f
                    child.alpha = 1f
                }

                3 -> {
                    scale = 0.75f
                    child.alpha = 0.75f
                }
                4 -> {
                    scale = 0.55f
                    child.alpha = 0.55f
                }

            }



//            val scale = 1.0f + -1 * scaleDownBy * Math.min(
//                unitScaleDownDist,
//                Math.abs(mid - childMid)
//            ) / unitScaleDownDist
            child.scaleX = scale
            child.scaleY = scale

 //           Log.d("MyLog", "childCount = $childCount, scale = $scale")
            if (changeAlpha) {
                child.alpha = scale
            }
        }
    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        if (state == 0) {
            if (onScrollDataListener != null) {
                var selected = 0
                var lastHeight = 0f
                for (i in 0 until childCount) {
                    if (lastHeight < getChildAt(i)!!.scaleY) {
                        lastHeight = getChildAt(i)!!.scaleY
                        selected = i
                    }
                }
                onScrollDataListener!!.selectedView(getChildAt(selected))
            }
        }
    }

    fun setOnScrollStopListener(onScrollStopDataListener: onScrollStopDataListener?) {
        this.onScrollDataListener = onScrollStopDataListener
    }

    interface onScrollStopDataListener {
        fun selectedView(view: View?)
    }

}