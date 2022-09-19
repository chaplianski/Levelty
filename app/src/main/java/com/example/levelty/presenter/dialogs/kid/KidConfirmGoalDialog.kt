package com.example.levelty.presenter.dialogs.kid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.levelty.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class KidConfirmGoalDialog : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kid_confirm_goal_dialog, container, false)
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
            behavior.isDraggable = false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val closeButton: ImageView = view.findViewById(R.id.iv_dialog_confirm_goal_close)
        val sendButton: Button = view.findViewById(R.id.bt_dialog_confirm_goal_send)
        val goalTitle: TextView = view.findViewById(R.id.tv_dialog_confirm_goal_name)

        val goalName = arguments?.getString("goal name", "")

        goalTitle.text = goalName

        closeButton.setOnClickListener {
            dismiss()
        }

        sendButton.setOnClickListener {
            findNavController().previousBackStackEntry?.savedStateHandle?.set(
                "goal confirm",
               true
            )
            dismiss()
        }
    }

}