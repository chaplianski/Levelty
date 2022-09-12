package com.example.levelty.presenter.ui.parent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.levelty.R
import com.example.levelty.presenter.utils.getParentBottomNavigationBar
import com.google.android.material.bottomnavigation.BottomNavigationView


class SettingsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigation: BottomNavigationView = view.findViewById(R.id.bottomAppBar_parent_settings_fragment)
        bottomNavigation.selectedItemId = R.id.parent_settings
        getParentBottomNavigationBar(bottomNavigation, view)
    }

//    private fun getParentBottomNavigationBar(bottomNavigation: BottomNavigationView) {
//        bottomNavigation.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.tasks -> {
//                    findNavController().navigate(R.id.tasksFragment)
//                    true
//                }
//                R.id.profile -> {
//                    findNavController().navigate(R.id.profileFragment)
//                    true
//                }
//                R.id.settings -> {
//                    findNavController().navigate(R.id.settingsFragment)
//                    true
//                }
//                else -> false
//            }
//        }
//    }

}