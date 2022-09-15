package com.example.levelty.presenter.ui.parent

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.levelty.R
import com.example.levelty.databinding.FragmentDayPersonalTasksBinding
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.ParentProcessedTask
import com.example.levelty.presenter.adapters.FragmentDayPersonalTasksAdapter
import com.example.levelty.presenter.adapters.PickerAdapter
import com.example.levelty.presenter.adapters.PickerLayoutManager
import com.example.levelty.presenter.factories.parent.ParentDayKidTasksViewModelFactory
import com.example.levelty.presenter.utils.*
import com.example.levelty.presenter.viewmodels.parent.ParentDayKidTasksViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject


class ParentDayKidTasksFragment : Fragment() {


    @Inject
    lateinit var parentDayKidTasksViewModelFactory: ParentDayKidTasksViewModelFactory
    val parentDayKidTasksViewModel: ParentDayKidTasksViewModel by viewModels { parentDayKidTasksViewModelFactory }

    var dayPersonalTaskRecyclerView: RecyclerView? = null

    var _binding: FragmentDayPersonalTasksBinding? = null
    val binding: FragmentDayPersonalTasksBinding get() = _binding!!

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .dayPersonalTasksFragmentInject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dayPersonalTaskRecyclerView =
            view?.findViewById(R.id.rv_fragment_day_personal_tasks_tasks_list)
        _binding = FragmentDayPersonalTasksBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //   val dataPicker: NumberPicker = view.findViewById(R.id.np_fragment_day_personal_tasks_numbers)
        val addNewTaskButton: FloatingActionButton = binding.fbDayPersonalTasksFragmentAdd
        val kidName = arguments?.getString("kid name")
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val kidID = sharedPref?.getInt(CURRENT_KID_ID, 0)
//        val currentDay = arguments?.getString("current date")
        val currentDay = getTodayShortDate()
        var checkedDay = currentDay
//        Log.d("MyLog", "checked day = $checkedDay")
//        var checkedDay = getTodayDate()

        val progressText = binding.tvFragmentDayPersonalTasksProgressText
        val progressBar = binding.pbFragmentDayPersonalTasksProgressView
        val futureTaskDialog = binding.parentDialogFutureTask.viewParentDialogSetTask
        val setNewTaskFutureText = binding.parentDialogFutureTask.tvDialogParentSetTask
        val pastTaskDialog = binding.parentDialogPastTask.parentDialogPastTask
        val backButton = binding.ivFragmentDayPersonalBack
        val bottomNavigation = binding.bottomAppBarParentDayKidTasksFragment
        val kidNameField = binding.tvFragmentDayPersonalTasksKidName

        bottomNavigation.selectedItemId = R.id.parent_profile
        //     val swipeRefresh: SwipeRefreshLayout = view.findViewById(R.id.swipe_fragment_day_personal_task)
        getParentBottomNavigationBar(bottomNavigation, view)
//        Log.d("MyLog", "name = $kidName")
        kidNameField.text = kidName.toString()


        //**** Back to profile fragment

        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_parentDayKidTasksFragment_to_parenProfileFragment)
        }

        setNewTaskFutureText.setOnClickListener {
            findNavController().navigate(R.id.action_parentDayKidTasksFragment_to_parentNewTaskFragment)
        }
        // ***** Fill data Days personal tasks

        //      dayPersonalTasksFragmentViewModel.getDayTasks()

//        if (kidName != null) {
//            if (currentDay != null) {
//                parentDayKidTasksViewModel.getDayTasks(CURRENT_KID_ID)
//            }
//        }

        parentDayKidTasksViewModel.getTasksList()

        parentDayKidTasksViewModel.allTaskList.observe(this.viewLifecycleOwner) { allTasks ->
//            Log.d("MyLog", "allTasks = $allTasks")
            if (kidID != null) {
                parentDayKidTasksViewModel.getDayTasks(allTasks, kidID, checkedDay)
            }

            parentDayKidTasksViewModel.currentDay.observe(this.viewLifecycleOwner)
            { checkedDay ->
                if (kidID != null) {
                    parentDayKidTasksViewModel.getDayTasks(allTasks, kidID, checkedDay)
                }
            }
        }

        parentDayKidTasksViewModel.dayTaskList.observe(this.viewLifecycleOwner) { dayTaskList ->
//            Log.d("MyLog", "dayTaskList = $dayTaskList")

            futureTaskDialog.visibility = View.INVISIBLE
            pastTaskDialog.visibility = View.INVISIBLE

            // ***** Task List *****
            // *** Check taking and current dates and hide and show task list / messages about absent tasks

//            val format = SimpleDateFormat("MMMM dd yyyy")
            if (dayTaskList.isEmpty()) {
                progressText.visibility = View.INVISIBLE
                progressBar.visibility = View.INVISIBLE
//                if (format.parse(currentDay).time <= format.parse(checkedDay).time) {
                if (dateShortStringToTime(currentDay) <= dateShortStringToTime(checkedDay)) {
                    futureTaskDialog.visibility = View.VISIBLE
                }
                if (dateShortStringToTime(currentDay) > dateShortStringToTime(checkedDay)) {
                    pastTaskDialog.visibility = View.VISIBLE
                }
            } else {
                progressText.visibility = View.VISIBLE
                progressBar.visibility = View.VISIBLE
                futureTaskDialog.visibility = View.INVISIBLE
                pastTaskDialog.visibility = View.INVISIBLE
            }

            // *** Fill list tasks according selected date
            val dayPersonalTaskRecyclerView: RecyclerView =
                view.findViewById(R.id.rv_fragment_day_personal_tasks_tasks_list)
            val fragmentDayPersonalTasksAdapter =
                FragmentDayPersonalTasksAdapter(dayTaskList, checkedDay)
            dayPersonalTaskRecyclerView.adapter = fragmentDayPersonalTasksAdapter

            fragmentDayPersonalTasksAdapter.shortOnClickListener =
                object : FragmentDayPersonalTasksAdapter.ShortOnClickListener {
                    override fun ShortClick(task: ParentProcessedTask) {
                      val bundle = Bundle()
                        task.id?.let { bundle.putInt(CURRENT_TASK_ID, it) }
                        bundle.putParcelable(CURRENT_TASK, task.mapToEditTask())
                        findNavController().navigate(
                            R.id.action_parentDayKidTasksFragment_to_parentDayKidChangeStatusTaskDialogFragment,
                            bundle
                        )
                    }
                }

            //***** Set progress indicators *******

            val allTaskCount = dayTaskList.size
            val completedTaskCount = allTaskCount - getUpcomingCountTask(dayTaskList, checkedDay)
            progressText.text = "$completedTaskCount of $allTaskCount are completed"
            progressBar.max = allTaskCount
            progressBar.progress = completedTaskCount

        }


        // ***** Go to NewTaskFragment *****
        addNewTaskButton.setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_parentDayKidTasksFragment_to_parentNewTaskFragment)
        }

        // ***** Date Wheel ******

        val pickerLayoutManager = pickerLayoutManager(view)

        pickerLayoutManager.setOnScrollStopListener(
            object : PickerLayoutManager.ScrollStopListener {
                override fun selectedView(view: View?) {
                    val day = view?.findViewById<TextView>(R.id.tv_date_item_number)
                    val month = view?.findViewById<TextView>(R.id.tv_date_item_month)
                    val year = view?.findViewById<TextView>(R.id.tv_date_item_year)
                    parentDayKidTasksViewModel.transferDateValue(
                        "${year?.text}-${
                            String.format("%02d",getMonthNumber(
                                month?.text.toString())
                            )
                        }-${day?.text}"
                    )
                    checkedDay =
                        "${year?.text}-${getMonthNumber(month?.text.toString())}-${day?.text}"
                }
            })

