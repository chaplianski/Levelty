package com.example.levelty.presenter.dialogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.levelty.R
import com.example.levelty.presenter.adapters.DatePickerLayoutManager
import com.example.levelty.presenter.adapters.IntegerWheelPickerAdapter
import com.example.levelty.presenter.adapters.StringWheelPickerAdapter
import com.example.levelty.presenter.utils.dateShortStringToTime
import com.example.levelty.presenter.viewmodels.parent.DateChooseFragmentViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.util.*


class ParentDateChooseDialog : BottomSheetDialogFragment() {

    val dateChooseFragmentViewModel: DateChooseFragmentViewModel by viewModels ()
    var currentDayPosition = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_date_choose, container, false)

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

        val checkedDate = arguments?.getString("checked date")
        var checkedDay = 0
        var checkedYear = 0
        var checkedMonth = ""
        if (checkedDate !=null){
            checkedDay = getCheckedDay(checkedDate)
            checkedYear = getCheckedYear(checkedDate)
            checkedMonth = getCheckedMonth(checkedDate)
        }

        val saveButton: Button = view.findViewById(R.id.bt_fragment_date_choose_save)
        val closeButton: ImageView = view.findViewById(R.id.iv_fragment_date_choose_close)

        val calendar = Calendar.getInstance(TimeZone.getDefault())
        var currentDayValue: Int = if (checkedDay == 0) {
            calendar[Calendar.DAY_OF_MONTH]
        } else checkedDay

        var currentYearValue = if (checkedYear == 0) {
            calendar[Calendar.YEAR]
        } else checkedYear
//        var currentDayValue = calendar[Calendar.DAY_OF_MONTH]
//        var currentYearValue = calendar[Calendar.YEAR]
        var currentMonthValue = if (checkedMonth == ""){
            DateFormatSymbols().months[calendar.get(Calendar.MONTH)]
        }else checkedMonth

        Log.d("MyLog", "month = $currentMonthValue, checked day = $checkedDay, checked month = $checkedMonth, checked year = $checkedYear")

        val yearRV: RecyclerView = view.findViewById(R.id.rv_fragment_date_choose_year)
        val yearPickerLayoutManager = DatePickerLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val yearsList = mutableListOf<Int>()
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)

        for (i in (currentYear-20)..(currentYear+500)){
            yearsList.add(i)
        }

        val dateTasksFragmentAdapter = context?.let { IntegerWheelPickerAdapter(it, yearsList.toList(), yearRV) }
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(yearRV)
        yearRV.setLayoutManager(yearPickerLayoutManager)
        yearRV.layoutManager?.scrollToPosition(18)
        yearRV.adapter = dateTasksFragmentAdapter

        // ****** Month Picker *****

        val monthRV: RecyclerView = view.findViewById(R.id.rv_fragment_date_choose_month)
//        val calendar = Calendar.getInstance(TimeZone.getDefault())
        val monthValues = mutableListOf<String>()
        calendar.add(Calendar.MONTH, 1)

        for (i in 7..11){
            val month: String = DateFormatSymbols().months[i]
            monthValues.add(month)
        }
        for (i in 0..11){
            val month: String = DateFormatSymbols().months[i]
            monthValues.add(month)
        }
        for (i in 0..5){
            val month: String = DateFormatSymbols().months[i]
            monthValues.add(month)
        }

        val monthPickerLayoutManager = DatePickerLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val stringWheelPickerAdapter = context?.let { StringWheelPickerAdapter(it, monthValues.toList(), monthRV) }
        val monthSnapHelper: SnapHelper = LinearSnapHelper()

