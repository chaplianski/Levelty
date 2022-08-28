package com.example.levelty.presenter.ui.kid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.levelty.R
import com.example.levelty.databinding.FragmentKidSetGoalBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class KidSetGoalFragment : Fragment() {

    var _binding: FragmentKidSetGoalBinding? = null
    val binding: FragmentKidSetGoalBinding = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKidSetGoalBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val goalsRV = binding.rvKidSetGoalFragmentGoals
        val progressText = binding.tvKidSetGoalFragmentProgressText
        val progressView = binding.pbKidSetGoalFragmentProgressView
        val backButton = binding.ivKidSetGoalFragmentBack
        val bottomNavigation = binding.bottomAppBarKidSetGoalFragment

        getKidBottomNavigationBar(bottomNavigation)
        goalsRV.layoutManager = GridLayoutManager(context,2)

    }

    private fun getKidBottomNavigationBar(bottomNavigation: BottomNavigationView) {
        bottomNavigation.setOnItemSelectedListener { itemMenu ->
            when (itemMenu.itemId) {
                R.id.tasks -> {
                    findNavController().navigate(R.id.kidDayTasksFragment)
                    true
                }
                R.id.profile -> {
                    findNavController().navigate(R.id.kidProfileFragment)
                    true
                }
                R.id.goals -> {
                    findNavController().navigate(R.id.kidGoalsFragment)
                    true
                }
                else -> false
            }

        }
    }

}