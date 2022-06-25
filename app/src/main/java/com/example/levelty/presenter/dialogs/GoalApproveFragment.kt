package com.example.levelty.presenter.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.levelty.R
import com.example.levelty.databinding.FragmentGoalApproveBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class GoalApproveFragment : BottomSheetDialogFragment() {

    var _binding: FragmentGoalApproveBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoalApproveBinding.inflate(inflater, container,false)

        return binding.root
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

        val closeButton: ImageView = binding.ivGoalApproveFragmentClose
        val approveButton: Button = binding.btGoalApproveFragmentApprove
        val goalName: TextView = binding.tvGoalApproveFragmentNameGoal
        val goalValue: TextView = binding.tvGoalApproveFragmentGoalValue

        val goalCoins = arguments?.getInt("goal value")
        goalName.text = arguments?.getString("goal name")
        if (goalCoins == 1){
            goalValue.text = "$goalCoins coin"
        } else goalValue.text = "$goalCoins coins"

        approveButton.setOnClickListener {
            // сделать вьюмодель с юзкейсом обновить цель. Передавать ай-ди цели.
        }

        closeButton.setOnClickListener {
            dismiss()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}