//        Log.d("MyLog", "month = ${calendar.get(Calendar.MONTH)}")
        for (date in monthValues.withIndex()){
//            Log.d("MyLog", "month = ${date}")
        }

        monthRV.setLayoutManager(monthPickerLayoutManager)
        monthRV.layoutManager?.scrollToPosition(calendar.get(Calendar.MONTH)+2)
        monthRV.adapter = stringWheelPickerAdapter
        monthSnapHelper.attachToRecyclerView(monthRV)

        monthRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val layoutManager = recyclerView.layoutManager
                    val firstVisibleItemPosition =
                        (layoutManager as LinearLayoutManager?)?.findFirstVisibleItemPosition()
                    val lastVisibleItemPosition =
                        layoutManager?.findLastVisibleItemPosition()
                    if (lastVisibleItemPosition == 22){
                        layoutManager?.scrollToPosition(6)
                    }
                    if (firstVisibleItemPosition == 0){
                        layoutManager?.scrollToPosition(16)
                    }
                }
            }
        })

        // ***** Day picker  *******

        var currentDaysInMonth = YearMonth.now().lengthOfMonth()
        var dayValue = getDaysList(currentDaysInMonth)

        val dayRV: RecyclerView = view.findViewById(R.id.rv_fragment_date_choose_day)
        val dayPickerLayoutManager = DatePickerLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val dayPickerAdapter = context?.let { IntegerWheelPickerAdapter(it, dayValue.toList(), dayRV) }
        val daySnapHelper: SnapHelper = LinearSnapHelper()

        dayRV.setLayoutManager(dayPickerLayoutManager)
 //       dayRV.layoutManager?.scrollToPosition(Calendar.DAY_OF_MONTH + 9)
//        if (checkedDate != getTodayShortDate()){
//            if (checkedDay != null) {
//                dayRV.layoutManager?.scrollToPosition(checkedDay+1)
//            }
//        } else {
            dayRV.layoutManager?.scrollToPosition(currentDayValue?.plus(1) ?: 1)
//        }



        dayRV.adapter = dayPickerAdapter
        daySnapHelper.attachToRecyclerView(dayRV)

