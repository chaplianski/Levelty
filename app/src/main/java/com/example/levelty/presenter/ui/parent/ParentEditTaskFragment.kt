package com.example.levelty.presenter.ui.parent

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.levelty.R
import com.example.levelty.databinding.FragmentEditTaskBinding
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.EditTask
import com.example.levelty.domain.models.NewTask
import com.example.levelty.presenter.adapters.parent.AddingStringChipsAdapter
import com.example.levelty.presenter.adapters.parent.SampleStringChipsAdapter
import com.example.levelty.presenter.factories.parent.EditTaskFragmentViewModelFactory
import com.example.levelty.presenter.utils.CURRENT_TASK
import com.example.levelty.presenter.utils.TASK_NAME
import com.example.levelty.presenter.utils.dateShortStringToFullString
import com.example.levelty.presenter.viewmodels.parent.ParentEditTaskFragmentViewModel
import javax.inject.Inject


class ParentEditTaskFragment : Fragment() {

    @Inject
    lateinit var editTaskFragmentViewModelFactory: EditTaskFragmentViewModelFactory
    val parentEditTaskFragmentViewModel: ParentEditTaskFragmentViewModel by viewModels { editTaskFragmentViewModelFactory }

    var _binding: FragmentEditTaskBinding? = null
    val binding get() = _binding!!

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .parentEditTaskFragmentInject(this)
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

        val taskTitle: TextView = binding.tvParentEditTaskFragmentTitle
        val taskField = binding.etParentEditTaskFragmentTaskContainer
        val taskName = binding.etFparentEditTaskFragmentTaskText
        val kidRV = binding.rvParentEditTaskFragmentKids
        val purposeRV = binding.rvParentEditTaskFragmentPurposes
        val pointsRV = binding.rvParentEditTaskFragmentPoints
        val dateText = binding.tvParentEditTaskFragmentDate
        val changeDataButton = binding.ivParentEditTaskFragmentDate
        val repeatTitle = binding.tvParentEditTaskFragmentRepeat
        val repeatRV = binding.rvParentEditTaskFragmentRepeat
        val setNotificationButton = binding.tvParentEditTaskFragmentSetNotification
        val closeButton: ImageView = binding.ivParentEditTaskFragmentClose
        val saveButton: Button = binding.btParentEditTaskFragmentSave
        val fromFragment = arguments?.getString(CategoryFragment.CATEGORY_MARK)

        val task = arguments?.getParcelable<EditTask>(CURRENT_TASK)

//        val taskId = arguments?.getInt(CURRENT_TASK_ID)
//        if (taskId != null){
//            parentEditTaskFragmentViewModel.getCurrentTask(taskId)
//        }
//
//        newTaskViewModel.task.observe(this.viewLifecycleOwner){ task ->
//            if (task.assigneeId != null)
//                kidId = task.assigneeId!!
//
//        }

        val kidId = task?.assigneeId
        val isPeriodic = task?.isPeriodic
        var categoryValue = ""
        var dateValue = ""
        var startTimeValue = ""
        var pointsValue = 0
        var repeatValue = 0
        var parentPurposeValue = ""
        var kidInterestValue = ""
//        val status = task?.taskStatus ?: "Upcoming"

        val kidName = "Andrew"

        // ***** Fill data ******

//        taskTitle.text = task?.title
//        taskKidName.text = "For ${task?.kidName}"
        taskName.setText(task?.title)
        dateText.text = task?.choreDate?.let { dateShortStringToFullString(it) }

        parentEditTaskFragmentViewModel.getKids()
        parentEditTaskFragmentViewModel.kids.observe(this.viewLifecycleOwner){ kids ->
            val kidPosition = -1
            val kidList = mutableListOf<String>()
            for (kid in kids){
                if (kid.id == task?.assigneeId) kidList.add(kid.user?.firstName.toString())
            }
            val kidAdapter = SampleStringChipsAdapter(kidList, kidPosition)
            kidRV.adapter = kidAdapter

        }

        parentEditTaskFragmentViewModel.getPurpose()
        parentEditTaskFragmentViewModel.purpose.observe(this.viewLifecycleOwner){ purposes ->
            var purposePosition = -1
            for (purpose in purposes.withIndex()){
                if (task?.parentPurpose == purpose.value) purposePosition = purpose.index
            }
            val purposeAdapter = SampleStringChipsAdapter(purposes, purposePosition)
            purposeRV.adapter = purposeAdapter
        }

