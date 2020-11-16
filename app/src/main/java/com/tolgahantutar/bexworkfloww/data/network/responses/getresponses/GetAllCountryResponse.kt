package com.tolgahantutar.bexworkfloww.data.network.responses.getresponses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.getallcountries.GetAllCountryValue
import com.tolgahantutar.bexworkfloww.data.models.getallcountries.GrouperResult

@JsonClass(generateAdapter = true)
data class GetAllCountryResponse(
    @Json(name = "Value")
    val getAllCountryValue: List<GetAllCountryValue>?,
    @Json(name = "TotalRowsCount")
    val totalRowsCount: Int,
    @Json(name = "CurrentPage")
    val currentPage: Int,
    @Json(name = "GrouperResults")
    val grouperResults: List<GrouperResult>?,
    @Json(name = "Result")
    val result: Boolean,
    @Json(name = "Description")
    val description: String,
    @Json(name = "Code")
    val code: String
)