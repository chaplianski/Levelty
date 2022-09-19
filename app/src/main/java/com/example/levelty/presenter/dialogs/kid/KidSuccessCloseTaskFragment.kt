package com.example.levelty.presenter.dialogs.kid

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.navigation.fragment.findNavController
import com.example.levelty.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


//class KidSuccessCloseTaskFragment : BottomSheetDialogFragment() {
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_kid_success_close_task_dialog, container, false)
//    }
//
//    override fun getTheme(): Int {
//        return R.style.AppBottomSheetDialogTheme
//    }
//
//    override fun onStart() {
//        super.onStart()
//
//        dialog.let {
//
//            val bottomSheet = it?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
//            val behavior = BottomSheetBehavior.from(bottomSheet)
//            behavior.isFitToContents = false
//            behavior.state = BottomSheetBehavior.STATE_EXPANDED
//            behavior.isDraggable = false
//        }
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val bottomNavigation = view.findViewById<BottomNavigationView>(R.id.bottom_appBar_kid_success_close_task)
////        getKidBottomNavigationBar(bottomNavigation)
//        bottomNavigation.selectedItemId = R.id.kid_tasks
//        getKidBottomNavigationBar(bottomNavigation)
//        bottomNavigation.itemIconTintList = null
//
//        val coolButton: Button = view.findViewById(R.id.bt_kid_success_close_task_cool)
////        coolButton.background.alpha = (0.1*255).toInt()
//
//        coolButton.setOnClickListener {
//            Log.d("MyLog", "cool button")
//            dismiss()
//        }
//    }
//    private fun getKidBottomNavigationBar(bottomNavigation: BottomNavigationView) {
//        bottomNavigation.setOnItemSelectedListener { itemMenu ->
//            when (itemMenu.itemId) {
//                R.id.kid_tasks -> {
//                    findNavController().navigate(R.id.kidDayTasksFragment)
//                    true
//                }
//                R.id.kid_profile -> {
//                    findNavController().navigate(R.id.kidProfileFragment)
//                    true
//                }
//                R.id.kid_goals -> {
//                    findNavController().navigate(R.id.kidGoalsFragment)
//                    true
//                }
//                else -> false
//            }
//
//        }
//    }
//
//}