package com.example.levelty.presenter.dialogs.kid

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.example.levelty.R
import com.example.levelty.databinding.DialogKidNotificationSendBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class KidNotificationSendDialog : DialogFragment() {

    var _binding: DialogKidNotificationSendBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        activity?.window?.statusBarColor = Color.TRANSPARENT

        _binding = DialogKidNotificationSendBinding.inflate(layoutInflater, container, false)
            return binding.root
    }

    override fun getTheme(): Int {
        return R.style.DialogFragmentTheme
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.window?.navigationBarColor = Color.BLACK

        val bottomNavigation: BottomNavigationView = binding.bottomAppBarKidNotificationSend
        bottomNavigation.selectedItemId = R.id.kid_tasks
        getBottonNavigation(bottomNavigation)
        bottomNavigation.itemIconTintList = null

        val coolButton = view.findViewById<Button>(R.id.bt_kid_notification_send_cool)

        coolButton.setOnClickListener {
            dismiss()
        }
    }

    private fun getBottonNavigation(bottomNavigation: BottomNavigationView) {
        bottomNavigation.setOnItemSelectedListener { itemMenu ->
            when (itemMenu.itemId) {
                R.id.kid_tasks -> {
                    findNavController().navigate(R.id.kidDayTasksFragment)
                    true
                }
                R.id.kid_profile -> {
                    findNavController().navigate(R.id.kidProfileFragment)
                    true
                }
                R.id.kid_goals -> {
                    findNavController().navigate(R.id.kidGoalsFragment)
                    true
                }
                else -> false
            }

        }
    }

    companion object {

        val KEY_RESPONSE = "key response"

        val TAG = KidNotificationSendDialog::class.java.simpleName
        val REQUEST_KEY = "$TAG: default request key"
        //
        fun show(manager: FragmentManager) {
            val dialogFragment = KidNotificationSendDialog()
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