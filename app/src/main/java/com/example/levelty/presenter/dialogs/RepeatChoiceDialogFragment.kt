package com.example.levelty.presenter.dialogs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
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
import com.example.levelty.presenter.viewmodels.parent.StartTimeChooseFragmentViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class RepeatChoiceDialogFragment : BottomSheetDialogFragment() {

 //   val startTimeChooseFragmentViewModel: StartTimeChooseFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repeat_choice_dialog, container, false)
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


        val saveButton: Button = view.findViewById(R.id.bt_repeat_choose_dialog_save)
        val closeButton: ImageView = view.findViewById(R.id.iv_repeat_choose_dialog_close)



        // ****** Repeat frequency ******
        val repeatFrequencyValue = mutableListOf<String>()
        repeatFrequencyValue.add("")
        repeatFrequencyValue.add("")
        for (i in 1..10){
            repeatFrequencyValue.add(i.toString())
        }
        repeatFrequencyValue.add("")
        repeatFrequencyValue.add("")

        val repeatFraquencyRV: RecyclerView = view.findViewById(R.id.rv_repeat_choose_dialog_frequency)
        val repeatFraquencyPickerLayoutManager =
            DatePickerLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val repeatFraquencyPickerAdapter = context?.let { MonthPickerAdapter(it, repeatFrequencyValue.toList(), repeatFraquencyRV) }
        val repeatFrequencyHelper: SnapHelper = LinearSnapHelper()

        repeatFraquencyRV.setLayoutManager(repeatFraquencyPickerLayoutManager)
        //    periodRV.layoutManager?.scrollToPosition(cal.get(Calendar.AM_PM))
        repeatFraquencyRV.adapter = repeatFraquencyPickerAdapter
        repeatFrequencyHelper.attachToRecyclerView(repeatFraquencyRV)


        // ****** Item period ******

        val periodValue = mutableListOf<String>()
        periodValue.add("")
        periodValue.add("")
        periodValue.add("day")
        periodValue.add("week")
        periodValue.add("month")
        periodValue.add("year")
        periodValue.add("")
        periodValue.add("")

        val periodRV: RecyclerView = view.findViewById(R.id.rv_repeat_choose_dialog_item)
        val periodPickerLayoutManager =
            DatePickerLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val periodPickerAdapter = context?.let { MonthPickerAdapter(it, periodValue.toList(), periodRV) }
        val amPmSnapHelper: SnapHelper = LinearSnapHelper()

        periodRV.setLayoutManager(periodPickerLayoutManager)
    //    periodRV.layoutManager?.scrollToPosition(cal.get(Calendar.AM_PM))
        periodRV.adapter = periodPickerAdapter
        amPmSnapHelper.attachToRecyclerView(periodRV)

//        Log.d("MyLog", "hour = $currentHour, minute = $currentMinute, am-pm = $currentAmPm")
//        Log.d(
//            "MyLog",
//            "positionHour = $currentHourPosition, positionMinute = ${currentMinutePosition}, am-pm = ${
//                cal.get(Calendar.AM_PM)
//            }"
//        )

        repeatFraquencyPickerLayoutManager.setOnScrollStopListener( object : DatePickerLayoutManager.onScrollStopDataListener{
            override fun selectedView(view: View?) {
                val repeatFraquency = view?.findViewById<TextView>(R.id.tv_fragment_date_choose_year)
     //           startTimeChooseFragmentViewModel.transferHourValue(hour?.text.toString())
                Toast.makeText(
                    context,
                    "Selected frequency ${repeatFraquency?.text} ", Toast.LENGTH_SHORT
                ).show()
            }

        })


       periodPickerLayoutManager.setOnScrollStopListener( object : DatePickerLayoutManager.onScrollStopDataListener{
            override fun selectedView(view: View?) {
                val period = view?.findViewById<TextView>(R.id.tv_fragment_date_choose_month)
    //            startTimeChooseFragmentViewModel.transferAmPmValue(amPm?.text.toString())
                Toast.makeText(
                    context,
                    "Selected period ${period?.text} ", Toast.LENGTH_SHORT
                ).show()
            }

        })

//        startTimeChooseFragmentViewModel.hourValue.observe(this.viewLifecycleOwner){
//            hourToValue = it
//        }
//
//        startTimeChooseFragmentViewModel.minuteValue.observe(this.viewLifecycleOwner){
//            minuteToValue = it
//        }
//
//        startTimeChooseFragmentViewModel.amPmValue.observe(this.viewLifecycleOwner){
//            amPmToValue = it
//        }

        saveButton.setOnClickListener {
//            val customRepeat = "$hourToValue : $minuteToValue $amPmToValue"
//            val navController = findNavController()
//            navController.previousBackStackEntry?.savedStateHandle?.set("start time", startTimeItem)
            dismiss()
        }

        closeButton.setOnClickListener {
            dismiss()
        }

    }


}