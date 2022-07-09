package com.example.levelty.presenter.ui.kid


import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout


class KidPersonalFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.levelty.R.layout.fragment_kid_personal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: androidx.appcompat.widget.Toolbar = view.findViewById(com.example.levelty.R.id.toolbar)
        val collapsingToolbar = view.findViewById(com.example.levelty.R.id.collapsing_toolbar) as CollapsingToolbarLayout
        val appBar = view.findViewById(com.example.levelty.R.id.appbar) as AppBarLayout

//        Log.d("MyLog", "collapsingToolbar.height = ${collapsingToolbar.height}")

        appBar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (collapsingToolbar.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(
                    collapsingToolbar
                )
            ) {
//                Log.d("MyLog", "white")
//                activity?.window?.decorView?.windowInsetsController?.setSystemBarsAppearance(0, APPEARANCE_LIGHT_STATUS_BARS);
                view.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//                activity?.window?.setDecorFitsSystemWindows(false)


                toolbar.navigationIcon?.setColorFilter(resources.getColor(com.example.levelty.R.color.white), PorterDuff.Mode.SRC_ATOP)
            } else {
//                Log.d("MyLog", "black")


                view.systemUiVisibility = 0




                toolbar.navigationIcon?.setColorFilter(resources.getColor(com.example.levelty.R.color.black), PorterDuff.Mode.SRC_ATOP)
            }
        }


//        val levelText: TextView = view.findViewById(R.id.tv_kid_detail_fragment_level)
//        val levelFrame: FrameLayout = view.findViewById(R.id.fl_level)



//        val myBehavior = MyBehavior()
//        val params = levelAny.layoutParams as CoordinatorLayout.LayoutParams
//        params.behavior = myBehavior


//        val lp = levelFrame.layoutParams as CoordinatorLayout.LayoutParams
//        lp.behavior = MyBehavior()

//        mToolBar.getLayoutParams().topMargin = DisplayUtils.getStatusBarHeight(this)

    }


}