package com.example.levelty.domain.models

data class Goal(
    val goalId: Long,
    val goalName: String,
    val goalValue: Int,
    val isApproval: Boolean,
    val kidId: Long
)
