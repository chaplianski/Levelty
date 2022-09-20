package com.example.levelty.presenter.dialogs.parent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.levelty.R
import com.example.levelty.databinding.FragmentParentSuccessCreateTaskDialogBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class ParentSuccessCreateTaskDialog : Fragment() {

    var _binding: FragmentParentSuccessCreateTaskDialogBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentParentSuccessCreateTaskDialogBinding.inflate(layoutInflater, container,false)
        return binding.root

    }

//    override fun getTheme(): Int {
//        return R.style.DialogFragmentTheme
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigation: BottomNavigationView = binding.bottomAppBarParentSuccessCreateTask
        bottomNavigation.selectedItemId = R.id.kid_tasks
        getBottonNavigation(bottomNavigation)
        bottomNavigation.itemIconTintList = null

        val coolButton = binding.btParentSuccessCreateTaskTaskGo

        coolButton.setOnClickListener {
            findNavController().previousBackStackEntry?.savedStateHandle?.set(
                "cool",
                true
            )
//            dismiss()
            findNavController().navigate(R.id.action_parentSuccessCreateTaskDialog_to_dayPersonalTasksFragment)
//                dismiss()
        }
    }

    private fun getBottonNavigation(bottomNavigation: BottomNavigationView) {
        bottomNavigation.setOnItemSelectedListener { itemMenu ->
            when (itemMenu.itemId) {
                R.id.kid_tasks -> {
                    findNavController().navigate(R.id.parent_tasks)
                    true
                }
                R.id.kid_profile -> {
                    findNavController().navigate(R.id.parent_profile)
                    true
                }
                R.id.kid_goals -> {
                    findNavController().navigate(R.id.parent_settings)
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