package com.example.levelty.presenter.ui.parent

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.levelty.R
import com.example.levelty.databinding.FragmentEditTaskBinding
import com.example.levelty.databinding.FragmentNewTaskBinding
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.Task
import com.example.levelty.presenter.factories.parent.EditTaskFragmentViewModelFactory
import com.example.levelty.presenter.viewmodels.parent.EditTaskFragmentViewModel
import com.google.android.material.chip.Chip
import javax.inject.Inject


class EditTaskFragment : Fragment() {

    @Inject
    lateinit var editTaskFragmentViewModelFactory: EditTaskFragmentViewModelFactory
    val editTaskFragmentViewModel: EditTaskFragmentViewModel by viewModels { editTaskFragmentViewModelFactory }

    var _binding: FragmentEditTaskBinding? = null
    val binding get() = _binding!!

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .editTaskFragmentInject(this)
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditTaskBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskTitle: TextView = binding.tvFragmentEditTaskTitle
        val taskKidName: TextView = binding.tvFragmentEditTaskKidName
        val taskText: EditText = binding.etFragmentEditTaskTaskText
        val purpose: Chip = binding.chipFragmentEditTaskPurpose
        val points: Chip = binding.chipFragmentEditTaskPoints
        val date: Chip = binding.chipFragmentEditTaskDate
//        val taskRemainTime: Chip = binding.chipFragmentEditTaskRemaindTime
        val startTime: Chip = binding.chipFragmentEditTaskRemaindTime
        val repeat: Chip = binding.chipFragmentEditTaskRepeat
        val category: Chip = binding.chipFragmentEditTaskCategory
        val kidsInterest: Chip = binding.chipFragmentEditTaskKidsInterest
        val closeButton: ImageView = binding.ivFragmentEditTaskClose
        val saveButton: Button = binding.btFragmentEditTaskSave

        val task = arguments?.getParcelable<Task>("current task")

        var categoryValue = ""
        var dateValue = ""
        var startTimeValue = ""
        var pointsValue = 0
        var repeatValue = ""
        var parentPurposeValue = ""
        var kidInterestValue = ""
        val status = task?.taskStatus ?: "Upcoming"

        val kidName = "Andrew"

        // ***** Fill data ******

        taskTitle.text = task?.taskName
        taskKidName.text = "For ${task?.kidName}"
        taskText.setText(task?.taskName)
        purpose.text = task?.taskParentPurpose
        points.text = task?.taskPoints.toString()
        date.text = task?.taskDate
        startTime.text = task?.taskStartTime
        repeat.text = task?.taskRepeat
        category.text = task?.taskCategory
        kidsInterest.text = task?.taskKidsInterest



//        category.setOnClickListener {
//            val navController = Navigation.findNavController(view)
//            navController.navigate(R.id.action_editTaskFragment_to_categoryChooseFragment)
//
//        }
//
//        date.setOnClickListener {
//            val navController = Navigation.findNavController(view)
//            navController.navigate(R.id.action_editTaskFragment_to_dateChooseFragment)
//        }
//
//        startTime.setOnClickListener {
//            val navController = Navigation.findNavController(view)
//            navController.navigate(R.id.action_editTaskFragment_to_startTimeChooseFragment)
//        }
//
//        points.setOnClickListener {
//            val navController = Navigation.findNavController(view)
//            navController.navigate(R.id.action_editTaskFragment_to_pointChooseFragment)
//        }
//
//        purpose.setOnClickListener {
//            val navController = Navigation.findNavController(view)
//            navController.navigate(R.id.action_editTaskFragment_to_parentsPurposeChooseFragment)
//        }
//
//        repeat.setOnClickListener {
//            val navController = Navigation.findNavController(view)
//            navController.navigate(R.id.action_editTaskFragment_to_repeatChooseFragment)
//        }
//
//        kidsInterest.setOnClickListener {
//            val navController = Navigation.findNavController(view)
//            navController.navigate(R.id.action_editTaskFragment_to_kidsInterestChooseFragment)
//
//        }

        val navController = Navigation.findNavController(view)
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
                purpose.text = it.toString()
                purpose.setTextColor(Color.BLACK)
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
            view.findNavController().popBackStack()
        }

        saveButton.setOnClickListener {
            val taskNameValue = taskText.text.toString()
            val editedTask = Task(0,taskNameValue,categoryValue,pointsValue,dateValue,startTimeValue,repeatValue,parentPurposeValue,kidInterestValue,kidName, status)
            editTaskFragmentViewModel.updateTask(editedTask)
   //         val navController = Navigation.findNavController(view)
            view.findNavController().navigate(R.id.action_editTaskFragment_to_dayPersonalTasksFragment)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}