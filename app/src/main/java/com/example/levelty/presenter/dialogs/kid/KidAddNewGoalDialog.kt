package com.example.levelty.presenter.dialogs.kid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.levelty.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class KidAddNewGoalDialog : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_kid_add_new_goal, container, false)
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

        val closeButton: ImageView = view.findViewById(R.id.iv_dialog_new_goal_close)
        val sendButton: Button = view.findViewById(R.id.bt_dialog_new_goal_send)
        val goalField: TextInputLayout = view.findViewById(R.id.et_dialog_new_goal_container)
        val goalText: TextInputEditText = view.findViewById(R.id.et_dialog_new_goal_text_field)

        closeButton.setOnClickListener {
            dismiss()
        }

        sendButton.setOnClickListener {
            if (goalText.text?.isEmpty() == false) {
                val navController = findNavController()
                navController.previousBackStackEntry?.savedStateHandle?.set(
                    "new goal",
                    goalText.text.toString()
                )
                dismiss()
            }
        }
    }
}