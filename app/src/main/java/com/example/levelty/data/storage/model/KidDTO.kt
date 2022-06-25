package com.example.levelty.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kids")
data class KidDTO(
    @PrimaryKey(autoGenerate = true)
    val kidId: Long,
    val kidName: String,
//    val kidImage: Bitmap
)