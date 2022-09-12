package com.example.levelty.presenter.utils

import android.view.View
import androidx.navigation.Navigation
import com.example.levelty.R
import com.google.android.material.bottomnavigation.BottomNavigationView

fun getKidBottomNavigationBar(bottomNavigation: BottomNavigationView, view: View) {

    val navController = Navigation.findNavController(view)

    bottomNavigation.setOnItemSelectedListener { itemMenu ->
        when (itemMenu.itemId) {
            R.id.kid_tasks -> {
                navController.navigate(R.id.kidDayTasksFragment)
                true
            }
            R.id.kid_profile -> {
                navController.navigate(R.id.kidProfileFragment)
                true
            }
            R.id.kid_goals -> {
                navController.navigate(R.id.kidGoalsFragment)
                true
            }
            else -> false
        }

    }
}

fun getParentBottomNavigationBar(bottomNavigation: BottomNavigationView, view: View) {

    val navController = Navigation.findNavController(view)

    bottomNavigation.setOnItemSelectedListener { item ->
        when (item.itemId) {
            R.id.parent_tasks -> {
                navController.navigate(R.id.tasksFragment)
                true
            }
            R.id.parent_profile -> {
                navController.navigate(R.id.profileFragment)
                true
            }
            R.id.parent_settings -> {
                navController.navigate(R.id.settingsFragment)
                true
            }
            else -> false
        }
    }
}


