package com.example.levelty.presenter.ui.parent

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.levelty.R
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.DateTask
import com.example.levelty.domain.models.Task
import com.example.levelty.presenter.adapters.FragmentDayPersonalTasksAdapter
import com.example.levelty.presenter.adapters.PickerAdapter
import com.example.levelty.presenter.adapters.PickerLayoutManager
import com.example.levelty.presenter.dialogs.DayPersonalTasksDialogFragment
import com.example.levelty.presenter.factories.parent.DayPersonalTasksFragmentViewModelFactory
import com.example.levelty.presenter.viewmodels.parent.DayPersonalTasksFragmentViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class DayPersonalTasksFragment : Fragment() {


    @Inject
    lateinit var dayPersonalTasksFragmentViewModelFactory: DayPersonalTasksFragmentViewModelFactory
    val dayPersonalTasksFragmentViewModel: DayPersonalTasksFragmentViewModel by viewModels { dayPersonalTasksFragmentViewModelFactory }

    var dayPersonalTaskRecyclerView: RecyclerView? = null


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
        return inflater.inflate(R.layout.fragment_day_personal_tasks, container, false )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

     //   val dataPicker: NumberPicker = view.findViewById(R.id.np_fragment_day_personal_tasks_numbers)
        val addNewTaskButton: FloatingActionButton = view.findViewById(R.id.fb_day_personal_tasks_fragment_add)
        val kidName = arguments?.getString("kid name")
        val currentDay = arguments?.getString("current date")
        val progressText = view.findViewById<TextView>(R.id.tv_fragment_day_personal_tasks_progress_text)
        val progressBar = view.findViewById<ProgressBar>(R.id.pb_fragment_day_personal_tasks_progress_view)

   //     val swipeRefresh: SwipeRefreshLayout = view.findViewById(R.id.swipe_fragment_day_personal_task)


        // ***** Fill data Days personal tasks

  //      dayPersonalTasksFragmentViewModel.getDayTasks()

        if (kidName != null) {
            if (currentDay != null) {
                dayPersonalTasksFragmentViewModel.getDayTasks(kidName, currentDay)
            }
        }

        dayPersonalTasksFragmentViewModel.dayTaskList.observe(this.viewLifecycleOwner){

            // ***** Task List ****
            val dayPersonalTaskRecyclerView: RecyclerView = view.findViewById(R.id.rv_fragment_day_personal_tasks_tasks_list)
            val fragmentDayPersonalTasksAdapter = FragmentDayPersonalTasksAdapter(it)
            dayPersonalTaskRecyclerView.adapter = fragmentDayPersonalTasksAdapter
            val bundle = Bundle()
            fragmentDayPersonalTasksAdapter.shortOnClickListener = object : FragmentDayPersonalTasksAdapter.ShortOnClickListener{
                override fun ShortClick(task: Task) {


                    bundle.putParcelable("current task", task)
                    val navController = Navigation.findNavController(view)
                    navController.navigate(R.id.action_dayPersonalTasksFragment_to_dayPersonalTasksDialogFragment, bundle)
                }




            }




            //***** Get progress indicators *******

            val allTaskCount = it.size
            val completedTaskCount = allTaskCount - getUpcomingCountTask(it)
            progressText.text = "$completedTaskCount of $allTaskCount are completed"
            progressBar.max = allTaskCount
            progressBar.progress = completedTaskCount
         }

        // ***** Go to NewTaskFragment *****
        addNewTaskButton.setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_dayPersonalTasksFragment_to_newTaskFragment)
        }

        // ***** Date Wheel ******

        val dateRV: RecyclerView = view.findViewById(R.id.rv_fragment_day_personal_task_date)
        val pickerLayoutManager = PickerLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val beginDaysCount = 365
        val formatDateDay = SimpleDateFormat("dd", Locale.getDefault())
        val formatDateMonth = SimpleDateFormat("MMMM", Locale.getDefault())
        val formatDateYear = SimpleDateFormat("yyyy")
        val todayDate = Calendar.getInstance()
        todayDate.add(Calendar.DATE, -beginDaysCount)

        val dateValues = mutableListOf<DateTask>()
        var counter = 0L
        for (day in 1..(beginDaysCount+377)){
            todayDate.add(Calendar.DATE, 1)
            dateValues.add(DateTask(
                counter, formatDateDay.format(todayDate.timeInMillis),
                formatDateMonth.format(todayDate.timeInMillis),
            formatDateYear.format(todayDate.timeInMillis)))
            counter++
        }

        val dateTasksFragmentAdapter = context?.let { PickerAdapter(it, dateValues.toList(), dateRV) }
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(dateRV)
        dateRV.setLayoutManager(pickerLayoutManager)
        dateRV.adapter = dateTasksFragmentAdapter
        lifecycleScope.launchWhenCreated {
            delay(10)
            dateRV.scrollToPosition(beginDaysCount+1)
        }

        pickerLayoutManager.setOnScrollStopListener( object : PickerLayoutManager.ScrollStopListener{
            override fun selectedView(view: View?) {
                val day = view?.findViewById<TextView>(R.id.tv_date_item_number)
                val month = view?.findViewById<TextView>(R.id.tv_date_item_month)
                val year = view?.findViewById<TextView>(R.id.tv_date_item_year)
                dayPersonalTasksFragmentViewModel.transferDateValue("${month?.text} ${day?.text} ${year?.text}")
            }
        })

        dayPersonalTasksFragmentViewModel.currentDay.observe(this.viewLifecycleOwner){
            if (kidName != null) {
                dayPersonalTasksFragmentViewModel.getDayTasks(kidName, it)
            }

            Log.d("MyLog", "kid = $kidName, date = $it")

//            Toast.makeText(
//                context,
//                "Selected date ${it}", Toast.LENGTH_SHORT
//            ).show()
        }
    }


    private fun getUpcomingCountTask(tasksList: List<Task>): Int {
        var count = 0
        for (task in tasksList){
            if (task.taskStatus == "Upcoming") count++
        }
        return count

    }


}