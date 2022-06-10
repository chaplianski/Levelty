package com.example.levelty.presenter.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.example.levelty.R
import com.google.android.material.chip.Chip


class NewTaskFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //      val taskText: EditText = view.findViewById(R.id.et_fragment_new_task_task)
        val category: Chip = view.findViewById(R.id.chip_fragment_new_task_category)
        val points: Chip = view.findViewById(R.id.chip_fragment_new_task_points)
        val date: Chip = view.findViewById(R.id.chip_fragment_new_task_date)
        val startTime: Chip = view.findViewById(R.id.chip_fragment_new_task_start_time)
        val repeat: Chip = view.findViewById(R.id.chip_fragment_new_task_repeat)
        val parentPurpose: Chip = view.findViewById(R.id.chip_fragment_new_task_parents_purpose)
        val kidsInterest: Chip = view.findViewById(R.id.chip_fragment_new_task_kids_interest)


       category.setOnClickListener {
           val navController = Navigation.findNavController(view)
           navController.navigate(R.id.action_newTaskFragment_to_categoryChooseFragment)

       }

        date.setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_newTaskFragment_to_dateChooseFragment22)
        }

        startTime.setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_newTaskFragment_to_startTimeChooseFragment)
        }

        points.setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_newTaskFragment_to_pointChooseFragment)
        }

        parentPurpose.setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_newTaskFragment_to_parentsPurposeChooseFragment)
        }

        repeat.setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_newTaskFragment_to_repiatChooseFragment)
        }

        val navController = Navigation.findNavController(view)
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Int>("points")?.observe(
            viewLifecycleOwner) {
            points.text = it.toString()
            points.setTextColor(Color.BLACK)
        }

        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("repeat")?.observe(
            viewLifecycleOwner) {
            repeat.text = it.toString()
            repeat.setTextColor(Color.BLACK)

        }


    }




}