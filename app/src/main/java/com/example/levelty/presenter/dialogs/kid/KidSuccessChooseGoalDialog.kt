package com.example.levelty.presenter.dialogs.kid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.levelty.R
import com.example.levelty.databinding.FragmentKidSuccessChooseGoalDialogBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class KidSuccessChooseGoalDialog : DialogFragment() {

    var _binding: FragmentKidSuccessChooseGoalDialogBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentKidSuccessChooseGoalDialogBinding.inflate(layoutInflater,container,false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_kid_success_choose_goal_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigation: BottomNavigationView = binding.bottomAppBarKidSuccessCloseGoal
        bottomNavigation.selectedItemId = R.id.kid_tasks
        getBottonNavigation(bottomNavigation)
        bottomNavigation.itemIconTintList = null

        val coolButton = binding.btKidSuccessCloseGoalCool

        coolButton.setOnClickListener {
            dismiss()
        }
    }

    private fun getBottonNavigation(bottomNavigation: BottomNavigationView) {
        bottomNavigation.setOnItemSelectedListener { itemMenu ->
            when (itemMenu.itemId) {
                R.id.kid_tasks -> {
                    findNavController().navigate(R.id.kid_tasks)
                    true
                }
                R.id.kid_profile -> {
                    findNavController().navigate(R.id.kid_profile)
                    true
                }
                R.id.kid_goals -> {
                    findNavController().navigate(R.id.kid_goals)
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}