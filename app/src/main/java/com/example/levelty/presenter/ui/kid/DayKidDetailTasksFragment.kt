package com.example.levelty.presenter.ui.kid

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.levelty.R
import com.example.levelty.di.DaggerAppComponent
import com.example.levelty.domain.models.DateTask
import com.example.levelty.presenter.adapters.PickerAdapter
import com.example.levelty.presenter.adapters.PickerLayoutManager
import com.example.levelty.presenter.adapters.kid.DayKidDetailTaskFragmentTasksAdapter
import com.example.levelty.presenter.adapters.kid.TaskPickerLayoutManager
import com.example.levelty.presenter.factories.kid.DayKidDetailTaskFragmentViewModelFactory
import com.example.levelty.presenter.viewmodels.kid.DayKidDetailTaskFragmentViewModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class DayKidDetailTasksFragment : Fragment() {

    @Inject
    lateinit var dayKidDetailTaskFragmentViewModelFactory: DayKidDetailTaskFragmentViewModelFactory
    val dayKidDetailTaskFragmentViewModel: DayKidDetailTaskFragmentViewModel by viewModels { dayKidDetailTaskFragmentViewModelFactory }

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_day_kid_detail_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ***** Date Wheel ******

        val dateRV: RecyclerView = view.findViewById(R.id.rv_fragment_day_kid_detail_task_date)
        val pickerLayoutManager = PickerLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val beginDaysCount = 4
        val formatDateDay = SimpleDateFormat("dd", Locale.getDefault())
        val formatDateMonth = SimpleDateFormat("MMMM", Locale.getDefault())
        val todayDate = Calendar.getInstance()
        todayDate.add(Calendar.DATE, -beginDaysCount)

        val dateValues = mutableListOf<DateTask>()
        var counter = 0L
        for (day in 1..(beginDaysCount+377)){
            todayDate.add(Calendar.DATE, 1)
            dateValues.add(
                DateTask(
                counter, formatDateDay.format(todayDate.timeInMillis),
                formatDateMonth.format(todayDate.timeInMillis))
            )
            counter++
        }

        val dateTasksFragmentAdapter = context?.let { PickerAdapter(it, dateValues.toList(), dateRV) }
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(dateRV)
        dateRV.layoutManager = pickerLayoutManager
        dateRV.adapter = dateTasksFragmentAdapter
        lifecycleScope.launchWhenCreated {
            delay(51)
            dateRV.scrollToPosition(beginDaysCount+1)
        }

        pickerLayoutManager.setOnScrollStopListener( object : PickerLayoutManager.ScrollStopListener{
            override fun selectedView(view: View?) {
                val day = view?.findViewById<TextView>(R.id.tv_date_item_number)
                val month = view?.findViewById<TextView>(R.id.tv_date_item_month)
                dayKidDetailTaskFragmentViewModel.transferDateValue("${day?.text} ${month?.text}")
            }
        })

        dayKidDetailTaskFragmentViewModel.currentDay.observe(this.viewLifecycleOwner){
            Toast.makeText(
                context,
                "Selected date ${it}", Toast.LENGTH_SHORT
            ).show()
        }

        // ******  Tasks wheel  ********

        val tasksRV: RecyclerView = view.findViewById(R.id.rv_fragment_day_kid_detail_tasks_tasks_list)
        val taskPickerLayoutManager = TaskPickerLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        dayKidDetailTaskFragmentViewModel.getTaskList()

        dayKidDetailTaskFragmentViewModel.taskList.observe(this.viewLifecycleOwner){

            val dayKidDetailTaskFragmentTasksAdapter = DayKidDetailTaskFragmentTasksAdapter(it, tasksRV)
   //         val dayKidDetailTaskFragmentTasksAdapter = DayKidDetailTaskFragmentTasksAdapter(it)
            val tasksSnapHelper: SnapHelper = LinearSnapHelper()
            tasksSnapHelper.attachToRecyclerView(tasksRV)

            if (it.size > 1) {
                tasksRV.layoutManager = taskPickerLayoutManager
            }

            tasksRV.adapter = dayKidDetailTaskFragmentTasksAdapter

            lifecycleScope.launchWhenCreated {
                delay(500)
                tasksRV.scrollToPosition(0)
            }

        }
    }




}