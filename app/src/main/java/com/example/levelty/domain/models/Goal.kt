package com.example.levelty.domain.models

import android.graphics.Bitmap

data class Goal(
    val goalId: Long,
    val goalName: String,
    val goalReward: String,
    val isApproval: Boolean,
    val goalImage: Bitmap
)
