package com.example.levelty.data.storage.model

import android.graphics.Bitmap

data class GoalDTO(
    val goalId: Long,
    val goalName: String,
    val goalReward: String,
    val isApproval: Boolean,
    val goalImage: Bitmap

)