        parentEditTaskFragmentViewModel.getPointsVariants()
        parentEditTaskFragmentViewModel.points.observe(this.viewLifecycleOwner){ coins ->
            var coinPosition = -1
            for (coin in coins.withIndex()){
//                Log.d("MyLog", "coin = ${coin.value}, taskCoin = ${task?.cost.toString()}")
                if (task?.cost.toString() == coin.value){
                    coinPosition = coin.index
                }
            }
            val coinsAdapter = AddingStringChipsAdapter(coins, coinPosition)
            pointsRV.adapter = coinsAdapter
            coinsAdapter.chipClickListener = object : AddingStringChipsAdapter.ChipClickListener{
                override fun clickChip(item: String, isLast: Boolean, position: Int) {
                    if (isLast) {
                        val bundle = Bundle()
                        bundle.putString(TASK_NAME, task?.title)
//                        Log.d("MyLog", "task name new task = ${taskText.text}")
                        findNavController().navigate(R.id.action_editTaskFragment_to_pointChooseFragment, bundle)
                    } else pointsValue = item.toInt()
                }
            }
        }

        if (task?.isPeriodic == true){
            repeatTitle.visibility = View.VISIBLE
            repeatRV.visibility = View.VISIBLE
            parentEditTaskFragmentViewModel.getRepeatVariants()
            parentEditTaskFragmentViewModel.repeats.observe(this.viewLifecycleOwner){ repeats ->

                var repeatPosition = -1
                val currentRepeatInterval = getRepeatInterval(task.repeatInterval.toString())
                for (repeat in repeats.withIndex()){
                    repeatPosition = when (currentRepeatInterval.toString()){
                        repeat.value -> repeat.index
                        "-2" -> repeats.size-1
                        else -> -1
                    }
                }
                val repeatAdapter = AddingStringChipsAdapter(repeats, repeatPosition)
                repeatRV.adapter = repeatAdapter
                repeatAdapter.chipClickListener = object : AddingStringChipsAdapter.ChipClickListener{
                    override fun clickChip(item: String, isLast: Boolean, position: Int) {
                        if (isLast) {
                            findNavController().navigate(R.id.action_editTaskFragment_to_repeatChoiceDialogFragment)
                        } else repeatValue = when (item) {
                            "Don't repeat" -> 0
                            "Daily" -> 1
                            "Every 3 days" -> 3
                            "Every week" -> 7
                            "Every month" -> 30
                            else -> 0
                        }
                    }
                }
            }
        }

        changeDataButton.setOnClickListener {
            findNavController().navigate(R.id.action_editTaskFragment_to_dateChooseFragment)
        }




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
//        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Int>("points")?.observe(
//            viewLifecycleOwner
//        ) {
//            points.text = it.toString()
//            points.setTextColor(Color.BLACK)
//            pointsValue = it
//        }
//
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Int>("repeat")
            ?.observe(
                viewLifecycleOwner
            ) {
//                repeat.text = it.toString()
//                repeat.setTextColor(Color.BLACK)
                repeatValue = it

            }
//
//
//        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("purpose")
//            ?.observe(
//                viewLifecycleOwner
//            ) {
//                purpose.text = it.toString()
//                purpose.setTextColor(Color.BLACK)
//                parentPurposeValue = it
//            }
//
//        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("interest")
//            ?.observe(
//                viewLifecycleOwner
//            ) {
//                kidsInterest.text = it.toString()
//                kidsInterest.setTextColor(Color.BLACK)
//                kidInterestValue = it
//            }
//
//        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("category")
//            ?.observe(
//                viewLifecycleOwner
//            ) {
//                category.text = it.toString()
//                category.setTextColor(Color.BLACK)
//                categoryValue = it
//            }

        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("date")
            ?.observe(
                viewLifecycleOwner
            ) {
                dateText.text = it.toString()
//                date.setTextColor(Color.BLACK)
                dateValue = it
            }

//        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("start time")
//            ?.observe(
//                viewLifecycleOwner
//            ) {
//                startTime.text = it.toString()
//                startTime.setTextColor(Color.BLACK)
//                startTimeValue = it
//            }

        closeButton.setOnClickListener {
            view.findNavController().popBackStack()
        }

        saveButton.setOnClickListener {

            val taskNameValue = taskName.text.toString()
            val editedTask = NewTask(null, pointsValue, null, dateValue, null, parentPurposeValue, null, repeatValue, taskNameValue, isPeriodic, dateValue, kidId)
            Log.d("MyLog", "title = $taskNameValue, category_id = 0, parent_purpose = $parentPurposeValue, cost = $pointsValue, start_date = $dateValue, due_date = 0, is_periodic = $isPeriodic, repeat_interval = $repeatValue" +
                    ", custom_schedule, assignee_id = $kidId, child_interest_ids")
//            parentEditTaskFragmentViewModel.updateTask(editedTask)
   //         val navController = Navigation.findNavController(view)
            if (fromFragment == "from category fragment") view.findNavController().navigate(R.id.action_editTaskFragment_to_categoryFragment)
            else view.findNavController().navigate(R.id.action_editTaskFragment_to_dayPersonalTasksFragment)
        }


    }

    private fun getRepeatInterval(repeatInterval: String): Int {
        return when (repeatInterval){
            "Doesn't repeat" -> 0
            "Daily" -> 1
            "Weekly" -> 7
            "Every Monthly" -> 30
            else -> -2
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}