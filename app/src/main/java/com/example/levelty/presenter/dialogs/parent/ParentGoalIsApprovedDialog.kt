package com.example.levelty.presenter.dialogs.parent

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.levelty.R
import com.example.levelty.databinding.DialogParentGoalIsApprovedBinding
import com.example.levelty.domain.models.GoalsItem
import com.example.levelty.presenter.utils.CURRENT_GOAL


class ParentGoalIsApprovedDialog : DialogFragment() {

    var _binding: DialogParentGoalIsApprovedBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DialogParentGoalIsApprovedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val goalName = binding.tvParentGoalIsApprovedName
        val goalCost = binding.tvParentGoalIsApprovedCost
        val goalCoolButton = binding.btParentGoalIsApprovedCool
        val goal = arguments?.getParcelable<GoalsItem>(CURRENT_GOAL)

        goalName.text = goal?.title
        goalCost.text = goal?.price.toString()

        goalCoolButton.setOnClickListener {
            dismiss()
        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}