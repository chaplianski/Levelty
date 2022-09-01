package com.example.levelty.data.storage.model

import com.example.levelty.domain.models.Image

data class CategoryDTO(
    val image: Image? = null,
    val backgroundColor: String? = null,
    val id: Int? = null,
    val title: String? = null
)
