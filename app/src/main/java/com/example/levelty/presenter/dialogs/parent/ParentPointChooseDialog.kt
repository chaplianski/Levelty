package com.example.levelty.presenter.dialogs.parent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.example.levelty.R
import com.example.levelty.presenter.utils.TASK_NAME
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ParentPointChooseDialog : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_point_choose, container, false)
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pointsText: EditText = view.findViewById(R.id.et_fragment_point_choose_text_field)
        val closeButton: ImageView = view.findViewById(R.id.iv_fragment_point_choose_close)
        val saveButton: Button = view.findViewById(R.id.bt_fragment_point_choose_save)
        val taskName: TextView = view.findViewById(R.id.tv_fragment_point_choose_title)
        val title = arguments?.getString(TASK_NAME).toString()

        if (title.isEmpty()) taskName.text = "Custom variant points"
        else taskName.text = title
        Log.d("MyLog", "task name = ${title}")

        closeButton.setOnClickListener {
            dismiss()
        }
        saveButton.setOnClickListener {

            if (!pointsText.text.isEmpty()) {
                val navController = findNavController()
                navController.previousBackStackEntry?.savedStateHandle?.set(
                    "points",
                    pointsText.text.toString().toInt()
                )
                dismiss()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        dialog.let {

            val bottomSheet =
                it?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val behavior = BottomSheetBehavior.from(bottomSheet)

//            behavior.skipCollapsed = true
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
//            behavior.isDraggable = false
        }
    }


}