package com.example.levelty.presenter.dialogs.parent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.levelty.R
import com.example.levelty.presenter.adapters.DatePickerLayoutManager
import com.example.levelty.presenter.adapters.StringWheelPickerAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ParentRepeatChoiceDialog : BottomSheetDialogFragment() {

 //   val startTimeChooseFragmentViewModel: StartTimeChooseFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_repeat_choice, container, false)
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

        var fraquency = 0
        var namePeriod = ""

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
        val repeatFraquencyPickerAdapter = context?.let { StringWheelPickerAdapter(it, repeatFrequencyValue.toList(), repeatFraquencyRV) }
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
        val periodPickerAdapter = context?.let { StringWheelPickerAdapter(it, periodValue.toList(), periodRV) }
        val periodSnapHelper: SnapHelper = LinearSnapHelper()

        periodRV.setLayoutManager(periodPickerLayoutManager)
    //    periodRV.layoutManager?.scrollToPosition(cal.get(Calendar.AM_PM))
        periodRV.adapter = periodPickerAdapter
        periodSnapHelper.attachToRecyclerView(periodRV)

//        Log.d("MyLog", "hour = $currentHour, minute = $currentMinute, am-pm = $currentAmPm")
//        Log.d(
//            "MyLog",
//            "positionHour = $currentHourPosition, positionMinute = ${currentMinutePosition}, am-pm = ${
//                cal.get(Calendar.AM_PM)
//            }"
//        )

        repeatFraquencyPickerLayoutManager.setOnScrollStopListener( object : DatePickerLayoutManager.onScrollStopDataListener{
            override fun selectedView(view: View?) {
                val repeatFrequency = view?.findViewById<TextView>(R.id.tv_fragment_date_choose_month)
     //           startTimeChooseFragmentViewModel.transferHourValue(hour?.text.toString())
                fraquency = repeatFrequency?.text.toString().toInt()
                Toast.makeText(
                    context,
                    "Selected frequency ${repeatFrequency?.text} ", Toast.LENGTH_SHORT
                ).show()
            }

        })


       periodPickerLayoutManager.setOnScrollStopListener( object : DatePickerLayoutManager.onScrollStopDataListener{
            override fun selectedView(view: View?) {
                val period = view?.findViewById<TextView>(R.id.tv_fragment_date_choose_month)
    //            startTimeChooseFragmentViewModel.transferAmPmValue(amPm?.text.toString())
                namePeriod = period?.text.toString()
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
              val repeatInterval = when(namePeriod){
                  "day" -> 1
                  "week" -> 7/fraquency.toInt()
                  "month" -> 30/fraquency.toInt()
                  "year" -> 365/fraquency.toInt()
                  else -> 1
              }

//            val customRepeat = "$hourToValue : $minuteToValue $amPmToValue"
            val navController = findNavController()
            navController.previousBackStackEntry?.savedStateHandle?.set("repeat", repeatInterval)
            dismiss()
        }

        closeButton.setOnClickListener {
            dismiss()
        }

    }


}