//        var currentDayValue = calendar[Calendar.DAY_OF_MONTH]
//        var currentYearValue = calendar[Calendar.YEAR]
//        var currentMonthValue = DateFormatSymbols().months[calendar.get(Calendar.MONTH)-1]

        dayRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val layoutManager = recyclerView.layoutManager
                    val firstVisibleItemPosition =
                        (layoutManager as LinearLayoutManager?)?.findFirstVisibleItemPosition()
                    val lastVisibleItemPosition =
                        (layoutManager as LinearLayoutManager?)?.findLastVisibleItemPosition()
                    if (lastVisibleItemPosition == currentDaysInMonth + 6){
                        layoutManager?.scrollToPosition(2)
                    }
                    if (firstVisibleItemPosition == 0){
                        layoutManager?.scrollToPosition(currentDaysInMonth + 3)
                    }
                }
            }
        })


        yearPickerLayoutManager.setOnScrollStopListener( object : DatePickerLayoutManager.onScrollStopDataListener{
            override fun selectedView(view: View?) {
               val year = view?.findViewById<TextView>(R.id.tv_fragment_date_choose_year)
               val yearChoose = year?.text.toString()
               dateChooseFragmentViewModel.transferYearValue(yearChoose)
//                Toast.makeText(
//                    context,
//                    "Selected year ${yearChoose} ", Toast.LENGTH_SHORT
//                ).show()
            }

        })

        monthPickerLayoutManager.setOnScrollStopListener( object : DatePickerLayoutManager.onScrollStopDataListener{
            override fun selectedView(view: View?) {
                val month = view?.findViewById<TextView>(R.id.tv_fragment_date_choose_month)
                val monthChoose = month?.text.toString()
                dateChooseFragmentViewModel.transferMonthValue(monthChoose)
//                Toast.makeText(
//                    context,
//                    "Selected month ${month?.text} ", Toast.LENGTH_SHORT
//                ).show()
            }

        })

        dayPickerLayoutManager.setOnScrollStopListener( object : DatePickerLayoutManager.onScrollStopDataListener{
            override fun selectedView(view: View?) {
                val day = view?.findViewById<TextView>(R.id.tv_fragment_date_choose_year)
                val dayChoose = day?.text.toString().toInt()
                dateChooseFragmentViewModel.transferDayValue(dayChoose)
//                Toast.makeText(
//                    context,
//                    "Selected day ${day?.text} ", Toast.LENGTH_SHORT
//                ).show()
            }

        })

        closeButton.setOnClickListener {
            dismiss()
        }

        dateChooseFragmentViewModel.yearValue.observe(this.viewLifecycleOwner){
            currentYearValue = it.toInt()
        }

        dateChooseFragmentViewModel.monthValue.observe(this.viewLifecycleOwner){

            if (currentDayPosition == 0) currentDayPosition = currentDayValue// - 6
//            Log.d("MyLog", "begin currentDayPosition = $currentDayPosition ")
 //           Log.d("MyLog", "currentDayPosition1 = $currentDayPosition")
            val monthNumber = getMonthNumber(it)
            val numberDaysInMonth = YearMonth.of(currentYearValue, monthNumber).lengthOfMonth()

            dayValue.clear()
            dayValue = getDaysList(numberDaysInMonth)
            dayPickerAdapter?.updateList(dayValue)
     //       currentDaysInMonth = numberDaysInMonth

  //          dayRV.layoutManager?.scrollToPosition()
//            Log.d("MyLog", "currentDayPosition = $currentDayPosition, numberDaysInMonth = $numberDaysInMonth, currentDaysInMonth = $currentDaysInMonth")
        //    if (currentDayPosition > 30)
            if (numberDaysInMonth < currentDaysInMonth && currentDayPosition > numberDaysInMonth) {
                val diffDays = currentDayPosition - numberDaysInMonth
//                Log.d("MyLog", "diffDays = $diffDays ")
                currentDayPosition -= diffDays
                dayRV.layoutManager?.scrollToPosition(currentDayPosition+1)
//                Log.d("MyLog", "currentDayPosition = $currentDayPosition ")
            }else{
                dayRV.layoutManager?.scrollToPosition(currentDayPosition+4)
            }

            currentDaysInMonth = numberDaysInMonth
            currentMonthValue = it
        }

        dateChooseFragmentViewModel.dayValue.observe(this.viewLifecycleOwner){
            currentDayValue = it
            currentDayPosition = currentDayValue
        }

        saveButton.setOnClickListener {
            val dateItem = "$currentMonthValue $currentDayValue $currentYearValue"
            val navController = findNavController()
            navController.previousBackStackEntry?.savedStateHandle?.set("date", dateItem)
            dismiss()
        }

    }

    private fun getDaysList(month: Int): MutableList<Int> {
        val dayValue = mutableListOf<Int>()

        for (i in (month-3)..month) {
            dayValue.add(i)
          }
        for (i in 1..month) {
            dayValue.add(i)
        }
        for (i in 1..3) {
            dayValue.add(i)
        }
 //       Log.d("MyLog", "month data = $dayValue")
        return dayValue
    }

    fun getMonthNumber(month: String): Int{
        var monthNumber = 0
        val monthList = mutableListOf<String>()
        for (i in 0..11){
            val currMonth: String = DateFormatSymbols().months[i]
            monthList.add(currMonth)
        }
 //       Log.d("MyLog", "List month = $monthList")
        for ((i, m) in monthList.withIndex()){
            if (m == month) monthNumber = i+1
//            Log.d("MyLog", "index month = $monthNumber")
        }
        return monthNumber
    }

    fun getCheckedDay(date: String): Int {
        val dateMls = dateShortStringToTime(date)
        val formatDateDay = SimpleDateFormat("dd", Locale.getDefault())
        return formatDateDay.format(dateMls).toInt()
    }

    fun getCheckedMonth(date: String): String{
        val dateMls = dateShortStringToTime(date)
        val formatDateMonth = SimpleDateFormat("MMMM", Locale.getDefault())
        return formatDateMonth.format(dateMls)
    }

    fun getCheckedYear(date: String): Int{
        val dateMls = dateShortStringToTime(date)
        val formatDateYear = SimpleDateFormat("yyyy", Locale.getDefault())
        return formatDateYear.format(dateMls).toInt()
    }

}

//class CenterLayoutManager1 : LinearLayoutManager {
//    constructor(context: Context?) : super(context) {}
//    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(
//        context,
//        orientation,
//        reverseLayout
//    ) {
//    }
//
//    constructor(
//        context: Context?,
//        attrs: AttributeSet?,
//        defStyleAttr: Int,
//        defStyleRes: Int
//    ) : super(context, attrs, defStyleAttr, defStyleRes) {
//    }
//
//    override fun smoothScrollToPosition(
//        recyclerView: RecyclerView,
//        state: RecyclerView.State,
//        position: Int
//    ) {
//        val smoothScroller: SmoothScroller = CenterSmoothScroller(recyclerView.context)
//        smoothScroller.targetPosition = position
//        startSmoothScroll(smoothScroller)
//    }
//
//    private class CenterSmoothScroller internal constructor(context: Context?) :
//        LinearSmoothScroller(context) {
//        override fun calculateDtToFit(
//            viewStart: Int,
//            viewEnd: Int,
//            boxStart: Int,
//            boxEnd: Int,
//            snapPreference: Int
//        ): Int {
//            return boxStart + (boxEnd - boxStart) / 2 - (viewStart + (viewEnd - viewStart) / 2)
//        }
//    }
//
//
//}