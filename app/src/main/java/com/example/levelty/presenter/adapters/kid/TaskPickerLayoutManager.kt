package com.example.levelty.presenter.adapters.kid

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TaskPickerLayoutManager (context: Context?, orientation: Int, reverseLayout: Boolean) :
    LinearLayoutManager(context, orientation, reverseLayout) {

    var scaleDownBy = 0.9f
    var scaleDownDistance = 1.8f

    var isChangeAlpha = true

    interface TaskScrollStopListener {
       fun selectedView(view: View?)
    }

    private var onScrollStopListener: TaskScrollStopListener? = null

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
                when (i) {
                    0 -> {
                        scale = scaleDownBy
                        child.alpha = 0.4f
                    }
                    1 -> {
                        scale = 1f
                        child.alpha = 1f
                    }
                    2 -> {
                        scale = scaleDownBy
                        child.alpha = 0.4f
                    }
                }

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

    fun setOnScrollStopListener(onScrollStopListener: TaskScrollStopListener?) {
        this.onScrollStopListener = onScrollStopListener
    }

}
