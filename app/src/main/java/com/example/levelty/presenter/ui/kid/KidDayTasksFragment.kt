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
import androidx.core.view.ViewCompat
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
import com.example.levelty.domain.models.CreatedTasksItem
import com.example.levelty.domain.models.DateTask
import com.example.levelty.domain.models.ProcessedTask
import com.example.levelty.domain.models.Task
import com.example.levelty.presenter.adapters.FragmentDayPersonalTasksAdapter
import com.example.levelty.presenter.adapters.PickerAdapter
import com.example.levelty.presenter.adapters.PickerLayoutManager
import com.example.levelty.presenter.adapters.kid.KidDayTasksFragmentAdapter
import com.example.levelty.presenter.adapters.kid.TaskPickerLayoutManager
import com.example.levelty.presenter.factories.kid.DayKidDetailTaskFragmentViewModelFactory
import com.example.levelty.presenter.utils.getTodayDate
import com.example.levelty.presenter.viewmodels.kid.DayKidDetailTaskFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
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

        val bottomNavigation = binding.bottomAppBarKidDayTasks
        getKidBottomNavigationBar(bottomNavigation)


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

//        dayKidDetailTaskFragmentViewModel.taskList.observe(this.viewLifecycleOwner) { tasks ->
//            Log.d("MyLog", "checked date = $checkedDate, today = ${getTodayDate()}" )
//            datesWheelLayout.visibility = View.VISIBLE
//            when (true) {
//                (checkedDate == getTodayDate() && tasks.isNotEmpty()) -> {
//                    // *** Fill wheel cards tasks
//                    noTaskFutureLayout.visibility = View.INVISIBLE
//                    noTasksPastLayout.visibility = View.INVISIBLE
//                    tasksListLayout.visibility = View.INVISIBLE
//                    tasksWheelLayout.visibility = View.VISIBLE
//                    skipTaskLayout.visibility = View.INVISIBLE
//
//                    val kidDayTasksFragmentAdapter =
//                        KidDayTasksFragmentAdapter(tasks, tasksWheelRV)
//                    //         val dayKidDetailTaskFragmentTasksAdapter = DayKidDetailTaskFragmentTasksAdapter(it)
//                    tasksWheelRV.onFlingListener = null
//                    val tasksSnapHelper: SnapHelper = LinearSnapHelper()
//                    tasksSnapHelper.attachToRecyclerView(tasksWheelRV)
//
//                    if (tasks.size > 1) {
//                        tasksWheelRV.layoutManager = taskPickerLayoutManager
//                    }
//
//                    tasksWheelRV.adapter = kidDayTasksFragmentAdapter
//
//                    lifecycleScope.launchWhenCreated {
//                        delay(100)
//                        tasksWheelRV.scrollToPosition(0)
//                    }
//
//                    kidDayTasksFragmentAdapter.checkTaskElementListener = object : KidDayTasksFragmentAdapter.CheckTaskElementListener{
//                        override fun clickOnDoneButton(task: CreatedTasksItem) {
//                            findNavController().navigate(R.id.action_kidDayTasksFragment_to_kidSuccessCloseTaskFragment)
//                        }
//
//                        override fun clickOnSkipButton(task: CreatedTasksItem) {
//                            noTaskFutureLayout.visibility = View.INVISIBLE
//                            datesWheelLayout.visibility = View.INVISIBLE
//                            noTasksPastLayout.visibility = View.INVISIBLE
//                            tasksListLayout.visibility = View.INVISIBLE
//                            tasksWheelLayout.visibility = View.INVISIBLE
//                            skipTaskLayout.visibility = View.VISIBLE
//                        }
//
//                        override fun clickOnCancelTaskButton(task: CreatedTasksItem) {
//                            TODO("Not yet implemented")
//                        }
//
//                        override fun clickOnRedoButton(task: CreatedTasksItem) {
//                            TODO("Not yet implemented")
//                        }
//
//                    }
//
//                    //***** Set progress indicators *******
//
//                    val allTaskCount = tasks.size
//                    val completedTaskCount = allTaskCount - getUpcomingCountTask(tasks)
//                    progressWheelText.text = "$completedTaskCount of $allTaskCount are completed"
//                    progressWheelView.max = allTaskCount
//                    progressWheelView.progress = completedTaskCount
//                }
//
//                (checkedDate > getTodayDate() && tasks.isEmpty()) -> {
//                    noTaskFutureLayout.visibility = View.VISIBLE
//                    noTasksPastLayout.visibility = View.INVISIBLE
//                    tasksListLayout.visibility = View.INVISIBLE
//                    tasksWheelLayout.visibility = View.INVISIBLE
//                    skipTaskLayout.visibility = View.INVISIBLE
//                }
//                (checkedDate < getTodayDate() && tasks.isEmpty()) -> {
//                    noTaskFutureLayout.visibility = View.INVISIBLE
//                    noTasksPastLayout.visibility = View.VISIBLE
//                    tasksListLayout.visibility = View.INVISIBLE
//                    tasksWheelLayout.visibility = View.INVISIBLE
//                    skipTaskLayout.visibility = View.INVISIBLE
//                }
//                else -> {
//                    noTaskFutureLayout.visibility = View.INVISIBLE
//                    noTasksPastLayout.visibility = View.INVISIBLE
//                    tasksListLayout.visibility = View.VISIBLE
//                    tasksWheelLayout.visibility = View.INVISIBLE
//                    skipTaskLayout.visibility = View.INVISIBLE
//                    fetchListTaskLayout(tasks, view, checkedDate)
//                }
//            }
//        }
    }

