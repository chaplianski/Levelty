package com.example.levelty.presenter.ui.parent

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.levelty.R
import com.example.levelty.databinding.FragmentNewTaskBinding
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.presenter.adapters.parent.AddingStringChipsAdapter
import com.example.levelty.presenter.adapters.parent.SampleStringChipsAdapter
import com.example.levelty.presenter.factories.parent.NewTaskViewModelFactory
import com.example.levelty.presenter.utils.dateTimeToShortString
import com.example.levelty.presenter.utils.dayToMls
import com.example.levelty.presenter.utils.getTodayDateMls
import com.example.levelty.presenter.utils.getTodayShortDate
import com.example.levelty.presenter.viewmodels.parent.NewTaskViewModel
import com.google.android.material.chip.Chip
import javax.inject.Inject


class ParentNewTaskFragment : Fragment() {

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
        _binding = FragmentNewTaskBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskText: EditText = binding.etFragmentNewTaskTaskText
//        val category: Chip = binding.chipFragmentNewTaskCategory
//        val points: Chip = binding.chipFragmentNewTaskPoints
//        val date: Chip = binding.chipFragmentNewTaskDate
//        val startTime: Chip = binding.chipFragmentNewTaskStartTime
//        val repeat: Chip = binding.chipFragmentNewTaskRepeat
//        val parentPurpose: Chip = binding.chipFragmentNewTaskParentsPurpose
//        val kidsInterest: Chip = binding.chipFragmentNewTaskKidsInterest
        val closeButton: ImageView = binding.ivFragmentNewTaskClose
        val saveButton: Button = binding.btFragmentNewTaskSave

        val kidsRV: RecyclerView = binding.rvFragmentNewTaskKids
        val purposeRV: RecyclerView = binding.rvFragmentNewTaskPurposes
        val pointRV: RecyclerView = binding.rvFragmentNewTaskPoints
        val repeatRV: RecyclerView = binding.rvFragmentNewTaskRepeat
        val dateRV: RecyclerView = binding.rvFragmentNewTaskSetDate


        var kidId = 0
        var categoryValue = ""
        var dateValue = ""
        var startTimeValue = ""
        var pointsValue = 0
        var repeatValue = 0
        var parentPurposeValue = ""
        var kidInterestValue = ""


        newTaskViewModel.getKids()
        newTaskViewModel.kids.observe(this.viewLifecycleOwner) { kids ->
            val kidsAdapter =
                SampleStringChipsAdapter(kids.map { kid -> kid.user?.firstName.toString() })
//            kidsRV.layoutManager = LinearLayoutManager(context)
            kidsRV.adapter = kidsAdapter
            kidsAdapter.chipClickListener = object : SampleStringChipsAdapter.ChipClickListener {
                override fun clickChip(item: String, position: Int) {
                    if (kids[position].id != null){
                        kidId = kids[position].id!!
                    }
                }
            }
        }

        newTaskViewModel.getPurpose()
        newTaskViewModel.purpose.observe(this.viewLifecycleOwner) { purposes ->
            val purposeAdapter =
                SampleStringChipsAdapter(purposes.map { purpose -> purpose.purposeName })
            purposeRV.adapter = purposeAdapter
            purposeAdapter.chipClickListener = object : SampleStringChipsAdapter.ChipClickListener {
                override fun clickChip(item: String, position: Int) {
                    parentPurposeValue = item
                }
            }
        }

        newTaskViewModel.getDateVariants()
        newTaskViewModel.dates.observe(this.viewLifecycleOwner) { dates ->

            val datesAdapter = AddingStringChipsAdapter(dates)
            dateRV.adapter = datesAdapter
            datesAdapter.chipClickListener = object : AddingStringChipsAdapter.ChipClickListener{
                override fun clickChip(item: String, isLast: Boolean, position: Int) {
                    if (isLast) {
                        findNavController().navigate(R.id.action_newTaskFragment_to_dateChooseFragment)
                    } else dateValue = when (item) {
                        "Today" -> getTodayShortDate()
                        "Tomorrow" -> getTodayDateMls()?.plus(dayToMls(1))
                            ?.let { dateTimeToShortString(it) }.toString()
                        else -> getTodayShortDate()
                    }
                }
            }
        }

        newTaskViewModel.getRepeatVariants()
        newTaskViewModel.repeats.observe(this.viewLifecycleOwner) { repeats ->

            val repeatsAdapter = AddingStringChipsAdapter(repeats)
            repeatRV.adapter = repeatsAdapter
            repeatsAdapter.chipClickListener = object : AddingStringChipsAdapter.ChipClickListener{
                override fun clickChip(item: String, isLast: Boolean, position: Int) {
                    if (isLast) {
                        findNavController().navigate(R.id.action_newTaskFragment_to_repeatChoiceDialogFragment)
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

        newTaskViewModel.getPointsVariants()
        newTaskViewModel.points.observe(this.viewLifecycleOwner) { points ->

            val pointsAdapter = AddingStringChipsAdapter(points)
            pointRV.adapter = pointsAdapter
            pointsAdapter.chipClickListener = object : AddingStringChipsAdapter.ChipClickListener{
                override fun clickChip(item: String, isLast: Boolean, position: Int) {
                    if (isLast) {
                        val bundle = Bundle()
                        bundle.putString(TASK_NAME, taskText.text.toString())
                        findNavController().navigate(R.id.action_newTaskFragment_to_pointChooseFragment)
                    } else pointsValue = item.toInt()
                }
            }
        }


        val navController = findNavController(view)
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Int>("points")?.observe(
            viewLifecycleOwner
        ) {
//            points.text = it.toString()
//            points.setTextColor(Color.BLACK)
            pointsValue = it
        }
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
//                parentPurpose.text = it.toString()
//                parentPurpose.setTextColor(Color.BLACK)
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
//
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("date")
            ?.observe(
                viewLifecycleOwner
            ) {
//                date.text = it.toString()
//                date.setTextColor(Color.BLACK)
                dateValue = it
            }
//
//        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("start time")
//            ?.observe(
//                viewLifecycleOwner
//            ) {
//                startTime.text = it.toString()
//                startTime.setTextColor(Color.BLACK)
//                startTimeValue = it
//            }

        closeButton.setOnClickListener {
            navController.popBackStack()
        }

        saveButton.setOnClickListener {
            val taskNameValue = taskText.text.toString()
            val isPeriodic = repeatValue > 1
            Log.d("MyLog", "title = $taskNameValue, category_id = 0, parent_purpose = $parentPurposeValue, cost = $pointsValue, start_date = $dateValue, due_date = 0, is_periodic = $isPeriodic, repeat_interval = $repeatValue" +
                    ", custom_schedule, assignee_id = $kidId, child_interest_ids")
//            val newTask = Task(0,taskNameValue,categoryValue,pointsValue,dateValue,startTimeValue,repeatValue,parentPurposeValue,kidInterestValue,kidName, "Upcoming")
//            newTaskViewModel.addTask(newTask)
            findNavController().navigate(R.id.action_newTaskFragment_to_tasksFragment)
        }


    }




    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        val TASK_NAME = "task name"
    }

}