package com.example.levelty.presenter.ui.kid

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.levelty.R
import com.example.levelty.databinding.FragmentKidDayTasksBinding
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.DateTask
import com.example.levelty.domain.models.KidProcessedTask
import com.example.levelty.presenter.adapters.PickerAdapter
import com.example.levelty.presenter.adapters.PickerLayoutManager
import com.example.levelty.presenter.adapters.kid.KidDayTasksFragmentAdapter
import com.example.levelty.presenter.adapters.kid.KidDaysTasksListAdapter
import com.example.levelty.presenter.adapters.kid.TaskPickerLayoutManager
import com.example.levelty.presenter.dialogs.kid.KidNotificationSendDialog
import com.example.levelty.presenter.dialogs.kid.KidRedoTaskDialog
import com.example.levelty.presenter.factories.kid.KidDayTaskFragmentViewModelFactory
import com.example.levelty.presenter.utils.*
import com.example.levelty.presenter.viewmodels.kid.KidDayTaskFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class KidDayTasksFragment : Fragment() {

    @Inject
    lateinit var kidDayTaskFragmentViewModelFactory: KidDayTaskFragmentViewModelFactory
    val kidDayTaskFragmentViewModel: KidDayTaskFragmentViewModel by viewModels { kidDayTaskFragmentViewModelFactory }

    var _binding: FragmentKidDayTasksBinding? = null
    val binding get() = _binding!!

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

        val kidName = binding.tvFragmentDayKidDetailKidName
        val kidLevel = binding.tvFragmentDayKidDetailLevel
        val kidCoins = binding.tvFragmentDayKidDetailKidNumberCoins

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        kidName.text = sharedPref?.getString(CURRENT_KID_NAME, "")
        kidLevel.text = "Level ${sharedPref?.getInt(CURRENT_KID_LEVEL, 0)}"
        kidCoins.text = sharedPref?.getInt(CURRENT_KID_COINS, 0).toString()


        var checkedDate = getTodayShortDate()
        val tasksWheelRV = binding.layoutFragmentDayKidWheelTasks.rvKidTasksCardsWheel
        val progressWheelText =
            binding.layoutFragmentDayKidWheelTasks.tvKidTasksCardsWheelProgressText
        val progressWheelView =
            binding.layoutFragmentDayKidWheelTasks.pbKidTasksCardsWheelProgressView

        val datesWheelLayout = binding.datesWheelLayout.viewDateWheelLayout
        val tasksWheelLayout = binding.layoutFragmentDayKidWheelTasks.viewKidTasksCardsWheelLayout
        val tasksListLayout = binding.layoutFragmentDayKidListTasks.viewKidDayTasksListLayout
        val noTasksPastLayout = binding.layoutKidDayTasksNoTaskInPast.viewKidMessageNoTaskInPast
        val noTaskFutureLayout =
            binding.layoutKidDayTasksNoTaskInFuture.viewKidDialogDialogNoTaskInFuture
        val askTaskButton = binding.layoutKidDayTasksNoTaskInFuture.tvKidDialogNoTaskInFutureAsk
        val skipTaskLayout = binding.layoutKidSkipTaskDialog.viewKidSkipTaskDialogLayout
        val skipSendButton = binding.layoutKidSkipTaskDialog.btSkipTaskMessageSend
        val skipReasonText = binding.layoutKidSkipTaskDialog.etSkipTaskMessageText


        askTaskButton.setOnClickListener {
            showNotificationSendDialog()
        }


        skipSendButton.setOnClickListener {
            if (!skipReasonText.text.isEmpty()) {
                datesWheelLayout.visibility = View.VISIBLE
                tasksWheelLayout.visibility = View.VISIBLE
                skipTaskLayout.visibility = View.INVISIBLE  // TODO send data to server
            }
        }


        val bottomNavigation = binding.bottomAppBarKidDayTasks
//        getKidBottomNavigationBar(bottomNavigation)
        bottomNavigation.selectedItemId = R.id.kid_tasks
        getKidBottomNavigationBar(bottomNavigation)
        bottomNavigation.itemIconTintList = null

        kidDayTaskFragmentViewModel.getTaskList()
        kidDayTaskFragmentViewModel.taskList.observe(this.viewLifecycleOwner) { allTasks ->
//            Log.d("MyLog", "checkedDate = $checkedDate, allTasks = $allTasks")

            kidDayTaskFragmentViewModel.getDayTaskList(checkedDate, allTasks)

            kidDayTaskFragmentViewModel.currentDay.observe(this.viewLifecycleOwner) { day ->
//                Log.d("MyLog", "date = $day, taskS = ${allTasks.size}" )
                checkedDate = day
                kidDayTaskFragmentViewModel.getDayTaskList(day, allTasks)
            }
        }


//        //***** Skip message block *****
//        skipButton.setOnClickListener {
//            if (skipReasonText.text?.isBlank() == true) {
//                skipReasonField.error = "Please, enter reason"
//            } else {
//                // TODO add send data to server
//                skipReasonText.setText("")
//                kidDayTaskFragmentViewModel.getTaskList()
//            }
//        }
//        changeErrorStatus(skipReasonText, skipReasonField)


        // ***** Date Wheel ******

        val pickerLayoutManager = getWheelPickerLayoutManager(view)
        // *** Listen current date and sent to viewModel
        pickerLayoutManager.setOnScrollStopListener(object :
            PickerLayoutManager.ScrollStopListener {
            override fun selectedView(view: View?) {
                val day = view?.findViewById<TextView>(R.id.tv_date_item_number)
                val month = view?.findViewById<TextView>(R.id.tv_date_item_month)
                val year = view?.findViewById<TextView>(R.id.tv_date_item_year)
                kidDayTaskFragmentViewModel.transferDateValue("${year?.text}-${
                    String.format("%02d", getMonthNumber(
                        month?.text.toString())
                    )
                }-${day?.text}")
            }
        })

//        kidDayTaskFragmentViewModel.currentDay.observe(this.viewLifecycleOwner) { date ->
//
//            checkedDate = date
//            kidDayTaskFragmentViewModel.getTaskList()
//            Toast.makeText(
//                context,
//                "Selected date ${date}", Toast.LENGTH_SHORT
//            ).show()
//        }

        // ******  Tasks wheel  ********

//        val tasksRV: RecyclerView =
//            view.findViewById(R.id.rv_fragment_day_kid_detail_tasks_tasks_list)
        val taskPickerLayoutManager =
            TaskPickerLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        kidDayTaskFragmentViewModel.getTaskList()

        kidDayTaskFragmentViewModel.todayTasksList.observe(this.viewLifecycleOwner) { todayTasks ->
            Log.d("MyLog", "checked date = $checkedDate, today = ${getTodayShortDate()}, task size = ${todayTasks.size}, $todayTasks")

            datesWheelLayout.visibility = View.VISIBLE
            when (true) {
                (dateShortStringToTime(checkedDate) == getTodayDateMls() && todayTasks.isNotEmpty()) -> {
                    // *** Fill wheel cards tasks
                    noTaskFutureLayout.visibility = View.INVISIBLE
                    noTasksPastLayout.visibility = View.INVISIBLE
                    tasksListLayout.visibility = View.INVISIBLE
                    tasksWheelLayout.visibility = View.VISIBLE
                    skipTaskLayout.visibility = View.INVISIBLE

                    val kidDayTasksFragmentAdapter =
                        KidDayTasksFragmentAdapter(todayTasks, tasksWheelRV)
                    //         val dayKidDetailTaskFragmentTasksAdapter = DayKidDetailTaskFragmentTasksAdapter(it)
                    tasksWheelRV.onFlingListener = null
                    val tasksSnapHelper: SnapHelper = LinearSnapHelper()
                    tasksSnapHelper.attachToRecyclerView(tasksWheelRV)

                    if (todayTasks.size > 1) {
                        tasksWheelRV.layoutManager = taskPickerLayoutManager
                    }

                    tasksWheelRV.adapter = kidDayTasksFragmentAdapter

                    lifecycleScope.launchWhenCreated {
                        delay(100)
                        tasksWheelRV.scrollToPosition(0)
                    }

                    kidDayTasksFragmentAdapter.checkTaskElementListener =
                        object : KidDayTasksFragmentAdapter.CheckTaskElementListener {
                            override fun clickOnDoneButton(task: KidProcessedTask) {
                                findNavController().navigate(R.id.action_kidDayTasksFragment_to_kidSuccessCloseTaskDialog)
                            }

                            override fun clickOnSkipButton(task: KidProcessedTask) {
                                noTaskFutureLayout.visibility = View.INVISIBLE
                                datesWheelLayout.visibility = View.INVISIBLE
                                noTasksPastLayout.visibility = View.INVISIBLE
                                tasksListLayout.visibility = View.INVISIBLE
                                tasksWheelLayout.visibility = View.INVISIBLE
                                skipTaskLayout.visibility = View.VISIBLE
                            }

                            override fun clickOnCancelTaskButton(task: KidProcessedTask) {
                                Log.d("MyLog", "cancel button")  //TODO("Not yet implemented")
                            }

                            override fun clickOnRedoButton(task: KidProcessedTask) {
                                task.choreID?.let { showRedoTaskDialog(it) }
                            }

                        }
                    setupRedoTaskDialog()

                    //***** Set progress indicators *******

                    val allTaskCount = todayTasks.size
                    val completedTaskCount = allTaskCount - getUncompletedCountTask(todayTasks, checkedDate)
                    progressWheelText.text = "$completedTaskCount of $allTaskCount are completed"
                    progressWheelView.max = allTaskCount
                    progressWheelView.progress = completedTaskCount
                }

                (checkedDate > getTodayShortDate() && todayTasks.isEmpty()) -> {
                    noTaskFutureLayout.visibility = View.VISIBLE
                    noTasksPastLayout.visibility = View.INVISIBLE
                    tasksListLayout.visibility = View.INVISIBLE
                    tasksWheelLayout.visibility = View.INVISIBLE
                    skipTaskLayout.visibility = View.INVISIBLE
                }
                (checkedDate < getTodayShortDate() && todayTasks.isEmpty()) -> {
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
                    fetchListTaskLayout(todayTasks, view, checkedDate)
                }
            }
        }
    }

    private fun fetchListTaskLayout(
        tasks: List<KidProcessedTask>,
        view: View, checkedDate: String
    ) {
        val tasksListRV = binding.layoutFragmentDayKidListTasks.rvKidDayTasksList
//        Log.d("MyLog", "tasks = ${tasks}")
        val fragmentDayPersonalTasksAdapter = KidDaysTasksListAdapter(tasks)
        tasksListRV.adapter = fragmentDayPersonalTasksAdapter
        val bundle = Bundle()
        fragmentDayPersonalTasksAdapter.shortOnClickListener =
            object : KidDaysTasksListAdapter.ShortOnClickListener {
                override fun ShortClick(task: KidProcessedTask) {
//                    bundle.putParcelable("current task", task)
//                    findNavController().navigate(
//                        R.id.action_dayPersonalTasksFragment_to_dayPersonalTasksDialogFragment,
//                        bundle
//                    )
                }
            }
//
        //***** Set progress indicators *******
        val progressListText = binding.layoutFragmentDayKidListTasks.tvKidDayTasksListProgressText
        val progressListView = binding.layoutFragmentDayKidListTasks.pbKidDayTasksListProgressView
        val allTaskCount = tasks.size
        val completedTaskCount = allTaskCount - getUncompletedCountTask(tasks, checkedDate)
        progressListText.text = "$completedTaskCount of $allTaskCount are completed"
        progressListView.max = allTaskCount
        progressListView.progress = completedTaskCount
    }

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

        dateTasksFragmentAdapter?.registerAdapterDataObserver(object :
            RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                dateWheelRV.scrollToPosition(beginDaysCount - 1)
            }

