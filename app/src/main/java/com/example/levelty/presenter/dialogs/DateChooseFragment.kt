package com.example.levelty.presenter.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.*
import com.example.levelty.R
import com.example.levelty.presenter.adapters.DatePickerLayoutManager
import com.example.levelty.presenter.adapters.MonthPickerAdapter
import com.example.levelty.presenter.adapters.YearPickerAdapter
import com.example.levelty.presenter.helpers.CircularScrollListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*


class DateChooseFragment : BottomSheetDialogFragment() {

//    private lateinit var datePickerView: DatePickerView
//
//    override val circularCheckBox: CheckBox
//        get() = findViewById(R.id.circular_check_box)
//    override val selectedItemTextView: TextView
//        get() = findViewById(R.id.selected_text_view)
//    override val vibrationFeedbackCheckBox: CheckBox
//        get() = findViewById(R.id.vibration_feedback_check_box)
//
//    private var formatter = SimpleDateFormat("yyyy-MM-dd")
//    private val calendar = Calendar.getInstance()
//
//    private lateinit var maxDateTextField: TextInputEditText
//    private lateinit var minDateTextField: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_date_choose, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val yearRV: RecyclerView = view.findViewById(R.id.rv_fragment_date_choose_year)
        val yearPickerLayoutManager = DatePickerLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val yearsList = mutableListOf<Int>()
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)

        for (i in (currentYear-20)..(currentYear+500)){
            yearsList.add(i)
        }


        val dateTasksFragmentAdapter = context?.let { YearPickerAdapter(it, yearsList.toList(), yearRV) }
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(yearRV)
        yearRV.setLayoutManager(yearPickerLayoutManager)
        yearRV.layoutManager?.scrollToPosition(18)
        yearRV.adapter = dateTasksFragmentAdapter


        val monthRV: RecyclerView = view.findViewById(R.id.rv_fragment_date_choose_month)
        val monthList = mutableListOf<String>()
        val monthes = Calendar.getInstance()
        val monthValues = mutableListOf<String>()
        val formatDateMonth = SimpleDateFormat("MMMM", Locale.getDefault())
        monthes.add(Calendar.MONTH, 1)


        for (i in 0..11){
            val month: String = DateFormatSymbols().getMonths().get(i)
            monthValues.add(month)
        }

        val monthPickerLayoutManager = DatePickerLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val monthPickerAdapter = context?.let { MonthPickerAdapter(it, monthValues.toList(), monthRV) }
        val monthSnapHelper: SnapHelper = LinearSnapHelper()

        monthRV.setLayoutManager(monthPickerLayoutManager)

  //      RecyclerView.LayoutManager lm = new GridLayoutManager(...): // or whatever layout manager you need
        val smoothScroller:RecyclerView.SmoothScroller  = CenterSmoothScroller(context);
        smoothScroller.setTargetPosition(Calendar.MONTH+1);
        monthPickerLayoutManager.startSmoothScroll(smoothScroller);

 //       monthRV.layoutManager?.scrollToPosition(Calendar.MONTH+1)
        monthRV.adapter = monthPickerAdapter
        monthSnapHelper.attachToRecyclerView(monthRV)

        monthRV.addOnScrollListener(CircularScrollListener(monthValues.size))

        val dayValue = mutableListOf<Int>()
        for (i in 1..31){
            dayValue.add(i)
        }

        val dayRV: RecyclerView = view.findViewById(R.id.rv_fragment_date_choose_day)
        val dayPickerLayoutManager = DatePickerLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val dayPickerAdapter = context?.let { YearPickerAdapter(it, dayValue.toList(), dayRV) }
        val daySnapHelper: SnapHelper = LinearSnapHelper()

        dayRV.setLayoutManager(dayPickerLayoutManager)
        dayRV.layoutManager?.scrollToPosition(Calendar.DAY_OF_MONTH-1)
        dayRV.adapter = dayPickerAdapter
        daySnapHelper.attachToRecyclerView(dayRV)

        var yearChoose = ""
        var monthChoose = ""
        var dayChoose = 0


        yearPickerLayoutManager.setOnScrollStopListener( object : DatePickerLayoutManager.onScrollStopDataListener{
            override fun selectedView(view: View?) {
                val year = view?.findViewById<TextView>(R.id.tv_fragment_date_choose_year)
                yearChoose = year?.text.toString()
                Toast.makeText(
                    context,
                    "Selected year ${yearChoose} ", Toast.LENGTH_SHORT
                ).show()
            }

        })

        monthPickerLayoutManager.setOnScrollStopListener( object : DatePickerLayoutManager.onScrollStopDataListener{
            override fun selectedView(view: View?) {
                val month = view?.findViewById<TextView>(R.id.tv_fragment_date_choose_month)
                Toast.makeText(
                    context,
                    "Selected month ${month?.text} ", Toast.LENGTH_SHORT
                ).show()
            }

        })

        dayPickerLayoutManager.setOnScrollStopListener( object : DatePickerLayoutManager.onScrollStopDataListener{
            override fun selectedView(view: View?) {
                val day = view?.findViewById<TextView>(R.id.tv_fragment_date_choose_year)
                Toast.makeText(
                    context,
                    "Selected day ${day?.text} ", Toast.LENGTH_SHORT
                ).show()
            }

        })



    }
    class CenterSmoothScroller(context: Context?) : LinearSmoothScroller(context) {
        override fun calculateDtToFit(
            viewStart: Int,
            viewEnd: Int,
            boxStart: Int,
            boxEnd: Int,
            snapPreference: Int
        ): Int {
            return boxStart + (boxEnd - boxStart) / 2 - (viewStart + (viewEnd - viewStart) / 2)
        }
    }



}