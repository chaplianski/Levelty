package com.example.levelty.presenter.dialogs.kid

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner
import com.example.levelty.R


class KidNewLevelDialog : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_kid_new_level, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val coolButton = view.findViewById<Button>(R.id.bt_kid_new_level_cool)
        val levelValue = view.findViewById<TextView>(R.id.tv_kid_new_level)

        coolButton.setOnClickListener {
            dismiss()
        }
    }

    companion object {

        val KEY_RESPONSE = "key response"

        val TAG = KidNewLevelDialog::class.java.simpleName
        val REQUEST_KEY = "$TAG: default request key"
        //
        fun show(manager: FragmentManager) {
            val dialogFragment = KidNewLevelDialog()
//            dialogFragment.arguments = bundleOf(
//                CURRENT_CHORE_ID to currentChoreId
//            )
            dialogFragment.show(manager, TAG)
        }

        fun setupListener(manager: FragmentManager, lifecycleOwner: LifecycleOwner, listener: (Int) -> Unit) {
            manager.setFragmentResultListener(REQUEST_KEY, lifecycleOwner, FragmentResultListener { key, result ->
                result.getInt(KEY_RESPONSE).let { listener.invoke(it) }
            })
        }
    }

}