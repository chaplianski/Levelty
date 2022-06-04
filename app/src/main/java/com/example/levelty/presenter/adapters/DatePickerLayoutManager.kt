package com.example.levelty.presenter.adapters

import android.content.Context
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DatePickerLayoutManager (context: Context?, orientation: Int, reverseLayout: Boolean) :
    LinearLayoutManager(context, orientation, reverseLayout) {

    var scaleDownBy = 0.66f
    var scaleDownDistance = 1.8f
    var isChangeAlpha = true

    interface scrollDateStopListener {
        //        fun selectedView(view: View?)
        fun selectedView(view: View?)
    }

    private var onScrollStopListener: DatePickerLayoutManager.scrollDateStopListener? = null


    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        super.onLayoutChildren(recycler, state)
        scaleDownView()
    }

    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {

        val orientation = orientation
        return if (orientation == VERTICAL) {
            val scrolled = super.scrollVerticallyBy(dy, recycler, state)
            scaleDownView()
            scrolled
        } else 0
    }

    private fun scaleDownView() {
        val mid = width / 2.0f  // центрирование вью
        val unitScaleDownDist = scaleDownDistance * mid   // дистанция между элементами
        for (i in 0 until childCount) {
            val child = getChildAt(i)
//            Log.d("MyLog", "child: $childCount")
//            if (1 == 0) {
//                val childMid = (getDecoratedLeft(child!!) + getDecoratedRight(child)) / 2.0f
//                val scale = 1.0f + -1  / unitScaleDownDist
//                child.scaleX = scale
//                child.scaleY = scale
//            }
            //           if (i > 1){
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
//        }

//            if (isChangeAlpha) {
//
//            }
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

    fun setOnScrollStopListener(onScrollStopListener: DatePickerLayoutManager.scrollDateStopListener?) {
        this.onScrollStopListener = onScrollStopListener
    }


}