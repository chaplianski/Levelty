package com.example.levelty.presenter.dialogs.kid

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner
import com.example.levelty.R
import com.example.levelty.presenter.utils.CURRENT_CHORE_ID


class KidRedoTaskDialog : DialogFragment() {

    private val currentChoreId: Int get() = requireArguments().getInt(CURRENT_CHORE_ID)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_kid_redo_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val doneButton = view.findViewById<Button>(R.id.bt_kid_redo_task_dialog_done)
        val backButton = view.findViewById<TextView>(R.id.tv_kid_redo_dialog_back)

        doneButton?.setOnClickListener {
            parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf(KEY_RESPONSE to currentChoreId))
            dismiss()
        }

        backButton?.setOnClickListener {
            dismiss()
        }
    }


    companion object {

        val KEY_RESPONSE = "key response"

        val TAG = KidRedoTaskDialog::class.java.simpleName
        val REQUEST_KEY = "$TAG: default request key"
//
        fun show(manager: FragmentManager, currentChoreId: Int) {
            val dialogFragment = KidRedoTaskDialog()
            dialogFragment.arguments = bundleOf(
                CURRENT_CHORE_ID to currentChoreId
            )
            dialogFragment.show(manager, TAG)
        }

        fun setupListener(manager: FragmentManager, lifecycleOwner: LifecycleOwner, listener: (Int) -> Unit) {
            manager.setFragmentResultListener(REQUEST_KEY, lifecycleOwner, FragmentResultListener { key, result ->
                result.getInt(KEY_RESPONSE).let { listener.invoke(it) }
            })
        }
    }





}