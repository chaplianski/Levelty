package com.example.levelty.presenter.ui.kid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.levelty.R
import com.example.levelty.databinding.FragmentDayPersonalTasksBinding
import com.example.levelty.databinding.FragmentKidAllTasksBinding
import com.example.levelty.domain.models.DateTask
import com.example.levelty.domain.models.Task
import com.example.levelty.presenter.adapters.FragmentDayPersonalTasksAdapter
import com.example.levelty.presenter.adapters.PickerAdapter
import com.example.levelty.presenter.adapters.PickerLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*


//class KidAllTasksFragment : Fragment() {
//
//
//    var _binding: FragmentKidAllTasksBinding? = null
//    val binding: FragmentKidAllTasksBinding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentKidAllTasksBinding.inflate(layoutInflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val addNewTaskButton: FloatingActionButton = binding.fbDayPersonalTasksFragmentAdd
//        val kidName = arguments?.getString("kid name")
//        val currentDay = arguments?.getString("current date")
//        var checkedDay = currentDay
//        val progressText = binding.tvFragmentDayPersonalTasksProgressText
//        val progressBar = binding.pbFragmentDayPersonalTasksProgressView
//        val futureTaskDialog = binding.parentDialogFutureTask.viewParentDialogSetTask
//        val setNewTaskFutureText = binding.parentDialogFutureTask.tvDialogParentSetTask
//        val pastTaskDialog = binding.parentDialogPastTask.parentDialogPastTask
//        val backButton = binding.ivFragmentDayPersonalBack
//
//
//        //     val swipeRefresh: SwipeRefreshLayout = view.findViewById(R.id.swipe_fragment_day_personal_task)
//
//        //**** Back to profile fragment
//
//        backButton.setOnClickListener {
//            findNavController().navigate(R.id.action_dayPersonalTasksFragment_to_profileFragment)
//        }
//
//
//        // ***** Fill data Days personal tasks
//
//        //      dayPersonalTasksFragmentViewModel.getDayTasks()
//
//        if (kidName != null) {
//            if (currentDay != null) {
//                dayPersonalTasksFragmentViewModel.getDayTasks(kidName, currentDay)
//            }
//        }
//
//        dayPersonalTasksFragmentViewModel.dayTaskList.observe(this.viewLifecycleOwner){
//
//            // ***** Task List *****
//            // *** Check taking and current dates and hide and show task list / messages about absent tasks
//            futureTaskDialog.visibility = View.INVISIBLE
//            pastTaskDialog.visibility = View.INVISIBLE
//
//            val format = SimpleDateFormat("MMMM dd yyyy")
//            if (it.isEmpty()){
//                progressText.visibility = View.INVISIBLE
//                progressBar.visibility = View.INVISIBLE
//                if (format.parse(currentDay).time <= format.parse(checkedDay).time) {
//                    futureTaskDialog.visibility = View.VISIBLE
//                }
//                if (format.parse(currentDay).time > format.parse(checkedDay).time){
//                    pastTaskDialog.visibility = View.VISIBLE
//                }
//            }else{
//                progressText.visibility = View.VISIBLE
//                progressBar.visibility = View.VISIBLE
//                futureTaskDialog.visibility = View.INVISIBLE
//                pastTaskDialog.visibility = View.INVISIBLE
//            }
//
//            // *** Fill list tasks according selected date
//            val dayPersonalTaskRecyclerView: RecyclerView = view.findViewById(R.id.rv_fragment_day_personal_tasks_tasks_list)
//            val fragmentDayPersonalTasksAdapter = FragmentDayPersonalTasksAdapter(it)
//            dayPersonalTaskRecyclerView.adapter = fragmentDayPersonalTasksAdapter
//            val bundle = Bundle()
//            fragmentDayPersonalTasksAdapter.shortOnClickListener = object : FragmentDayPersonalTasksAdapter.ShortOnClickListener{
//                override fun ShortClick(task: Task) {
//                    bundle.putParcelable("current task", task)
//                    val navController = Navigation.findNavController(view)
//                    navController.navigate(R.id.action_dayPersonalTasksFragment_to_dayPersonalTasksDialogFragment, bundle)
//                }
//            }
//
//            //***** Set progress indicators *******
//
//            val allTaskCount = it.size
//            val completedTaskCount = allTaskCount - getUpcomingCountTask(it)
//            progressText.text = "$completedTaskCount of $allTaskCount are completed"
//            progressBar.max = allTaskCount
//            progressBar.progress = completedTaskCount
//        }
//
//        // ***** Go to NewTaskFragment *****
//        addNewTaskButton.setOnClickListener {
//            val navController = Navigation.findNavController(view)
//            navController.navigate(R.id.action_dayPersonalTasksFragment_to_newTaskFragment)
//        }
//
//        // ***** Date Wheel ******
//
//        val pickerLayoutManager = pickerLayoutManager(view)
//
//        pickerLayoutManager.setOnScrollStopListener( object : PickerLayoutManager.ScrollStopListener{
//            override fun selectedView(view: View?) {
//                val day = view?.findViewById<TextView>(R.id.tv_date_item_number)
//                val month = view?.findViewById<TextView>(R.id.tv_date_item_month)
//                val year = view?.findViewById<TextView>(R.id.tv_date_item_year)
//                dayPersonalTasksFragmentViewModel.transferDateValue("${month?.text} ${day?.text} ${year?.text}")
//                checkedDay = "${month?.text} ${day?.text} ${year?.text}"
//            }
//        })
//
//        dayPersonalTasksFragmentViewModel.currentDay.observe(this.viewLifecycleOwner){
//            if (kidName != null) {
//                dayPersonalTasksFragmentViewModel.getDayTasks(kidName, it)
//            }
//        }
//    }
//
//    private fun pickerLayoutManager(view: View): PickerLayoutManager {
//        val dateRV: RecyclerView = view.findViewById(R.id.rv_fragment_day_personal_task_date)
//        val pickerLayoutManager =
//            PickerLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        val beginDaysCount = 365
//        val formatDateDay = SimpleDateFormat("dd", Locale.getDefault())
//        val formatDateMonth = SimpleDateFormat("MMMM", Locale.getDefault())
//        val formatDateYear = SimpleDateFormat("yyyy")
//        val todayDate = Calendar.getInstance()
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
//
//        val dateTasksFragmentAdapter =
//            context?.let { PickerAdapter(it, dateValues.toList(), dateRV) }
//        val snapHelper: SnapHelper = LinearSnapHelper()
//        snapHelper.attachToRecyclerView(dateRV)
//        dateRV.setLayoutManager(pickerLayoutManager)
//        dateRV.adapter = dateTasksFragmentAdapter
//        lifecycleScope.launchWhenCreated {
//            delay(10)
//            dateRV.scrollToPosition(beginDaysCount + 1)
//        }
//        return pickerLayoutManager
//    }
//
//
//    private fun getUpcomingCountTask(tasksList: List<Task>): Int {
//        var count = 0
//        for (task in tasksList){
//            if (task.taskStatus == "Upcoming") count++
//        }
//        return count
//    }
//
//    override fun onDestroy() {
//        _binding = null
//        super.onDestroy()
//    }
//    }
//}