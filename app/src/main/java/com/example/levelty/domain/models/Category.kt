package com.example.levelty.domain.models

import kotlinx.parcelize.Parcelize


data class Category(
    val image: Image? = null,
    val backgroundColor: String? = null,
    val id: Int? = null,
    val title: String? = null
)
