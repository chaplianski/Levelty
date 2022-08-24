package com.example.levelty.presenter.ui.kid

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.levelty.R
import com.example.levelty.databinding.FragmentKidDayTasksBinding
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.DateTask
import com.example.levelty.domain.models.Task
import com.example.levelty.presenter.adapters.FragmentDayPersonalTasksAdapter
import com.example.levelty.presenter.adapters.PickerAdapter
import com.example.levelty.presenter.adapters.PickerLayoutManager
import com.example.levelty.presenter.adapters.kid.DayKidDetailTaskFragmentTasksAdapter
import com.example.levelty.presenter.adapters.kid.TaskPickerLayoutManager
import com.example.levelty.presenter.factories.kid.DayKidDetailTaskFragmentViewModelFactory
import com.example.levelty.presenter.utils.getTodayDate
import com.example.levelty.presenter.viewmodels.kid.DayKidDetailTaskFragmentViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class KidDayTasksFragment : Fragment() {

    @Inject
    lateinit var dayKidDetailTaskFragmentViewModelFactory: DayKidDetailTaskFragmentViewModelFactory
    val dayKidDetailTaskFragmentViewModel: DayKidDetailTaskFragmentViewModel by viewModels { dayKidDetailTaskFragmentViewModelFactory }

    var _binding: FragmentKidDayTasksBinding? = null
    val binding: FragmentKidDayTasksBinding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .taskKidDetailFragmentInject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKidDayTasksBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var checkedDate = getTodayDate()
        val tasksWheelRV = binding.layoutFragmentDayKidWheelTasks.rvKidTasksCardsWheel
        val progressWheelText =
            binding.layoutFragmentDayKidWheelTasks.tvKidTasksCardsWheelProgressText
        val progressWheelView =
            binding.layoutFragmentDayKidWheelTasks.pbKidTasksCardsWheelProgressView

        val datesWheelLayout = binding.datesWheelLayout.viewDateWheelLayout
        val tasksWheelLayout = binding.layoutFragmentDayKidWheelTasks.viewKidTasksCardsWheelLayout
        val tasksListLayout = binding.layoutFragmentDayKidListTasks.viewKidDayTasksListLayout
        val noTasksPastLayout = binding.layoutKidDayTasksNoTaskInPast.viewKidMessageNoTaskInPast
        val noTaskFutureLayout = binding.layoutKidDayTasksNoTaskInFuture.viewKidDialogDialogNoTaskInFuture
        val skipTaskLayout = binding.layoutKidSkipTaskDialog.viewKidSkipTaskDialogLayout

        val skipReasonField = binding.layoutKidSkipTaskDialog.otlSkipTaskMessageContainer
        val skipReasonText = binding.layoutKidSkipTaskDialog.etSkipTaskMessageText
        val skipButton = binding.layoutKidSkipTaskDialog.btSkipTaskMessageSent


        //***** Skip message block *****
        skipButton.setOnClickListener {
            if (skipReasonText.text?.isBlank() == true){
                skipReasonField.error = "Please, enter reason"
            }else{
                // TODO add sent data to server
                skipReasonText.setText("")
                dayKidDetailTaskFragmentViewModel.getTaskList()
            }
        }
        changeErrorStatus(skipReasonText, skipReasonField)

        // ***** Date Wheel ******

        val pickerLayoutManager = getWheelPickerLayoutManager(view)
        // *** Listen current date and sent to viewModel
        pickerLayoutManager.setOnScrollStopListener(object :
            PickerLayoutManager.ScrollStopListener {
            override fun selectedView(view: View?) {
                val day = view?.findViewById<TextView>(R.id.tv_date_item_number)
                val month = view?.findViewById<TextView>(R.id.tv_date_item_month)
                val year = view?.findViewById<TextView>(R.id.tv_date_item_year)
                dayKidDetailTaskFragmentViewModel.transferDateValue("${day?.text} ${month?.text} ${year?.text}")

            }
        })

        dayKidDetailTaskFragmentViewModel.currentDay.observe(this.viewLifecycleOwner) { date ->

            checkedDate = date
            dayKidDetailTaskFragmentViewModel.getTaskList()
//            Toast.makeText(
//                context,
//                "Selected date ${date}", Toast.LENGTH_SHORT
//            ).show()
        }

        // ******  Tasks wheel  ********

//        val tasksRV: RecyclerView =
//            view.findViewById(R.id.rv_fragment_day_kid_detail_tasks_tasks_list)
        val taskPickerLayoutManager =
            TaskPickerLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        dayKidDetailTaskFragmentViewModel.getTaskList()

        dayKidDetailTaskFragmentViewModel.taskList.observe(this.viewLifecycleOwner) { tasks ->

            datesWheelLayout.visibility = View.VISIBLE
            when (true) {
                (checkedDate == getTodayDate() && tasks.isNotEmpty()) -> {
                    // *** Fill wheel cards tasks
                    noTaskFutureLayout.visibility = View.INVISIBLE
                    noTasksPastLayout.visibility = View.INVISIBLE
                    tasksListLayout.visibility = View.INVISIBLE
                    tasksWheelLayout.visibility = View.VISIBLE
                    skipTaskLayout.visibility = View.INVISIBLE

                    val dayKidDetailTaskFragmentTasksAdapter =
                        DayKidDetailTaskFragmentTasksAdapter(tasks, tasksWheelRV)
                    //         val dayKidDetailTaskFragmentTasksAdapter = DayKidDetailTaskFragmentTasksAdapter(it)
                    tasksWheelRV.onFlingListener = null
                    val tasksSnapHelper: SnapHelper = LinearSnapHelper()
                    tasksSnapHelper.attachToRecyclerView(tasksWheelRV)

                    if (tasks.size > 1) {
                        tasksWheelRV.layoutManager = taskPickerLayoutManager
                    }

                    tasksWheelRV.adapter = dayKidDetailTaskFragmentTasksAdapter

                    lifecycleScope.launchWhenCreated {
                        delay(100)
                        tasksWheelRV.scrollToPosition(0)
                    }

                    dayKidDetailTaskFragmentTasksAdapter.checkTaskElementListener = object : DayKidDetailTaskFragmentTasksAdapter.CheckTaskElementListener{
                        override fun clickOnDoneButton(task: Task) {
                            findNavController().navigate(R.id.action_kidDayTasksFragment_to_kidSuccessCloseTaskFragment)
                        }

                        override fun clickOnSkipButton(task: Task) {
                            noTaskFutureLayout.visibility = View.INVISIBLE
                            datesWheelLayout.visibility = View.INVISIBLE
                            noTasksPastLayout.visibility = View.INVISIBLE
                            tasksListLayout.visibility = View.INVISIBLE
                            tasksWheelLayout.visibility = View.INVISIBLE
                            skipTaskLayout.visibility = View.VISIBLE
                        }

                    }

                    //***** Set progress indicators *******

                    val allTaskCount = tasks.size
                    val completedTaskCount = allTaskCount - getUpcomingCountTask(tasks)
                    progressWheelText.text = "$completedTaskCount of $allTaskCount are completed"
                    progressWheelView.max = allTaskCount
                    progressWheelView.progress = completedTaskCount
                }

                (checkedDate > getTodayDate() && tasks.isEmpty()) -> {
                    noTaskFutureLayout.visibility = View.VISIBLE
                    noTasksPastLayout.visibility = View.INVISIBLE
                    tasksListLayout.visibility = View.INVISIBLE
                    tasksWheelLayout.visibility = View.INVISIBLE
                    skipTaskLayout.visibility = View.INVISIBLE
                }
                (checkedDate < getTodayDate() && tasks.isEmpty()) -> {
                    noTaskFutureLayout.visibility = View.INVISIBLE
                    noTasksPastLayout.visibility = View.VISIBLE
                    tasksListLayout.visibility = View.INVISIBLE
                    tasksWheelLayout.visibility = View.INVISIBLE
                    skipTaskLayout.visibility = View.INVISIBLE
                }
                else -> {
                    noTaskFutureLayout.visibility = View.INVISIBLE
                    noTasksPastLayout.visibility = View.INVISIBLE
                    tasksListLayout.visibility = View.VISIBLE
                    tasksWheelLayout.visibility = View.INVISIBLE
                    skipTaskLayout.visibility = View.INVISIBLE
                    fetchListTaskLayout(tasks, view)
                }
            }
        }
    }

    private fun fetchListTaskLayout(
        tasks: List<Task>,
        view: View
    ) {
        val tasksListRV = binding.layoutFragmentDayKidListTasks.rvKidDayTasksList

        val fragmentDayPersonalTasksAdapter = FragmentDayPersonalTasksAdapter(tasks)
        tasksListRV.adapter = fragmentDayPersonalTasksAdapter
        val bundle = Bundle()
        fragmentDayPersonalTasksAdapter.shortOnClickListener =
            object : FragmentDayPersonalTasksAdapter.ShortOnClickListener {
                override fun ShortClick(task: Task) {
                    bundle.putParcelable("current task", task)
                    val navController = Navigation.findNavController(view)
                    navController.navigate(
                        R.id.action_dayPersonalTasksFragment_to_dayPersonalTasksDialogFragment,
                        bundle
                    )
                }
            }

        //***** Set progress indicators *******
        val progressListText = binding.layoutFragmentDayKidListTasks.tvKidDayTasksListProgressText
        val progressListView = binding.layoutFragmentDayKidListTasks.pbKidDayTasksListProgressView
        val allTaskCount = tasks.size
        val completedTaskCount = allTaskCount - getUpcomingCountTask(tasks)
        progressListText.text = "$completedTaskCount of $allTaskCount are completed"
        progressListView.max = allTaskCount
        progressListView.progress = completedTaskCount
    }

    private fun getWheelPickerLayoutManager(view: View): PickerLayoutManager {
        val dateWheelRV = binding.datesWheelLayout.rvCommonDateWheelDates
//        val dateRV: RecyclerView = view.findViewById(R.id.rv_fragment_day_kid_detail_task_date)
        val pickerLayoutManager =
            PickerLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val beginDaysCount = 365
        val listDates = getDateList(365, 377)

        val dateTasksFragmentAdapter =
            context?.let { PickerAdapter(it, listDates, dateWheelRV) }
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(dateWheelRV)
        dateWheelRV.layoutManager = pickerLayoutManager
        dateWheelRV.adapter = dateTasksFragmentAdapter

        lifecycleScope.launchWhenCreated {
            delay(51)
            dateWheelRV.scrollToPosition(beginDaysCount + 1)
        }
        return pickerLayoutManager
    }

    private fun getDateList(daysBeforeCurrent: Int, daysAfterCurrent: Int): List<DateTask> {
        val formatDateDay = SimpleDateFormat("dd", Locale.getDefault())
        val formatDateMonth = SimpleDateFormat("MMMM", Locale.getDefault())
        val formatDateYear = SimpleDateFormat("yyyy")
        val todayDate = Calendar.getInstance()
        todayDate.add(Calendar.DATE, -daysAfterCurrent)

        val dateValues = mutableListOf<DateTask>()
        var counter = 0L
        for (day in 1..(daysBeforeCurrent + daysAfterCurrent)) {
            todayDate.add(Calendar.DATE, 1)
            dateValues.add(
                DateTask(
                    counter, formatDateDay.format(todayDate.timeInMillis),
                    formatDateMonth.format(todayDate.timeInMillis),
                    formatDateYear.format(todayDate.timeInMillis)
                )
            )
            counter++
        }
        return dateValues.toList()
    }

    private fun getUpcomingCountTask(tasksList: List<Task>): Int {
        var count = 0
        for (task in tasksList) {
            if (task.taskStatus == "Upcoming") count++
        }
        return count
    }

    private fun changeErrorStatus(
        text: TextInputEditText,
        field: TextInputLayout
    ) {
        text.doOnTextChanged { inputText, _, _, _ ->
            if (inputText?.length!! > 0) {
                field.error = null
            }
        }
    }
}