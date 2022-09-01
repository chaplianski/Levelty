package com.example.levelty.presenter.utils

import java.text.SimpleDateFormat
import java.util.*

fun getTodayDate(): String {
    val formatDateDay = SimpleDateFormat("dd", Locale.getDefault())
    val formatDateMonth = SimpleDateFormat("MMMM", Locale.getDefault())
    val formatDateYear = SimpleDateFormat("yyyy")
    val today = Calendar.getInstance()
    val dateInString =
        "${formatDateMonth.format(today.timeInMillis)} ${formatDateDay.format(today.timeInMillis)} ${
            formatDateYear.format(today.timeInMillis)
        }"
    return dateInString
}

fun dateStringToTime(date: String): Long? {
    val format = SimpleDateFormat("MMMM dd yyyy")
    return format.parse(date)?.time

}

fun dateTimeToString(){

}