package com.example.levelty.presenter.helpers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CircularScrollListener(val itemsNomber: Int): RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
//        val firstItemVisible: Int = layoutManager.findFirstVisibleItemPosition()
//        if (firstItemVisible != 1 && (firstItemVisible % itemsNomber == 1)) {
//            layoutManager.scrollToPosition(1)
//        } else if (firstItemVisible != 1 && firstItemVisible > itemsNomber && (firstItemVisible % itemsNomber > 1)) {
//            layoutManager.scrollToPosition(firstItemVisible % itemsNomber)
//        } else if (firstItemVisible == 0) {
//            layoutManager.scrollToPositionWithOffset(itemsNomber, -recyclerView.computeHorizontalScrollOffset())
//        }

        val firstItemVisible: Int = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        if (firstItemVisible != 0 && firstItemVisible % itemsNomber === 0) {
            recyclerView.layoutManager?.scrollToPosition(0)
        }
    }


}