package com.example.levelty.presenter.dialogs.parent

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.levelty.R


class ParentDeclineGoalDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_parent_decline_goal, container, false)
    }

//    override fun getTheme(): Int {
//        return R.style.DialogFragmentTheme
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val declineButton: Button = view.findViewById(R.id.bt_tv_parent_decline_goal_dialog_decline)
        val backButton: TextView = view.findViewById(R.id.tv_parent_decline_goal_dialog_back)

        backButton.setOnClickListener {
            dismiss()
        }

        declineButton.setOnClickListener {
            dismiss()
        }




    }

}