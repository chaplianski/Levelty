package com.example.levelty.presenter.dialogs.kid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.levelty.R
import com.example.levelty.databinding.DialogKidSuccessCloseTaskBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class KidSuccessCloseTaskDialog: DialogFragment()  {


    var _binding: DialogKidSuccessCloseTaskBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogKidSuccessCloseTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.DialogFragmentTheme
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigation: BottomNavigationView = binding.bottomAppBarKidSuccessCloseTask
        bottomNavigation.selectedItemId = R.id.kid_tasks
        getBottonNavigation(bottomNavigation)
        bottomNavigation.itemIconTintList = null

        val coolButton = binding.btKidSuccessCloseTaskCool

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