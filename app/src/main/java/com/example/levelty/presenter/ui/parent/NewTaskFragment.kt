package com.example.levelty.presenter.ui.parent

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import com.example.levelty.R
import com.example.levelty.databinding.FragmentNewTaskBinding
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.Task
import com.example.levelty.presenter.factories.parent.NewTaskViewModelFactory
import com.example.levelty.presenter.viewmodels.parent.NewTaskViewModel
import com.google.android.material.chip.Chip
import javax.inject.Inject


class NewTaskFragment : Fragment() {

    @Inject
    lateinit var newTaskViewModelFactory: NewTaskViewModelFactory
    val newTaskViewModel: NewTaskViewModel by viewModels { newTaskViewModelFactory }

    var _binding: FragmentNewTaskBinding? = null
    val binding get() = _binding!!


    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .newTaskFragmentInject(this)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewTaskBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskText: EditText = binding.etFragmentNewTaskTaskText
        val category: Chip = binding.chipFragmentNewTaskCategory
        val points: Chip = binding.chipFragmentNewTaskPoints
        val date: Chip = binding.chipFragmentNewTaskDate
        val startTime: Chip = binding.chipFragmentNewTaskStartTime
        val repeat: Chip = binding.chipFragmentNewTaskRepeat
        val parentPurpose: Chip = binding.chipFragmentNewTaskParentsPurpose
        val kidsInterest: Chip = binding.chipFragmentNewTaskKidsInterest
        val closeButton: ImageView = binding.ivFragmentNewTaskClose
        val saveButton: Button = binding.btFragmentNewTaskSave
        val kidName = "Andrew"

        var categoryValue = ""
        var dateValue = ""
        var startTimeValue = ""
        var pointsValue = 0
        var repeatValue = ""
        var parentPurposeValue = ""
        var kidInterestValue = ""


        category.setOnClickListener {
            val navController = findNavController(view)
            navController.navigate(R.id.action_newTaskFragment_to_categoryChooseFragment)

        }

        date.setOnClickListener {
            val navController = findNavController(view)
            navController.navigate(R.id.action_newTaskFragment_to_dateChooseFragment22)
        }

        startTime.setOnClickListener {
            val navController = findNavController(view)
            navController.navigate(R.id.action_newTaskFragment_to_startTimeChooseFragment)
        }

        points.setOnClickListener {
            val navController = findNavController(view)
            navController.navigate(R.id.action_newTaskFragment_to_pointChooseFragment)
        }

        parentPurpose.setOnClickListener {
            val navController = findNavController(view)
            navController.navigate(R.id.action_newTaskFragment_to_parentsPurposeChooseFragment)
        }

        repeat.setOnClickListener {
            val navController = findNavController(view)
            navController.navigate(R.id.action_newTaskFragment_to_repiatChooseFragment)
        }

        kidsInterest.setOnClickListener {
            val navController = findNavController(view)
            navController.navigate(R.id.action_newTaskFragment_to_kidsInterestChooseFragment)

        }

        val navController = findNavController(view)
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Int>("points")?.observe(
            viewLifecycleOwner
        ) {
            points.text = it.toString()
            points.setTextColor(Color.BLACK)
            pointsValue = it
        }

        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("repeat")
            ?.observe(
                viewLifecycleOwner
            ) {
                repeat.text = it.toString()
                repeat.setTextColor(Color.BLACK)
                repeatValue = it
            }


        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("purpose")
            ?.observe(
                viewLifecycleOwner
            ) {
                parentPurpose.text = it.toString()
                parentPurpose.setTextColor(Color.BLACK)
                parentPurposeValue = it
            }

        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("interest")
            ?.observe(
                viewLifecycleOwner
            ) {
                kidsInterest.text = it.toString()
                kidsInterest.setTextColor(Color.BLACK)
                kidInterestValue = it
            }

        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("category")
            ?.observe(
                viewLifecycleOwner
            ) {
                category.text = it.toString()
                category.setTextColor(Color.BLACK)
                categoryValue = it
            }

        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("date")
            ?.observe(
                viewLifecycleOwner
            ) {
                date.text = it.toString()
                date.setTextColor(Color.BLACK)
                dateValue = it
            }

        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("start time")
            ?.observe(
                viewLifecycleOwner
            ) {
                startTime.text = it.toString()
                startTime.setTextColor(Color.BLACK)
                startTimeValue = it
            }

        closeButton.setOnClickListener {
            navController.popBackStack()
        }

        saveButton.setOnClickListener {
            val taskNameValue = taskText.text.toString()
            val newTask = Task(0,taskNameValue,categoryValue,pointsValue,dateValue,startTimeValue,repeatValue,parentPurposeValue,kidInterestValue,kidName, "Upcoming")
            newTaskViewModel.addTask(newTask)
            val navController = findNavController(view)
            navController.navigate(R.id.action_newTaskFragment_to_tasksFragment)
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}