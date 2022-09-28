package com.example.levelty.domain.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GoalsItem(
    val childId: Int? = null,
    var price: Int? = null,
    val description: String? = null,
    val createdAt: String? = null,
    val id: Int? = null,
    val title: String? = null,
    val status: String? = null
): Parcelable