//    private fun fetchListTaskLayout(
//        tasks: List<CreatedTasksItem>,
//        view: View, checkedDate: String
//    ) {
//        val tasksListRV = binding.layoutFragmentDayKidListTasks.rvKidDayTasksList
//
//        val fragmentDayPersonalTasksAdapter = FragmentDayPersonalTasksAdapter(tasks, checkedDate)
//        tasksListRV.adapter = fragmentDayPersonalTasksAdapter
//        val bundle = Bundle()
//        fragmentDayPersonalTasksAdapter.shortOnClickListener =
//            object : FragmentDayPersonalTasksAdapter.ShortOnClickListener {
//                override fun ShortClick(task: ProcessedTask) {
////                    bundle.putParcelable("current task", task)
//                    val navController = Navigation.findNavController(view)
//                    navController.navigate(
//                        R.id.action_dayPersonalTasksFragment_to_dayPersonalTasksDialogFragment,
////                        bundle
//                    )
//                }
//            }
//
//        //***** Set progress indicators *******
//        val progressListText = binding.layoutFragmentDayKidListTasks.tvKidDayTasksListProgressText
//        val progressListView = binding.layoutFragmentDayKidListTasks.pbKidDayTasksListProgressView
//        val allTaskCount = tasks.size
//        val completedTaskCount = allTaskCount - getUpcomingCountTask(tasks, checkedDate)
//        progressListText.text = "$completedTaskCount of $allTaskCount are completed"
//        progressListView.max = allTaskCount
//        progressListView.progress = completedTaskCount
//    }

    private fun getWheelPickerLayoutManager(view: View): PickerLayoutManager {
        val dateWheelRV = binding.datesWheelLayout.rvCommonDateWheelDates
//        val dateRV: RecyclerView = view.findViewById(R.id.rv_fragment_day_kid_detail_task_date)
        val pickerLayoutManager =
            PickerLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        pickerLayoutManager.disableScrolling()
        val beginDaysCount = 4
        val listDates = getDateList(beginDaysCount, 377)

        val dateTasksFragmentAdapter =
            context?.let { PickerAdapter(it, listDates, dateWheelRV) }
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(dateWheelRV)
        dateWheelRV.layoutManager = pickerLayoutManager
        dateWheelRV.adapter = dateTasksFragmentAdapter

        dateTasksFragmentAdapter?.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver(){
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                dateWheelRV.scrollToPosition(beginDaysCount-1)
            }

//            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
//                dateWheelRV.smoothScrollToPosition(beginDaysCount+1)
//                pickerLayoutManager.disableScrolling()
//            }

        })
        dateTasksFragmentAdapter?.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        lifecycleScope.launchWhenCreated {
            delay(50)
            dateWheelRV.scrollToPosition(beginDaysCount + 1)
        }

        var currentPosition = beginDaysCount-1

        dateTasksFragmentAdapter?.pickerDateListener = object : PickerAdapter.PickerDateListener{
            override fun onItemClick(position: Int, dateTask: DateTask) {
                pickerLayoutManager.enableScrolling()
                Log.d("MyLog","currentPosition = $currentPosition, position = $position")
                var scrollPosition = 0
                scrollPosition = if (position > currentPosition) position+2
                else if (position < currentPosition) position-2
                else currentPosition
                    lifecycleScope.launchWhenCreated {
                        delay(50)
                        dateWheelRV.scrollToPosition(scrollPosition)
                        currentPosition = position
                    }
                dayKidDetailTaskFragmentViewModel.transferDateValue("${dateTask.month} ${dateTask.day} ${dateTask.year}")
                pickerLayoutManager.disableScrolling()

            }
        }
        return pickerLayoutManager
    }

    private fun getDateList(daysBeforeCurrent: Int, daysAfterCurrent: Int): List<DateTask> {
        val formatDateDay = SimpleDateFormat("dd", Locale.getDefault())
        val formatDateMonth = SimpleDateFormat("MMMM", Locale.getDefault())
        val formatDateYear = SimpleDateFormat("yyyy")
        val todayDate = Calendar.getInstance()
//        Log.d("MyLog","today day = $todayDate")
//        todayDate.add(Calendar.DATE, -daysAfterCurrent)
        todayDate.add(Calendar.DATE, -daysBeforeCurrent)

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

//    private fun getUpcomingCountTask(tasksList: List<CreatedTasksItem>): Int {
//        var count = 0
//        for (task in tasksList) {
//            if (task.taskStatus == "Upcoming") count++
//        }
//        return count
//    }

    private fun getUpcomingCountTask(tasksList: List<CreatedTasksItem>, checkedDate: String): Int {
        var count = 0
        for (task in tasksList) {
            if (task.chores != null){
                for (chore in task.chores){
                    if (chore?.date == checkedDate){
                        if (chore.status != "done" ){
                            count++
                        }
                    }
                }
            }
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

    private fun getKidBottomNavigationBar(bottomNavigation: BottomNavigationView) {
        bottomNavigation.setOnItemSelectedListener { itemMenu ->
            when (itemMenu.itemId) {
                R.id.kid_tasks -> {
                    findNavController().navigate(R.id.kidDayTasksFragment)
                    true
                }
                R.id.kid_profile -> {
                    findNavController().navigate(R.id.kidProfileFragment)
                    true
                }
                R.id.kid_goals -> {
                    findNavController().navigate(R.id.kidGoalsFragment)
                    true
                }
                else -> false
            }

        }
    }
}