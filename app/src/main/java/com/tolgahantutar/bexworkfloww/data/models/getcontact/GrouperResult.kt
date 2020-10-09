package com.tolgahantutar.bexworkfloww.data.models.getcontact

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GrouperResult(
    @Json(name = "Count")
    val count : Int,
    @Json(name = "CellValues")
    val cellValues: List<CellValues>
)