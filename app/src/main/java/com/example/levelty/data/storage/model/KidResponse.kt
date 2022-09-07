package com.example.levelty.data.storage.model

import com.squareup.moshi.Json

data class KidResponse(
    @Json(name="data")
    val data: String,
    @Json(name="kids")
    var listKids: List<ChildrenItemDTO>
)
