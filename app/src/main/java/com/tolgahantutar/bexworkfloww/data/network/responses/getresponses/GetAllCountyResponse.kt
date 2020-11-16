package com.tolgahantutar.bexworkfloww.data.network.responses.getresponses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.getallcounties.GetAllCountyValue
import com.tolgahantutar.bexworkfloww.data.models.getallcountries.GrouperResult

@JsonClass(generateAdapter = true)
data class GetAllCountyResponse(
    @Json(name = "Value")
    val getAllCountyValue: List<GetAllCountyValue>,
    @Json(name = "TotalRowsCount")
    val totalRowsCount: Int,
    @Json(name = "CurrentPage")
    val currentPage: Int,
    @Json(name = "GrouperResults")
    val grouperResult: List<GrouperResult>?,
    @Json(name = "Result")
    var result: Boolean,
    @Json(name = "Description")
    val description: String,
    @Json(name = "Code")
    val code: String
)