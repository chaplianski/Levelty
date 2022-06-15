package com.example.levelty.presenter.dialogs

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.levelty.R
import com.example.levelty.presenter.adapters.DatePickerLayoutManager
import com.example.levelty.presenter.adapters.MonthPickerAdapter
import com.example.levelty.presenter.adapters.YearPickerAdapter
import com.example.levelty.presenter.viewmodels.StartTimeChooseFragmentViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*


class StartTimeChooseFragment : BottomSheetDialogFragment() {

    val startTimeChooseFragmentViewModel: StartTimeChooseFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start_time_choose, container, false)
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onStart() {
        super.onStart()

        dialog.let {
            val bottomSheet = it?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val behavior = BottomSheetBehavior.from(bottomSheet)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.isDraggable = false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cal = Calendar.getInstance()
        var currentHour = cal.get(Calendar.HOUR_OF_DAY)
        val currentMinute = cal.get(Calendar.MINUTE)
        val currentAmPm = cal.get(Calendar.AM_PM)
        val saveButton: Button = view.findViewById(R.id.bt_fragment_start_time_choose_save)
        val closeButton: ImageView = view.findViewById(R.id.iv_fragment_start_time_choose_close)

        if(currentHour > 12) currentHour -= 12
        var hourToValue = String.format("%02d", currentHour)
        var minuteToValue = String.format("%02d", currentMinute)
        var amPmToValue = if (currentAmPm.toString().toInt() == 0) {"AM"} else "PM"

        // ****** Hour picker ******

        var currentHourPosition = 0
        var hourValue = mutableListOf<Int>()
        for (i in 7..12){
            hourValue.add(i)
        }
        for ((i, h) in (1..12).withIndex()) {
            hourValue.add(i+1)
            if (h == currentHour) currentHourPosition = i+4
            if (currentAmPm == 1) {
                if (h == currentHour - 12) currentHourPosition = i+4
            }
        }

        for (i in 1..6){
            hourValue.add(i)
        }

        Log.d("MyLog", "list hour size = ${hourValue.size}, list= $hourValue, current position = $currentHourPosition")

        val hourRV: RecyclerView = view.findViewById(R.id.rv_fragment_start_time_choose_hour)
        val hourPickerLayoutManager =
            DatePickerLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val hourPickerAdapter = context?.let { YearPickerAdapter(it, hourValue.toList(), hourRV) }
        val hourSnapHelper: SnapHelper = LinearSnapHelper()

        hourRV.setLayoutManager(hourPickerLayoutManager)

        hourRV.layoutManager?.scrollToPosition(currentHourPosition)
        hourRV.adapter = hourPickerAdapter
        hourSnapHelper.attachToRecyclerView(hourRV)

        hourRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val layoutManager = recyclerView.layoutManager
                    //     if (layoutManager instanceof LinearLayoutManager) {
                    val firstVisibleItemPosition =
                        (layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
                    val lastVisibleItemPosition =
                        (layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()
                    if (lastVisibleItemPosition == 23){
                        layoutManager?.scrollToPosition(5)
                    }
                    if (firstVisibleItemPosition == 0){
                        layoutManager?.scrollToPosition(16)
                    }

                    Log.d("MyLog", "lastVisibleItemPosition = $firstVisibleItemPosition dy = $lastVisibleItemPosition" )

                }
            }
        })

        // ****** Minute picker ******

        var currentMinutePosition = 0
        val minuteValue = mutableListOf<Int>()
        for (i in 54..59){
            minuteValue.add(i)
        }
        for ((i, m) in (0..59).withIndex()) {
            minuteValue.add(m)
            if (m == currentMinute) currentMinutePosition = i+4
        }
        for(i in 0..5){
            minuteValue.add(i)
        }

        Log.d("MyLog", "list size = ${minuteValue.size}")

        val minuteRV: RecyclerView = view.findViewById(R.id.rv_fragment_start_time_choose_minute)
        val minutePickerLayoutManager =
            DatePickerLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val minutePickerAdapter =
            context?.let { YearPickerAdapter(it, minuteValue.toList(), minuteRV) }
        val minuteSnapHelper: SnapHelper = LinearSnapHelper()

        minuteRV.setLayoutManager(minutePickerLayoutManager)
        minuteRV.layoutManager?.scrollToPosition(currentMinutePosition)
        minuteRV.adapter = minutePickerAdapter
        minuteSnapHelper.attachToRecyclerView(minuteRV)


        minuteRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                   val layoutManager = recyclerView.layoutManager
               //     if (layoutManager instanceof LinearLayoutManager) {
                        val firstVisibleItemPosition =
                            (layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
                        val lastVisibleItemPosition =
                            (layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()
                    if (lastVisibleItemPosition == 71){
                        layoutManager?.scrollToPosition(6)
                        Log.d("MyLog", "last")
                    }
                    if (firstVisibleItemPosition == 0){
                        layoutManager?.scrollToPosition(64)
                        Log.d("MyLog", "first")
                    }

                    Log.d("MyLog", "lastVisibleItemPosition = $firstVisibleItemPosition dy = $lastVisibleItemPosition" )

                }
            }
        })

        // ****** Am-Pm Picker ******

        val amPmValue = mutableListOf<String>()
        amPmValue.add("")
        amPmValue.add("")
        amPmValue.add("AM")
        amPmValue.add("PM")
        amPmValue.add("")
        amPmValue.add("")

        val amPmRV: RecyclerView = view.findViewById(R.id.rv_fragment_start_time_choose_ampm)
        val amPmPickerLayoutManager =
            DatePickerLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val amPmPickerAdapter = context?.let { MonthPickerAdapter(it, amPmValue.toList(), hourRV) }
        val amPmSnapHelper: SnapHelper = LinearSnapHelper()

        amPmRV.setLayoutManager(amPmPickerLayoutManager)
        amPmRV.layoutManager?.scrollToPosition(cal.get(Calendar.AM_PM))
        amPmRV.adapter = amPmPickerAdapter
        amPmSnapHelper.attachToRecyclerView(amPmRV)

        Log.d("MyLog", "hour = $currentHour, minute = $currentMinute, am-pm = $currentAmPm")
        Log.d(
            "MyLog",
            "positionHour = $currentHourPosition, positionMinute = ${currentMinutePosition}, am-pm = ${
                cal.get(Calendar.AM_PM)
            }"
        )

        hourPickerLayoutManager.setOnScrollStopListener( object : DatePickerLayoutManager.onScrollStopDataListener{
            override fun selectedView(view: View?) {
                val hour = view?.findViewById<TextView>(R.id.tv_fragment_date_choose_year)
                startTimeChooseFragmentViewModel.transferHourValue(hour?.text.toString())
                Toast.makeText(
                    context,
                    "Selected hour ${hour?.text} ", Toast.LENGTH_SHORT
                ).show()
            }

        })

        minutePickerLayoutManager.setOnScrollStopListener( object : DatePickerLayoutManager.onScrollStopDataListener{
            override fun selectedView(view: View?) {
                val minute = view?.findViewById<TextView>(R.id.tv_fragment_date_choose_year)
                startTimeChooseFragmentViewModel.transferMinuteValue(minute?.text.toString())

                Toast.makeText(
                    context,
                    "Selected minute ${minute?.text} ", Toast.LENGTH_SHORT
                ).show()
            }

        })

        amPmPickerLayoutManager.setOnScrollStopListener( object : DatePickerLayoutManager.onScrollStopDataListener{
            override fun selectedView(view: View?) {
                val amPm = view?.findViewById<TextView>(R.id.tv_fragment_date_choose_month)
                startTimeChooseFragmentViewModel.transferAmPmValue(amPm?.text.toString())
                Toast.makeText(
                    context,
                    "Selected period ${amPm?.text} ", Toast.LENGTH_SHORT
                ).show()
            }

        })

        startTimeChooseFragmentViewModel.hourValue.observe(this.viewLifecycleOwner){
            hourToValue = it
        }

        startTimeChooseFragmentViewModel.minuteValue.observe(this.viewLifecycleOwner){
            minuteToValue = it
        }

        startTimeChooseFragmentViewModel.amPmValue.observe(this.viewLifecycleOwner){
            amPmToValue = it
        }

        saveButton.setOnClickListener {
            val startTimeItem = "$hourToValue : $minuteToValue $amPmToValue"
            val navController = findNavController()
            navController.previousBackStackEntry?.savedStateHandle?.set("start time", startTimeItem)
            dismiss()
        }

        closeButton.setOnClickListener {
            dismiss()
        }

    }



}