package com.example.levelty.presenter.dialogs.parent

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner
import com.example.levelty.R
import com.example.levelty.databinding.FragmentGoalApproveBinding
import com.example.levelty.domain.models.GoalsItem
import com.example.levelty.presenter.utils.CURRENT_GOAL
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ParentApproveDeclineGoalDialog : BottomSheetDialogFragment() {

    private val currentGoal: GoalsItem? get() = requireArguments().getParcelable(CURRENT_GOAL)


    var _binding: FragmentGoalApproveBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoalApproveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onStart() {
        super.onStart()

        dialog.let {
            val bottomSheet =
                it?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val behavior = BottomSheetBehavior.from(bottomSheet)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.isDraggable = false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val closeButton = binding.ivGoalApproveFragmentClose
        val goalApproveButton = binding.btGoalApproveFragmentApprove
        val goalCoinField = binding.otfGoalApproveFragmentCoinContainer
        val goalCoinText = binding.etGoalApproveFragmentCoinText
        val goalDecline = binding.tvGoalApproveFragmentDecline

//        val goalCoins = arguments?.getInt("goal value")
//        goalName.text = arguments?.getString("goal name")
//        if (goalCoins == 1){
//            goalValue.text = "$goalCoins coin"
//        } else goalValue.text = "$goalCoins coins"


        goalApproveButton.setOnClickListener {
            if (goalCoinText.text?.isEmpty() == true) {
                goalCoinField.error = "You not enter value"
            } else {
                currentGoal?.price = goalCoinText.text.toString().toInt()
                parentFragmentManager.setFragmentResult(
                    REQUEST_KEY_APPROVE,
                    bundleOf(
                        CURRENT_GOAL to currentGoal,
//                    REJECTED_TASK_STATUS to "reject"
                    ),
                )
                dismiss()

            }

        }

        goalDecline.setOnClickListener {
            parentFragmentManager.setFragmentResult(
                REQUEST_KEY_DECLINE,
                bundleOf(
                    CURRENT_GOAL to currentGoal,
//                    REJECTED_TASK_STATUS to "reject"
                ),
            )
            dismiss()
        }

        closeButton.setOnClickListener {
            dismiss()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {

        val TAG = ParentApproveDeclineGoalDialog::class.java.simpleName
        val REQUEST_KEY = "$TAG: default request key"
        val REQUEST_KEY_DECLINE = "decline goal key"
        val REQUEST_KEY_APPROVE = "approve goal key"

        //
        fun show(manager: FragmentManager, currentGoal: Parcelable) {
            val dialogFragment = ParentApproveDeclineGoalDialog()
            dialogFragment.arguments = bundleOf(
                CURRENT_GOAL to currentGoal
            )
            dialogFragment.show(manager, TAG)
        }

        fun setupDeclineListener(
            manager: FragmentManager,
            lifecycleOwner: LifecycleOwner,
            listener: (GoalsItem) -> Unit
        ) {
            manager.setFragmentResultListener(
                REQUEST_KEY_DECLINE,
                lifecycleOwner,
                FragmentResultListener { key, result ->
                    result.getParcelable<GoalsItem>(CURRENT_GOAL).let {
                        if (it != null) {
                            listener.invoke(it)
                        }
                    }
                })
        }

        fun setupApproveListener(
            manager: FragmentManager,
            lifecycleOwner: LifecycleOwner,
            listener: (GoalsItem) -> Unit
        ) {
            manager.setFragmentResultListener(
                REQUEST_KEY_APPROVE,
                lifecycleOwner,
                FragmentResultListener { key, result ->
                    result.getParcelable<GoalsItem>(CURRENT_GOAL).let {
                        if (it != null) {
                            listener.invoke(it)
                        }
                    }
                })
        }

    }

}