//            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
//                dateWheelRV.smoothScrollToPosition(beginDaysCount+1)
//                pickerLayoutManager.disableScrolling()
//            }

        })
        dateTasksFragmentAdapter?.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        lifecycleScope.launchWhenCreated {
            delay(50)
            dateWheelRV.scrollToPosition(beginDaysCount + 1)
        }

        var currentPosition = beginDaysCount - 1

        dateTasksFragmentAdapter?.pickerDateListener = object : PickerAdapter.PickerDateListener {
            override fun onItemClick(position: Int, dateTask: DateTask) {
                pickerLayoutManager.enableScrolling()
                Log.d("MyLog", "currentPosition = $currentPosition, position = $position")
                var scrollPosition = 0
                scrollPosition = if (position > currentPosition) position + 2
                else if (position < currentPosition) position - 2
                else currentPosition
                lifecycleScope.launchWhenCreated {
                    delay(50)
                    dateWheelRV.scrollToPosition(scrollPosition)
                    currentPosition = position
                }
                kidDayTaskFragmentViewModel.transferDateValue("${dateTask.year}-${String.format("%02d", getMonthNumber(dateTask.month))}-${dateTask.day} ")
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

    private fun getUncompletedCountTask(
        tasksList: List<KidProcessedTask>,
        checkedDate: String
    ): Int {
        var count = 0
        for (task in tasksList) {
//            if (task.choreDate == checkedDate) {
                if (task.choreStatus != "done") {
                    count++
                }
//            }
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

     fun showRedoTaskDialog(currentChoreId: Int) {
        KidRedoTaskDialog.show(parentFragmentManager, currentChoreId)
    }

    fun showNotificationSendDialog() {
        KidNotificationSendDialog.show(parentFragmentManager)
    }

    private fun setupRedoTaskDialog() {
        KidRedoTaskDialog.setupListener(parentFragmentManager, this) { choreId ->
            kidDayTaskFragmentViewModel.updateChoreStatus(choreId)
        }
    }
}