package com.example.levelty.presenter.utils

import java.text.SimpleDateFormat
import java.time.Month
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

fun getTodayShortDate(): String {
    val formatDateDay = SimpleDateFormat("dd", Locale.getDefault())
    val formatDateMonth = SimpleDateFormat("MM", Locale.getDefault())
    val formatDateYear = SimpleDateFormat("yyyy")
    val today = Calendar.getInstance()
    val dateInString =
        "${formatDateYear.format(today.timeInMillis)}-${formatDateMonth.format(today.timeInMillis)}-${formatDateDay.format(today.timeInMillis)}"
    return dateInString
}

fun getTodayDateMls(): Long? {
    val formatDateDay = SimpleDateFormat("dd", Locale.getDefault())
    val formatDateMonth = SimpleDateFormat("MMMM", Locale.getDefault())
    val formatDateYear = SimpleDateFormat("yyyy")
    val today = Calendar.getInstance()
    val dateInString =
        "${formatDateMonth.format(today.timeInMillis)} ${formatDateDay.format(today.timeInMillis)} ${
            formatDateYear.format(today.timeInMillis)
        }"
    return dateFullStringToTime(dateInString)
}

fun dateFullStringToTime(date: String): Long? {
    val format = SimpleDateFormat("MMMM dd yyyy")
    return format.parse(date)?.time

}

fun dateTimeToFullString(date: Long): String {
    val format = SimpleDateFormat("MMMM dd yyyy")
    return format.format(date)
}

fun dateShortStringToTime(date: String): Long {
    val format = SimpleDateFormat("yyyy-MM-dd")
    return format.parse(date)?.time!!.toLong()
}

fun dateTimeToShortString(date: Long): String {
    val format = SimpleDateFormat("yyyy-MM-dd")
    return format.format(date)
}

fun dayToMls(day: Int): Long {
    return (day*24*60*60*1000).toLong()
}

fun getMonthNumber(month: String): Int{
    return  Month.valueOf(month.uppercase(Locale.getDefault())).value
}