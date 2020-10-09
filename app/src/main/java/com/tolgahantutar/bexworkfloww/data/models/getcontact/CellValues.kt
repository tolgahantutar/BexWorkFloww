package com.tolgahantutar.bexworkfloww.data.models.getcontact

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CellValues(
    @Json(name="Value")
    val value : String,
    @Json(name = "MemberName")
    val memberName : String,
    @Json(name = "IsGrouper")
    val isGrouper : Boolean
)