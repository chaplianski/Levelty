package com.example.levelty.presenter.dialogs.kid

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import com.example.levelty.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class KidSuccessTaskDialog: BottomSheetDialogFragment()  {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kid_success_close_task, container, false)
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onStart() {
        super.onStart()

        dialog.let {

            val bottomSheet = it?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val behavior = BottomSheetBehavior.from(bottomSheet)

            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            //      behavior.isHideable = false
            behavior.isDraggable = false
            //    behavior.state = BottomSheetBehavior.SAVE_HIDEABLE
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val coolButton: Button = view.findViewById(R.id.bt_kid_success_close_task_cool)
        Log.d("MyLog", "cool task")
        coolButton.setOnClickListener {
            Log.d("MyLog", "cool button")
            dismiss()
        }
    }

}