//    parentDayKidTasksViewModel.currentDay.observe(this.viewLifecycleOwner)
//    { checkedDay ->
//        if (kidID != null) {
//            parentDayKidTasksViewModel.getDayTasks(tasks, kidID, checkedDay)
//        }
//    }
    }


    private fun pickerLayoutManager(view: View): PickerLayoutManager {
        val dateRV: RecyclerView = binding.dateWheelParentDayKidTasks.rvCommonDateWheelDates
//        view.findViewById(R.id.rv_fragment_day_personal_task_date)
        val pickerLayoutManager =
            PickerLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        val beginDaysCount = 365
//        val formatDateDay = SimpleDateFormat("dd", Locale.getDefault())
//        val formatDateMonth = SimpleDateFormat("MMMM", Locale.getDefault())
//        val formatDateYear = SimpleDateFormat("yyyy")
//        val todayDate = Calendar.getInstance()
//        Log.d("MyLog", "today date = $todayDate")
//        todayDate.add(Calendar.DATE, -beginDaysCount)
//
//        val dateValues = mutableListOf<DateTask>()
//        var counter = 0L
//        for (day in 1..(beginDaysCount + 377)) {
//            todayDate.add(Calendar.DATE, 1)
//            dateValues.add(
//                DateTask(
//                    counter, formatDateDay.format(todayDate.timeInMillis),
//                    formatDateMonth.format(todayDate.timeInMillis),
//                    formatDateYear.format(todayDate.timeInMillis)
//                )
//            )
//            counter++
//        }

        val dateTasksFragmentAdapter =
            context?.let { PickerAdapter(it, getDateList(), dateRV) }
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(dateRV)
        dateRV.setLayoutManager(pickerLayoutManager)
        dateRV.adapter = dateTasksFragmentAdapter
        lifecycleScope.launchWhenCreated {
//        delay(10)   // TODO check on centre position
            pickerLayoutManager.scrollToPosition(DAYS_BEFORE_TODAY-3)
//            dateRV.scrollToPosition(DAYS_BEFORE_TODAY -1)
        }
        return pickerLayoutManager
    }

//    private fun getDateList(): List<DateTask> {
//        val formatDateDay = SimpleDateFormat("dd", Locale.getDefault())
//        val formatDateMonth = SimpleDateFormat("MMMM", Locale.getDefault())
//        val formatDateYear = SimpleDateFormat("yyyy")
//        val todayDate = Calendar.getInstance()
////        Log.d("MyLog","today day = $todayDate")
////        todayDate.add(Calendar.DATE, -daysAfterCurrent)
//        todayDate.add(Calendar.DATE, -DAYS_BEFORE_TODAY)
//
//        val dateValues = mutableListOf<DateTask>()
//        var counter = 0L
//        for (day in 1..(DAYS_BEFORE_TODAY + DAYS_AFTER_TODAY)) {
//            todayDate.add(Calendar.DATE, 1)
//            dateValues.add(
//                DateTask(
//                    counter, formatDateDay.format(todayDate.timeInMillis),
//                    formatDateMonth.format(todayDate.timeInMillis),
//                    formatDateYear.format(todayDate.timeInMillis)
//                )
//            )
//            counter++
//        }
//        return dateValues.toList()
//    }


    private fun getUpcomingCountTask(tasksList: List<ParentProcessedTask>, checkedDate: String): Int {
        var count = 0
        for (task in tasksList) {
            if (task.choreStatus != "done") count++
        }
        return count
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
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