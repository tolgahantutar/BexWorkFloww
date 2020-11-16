package com.tolgahantutar.bexworkfloww.data.network.responses.getresponses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.getcontact.GetContactValue
import com.tolgahantutar.bexworkfloww.data.models.getcontact.GrouperResult

@JsonClass(generateAdapter = true)
data class GetContactResponse(
    @Json(name = "Value")
    val getContactValue: List<GetContactValue>,
    @Json(name = "TotalRowsCount")
    val totalRowsCount : Int,
    @Json(name = "CurrentPage")
    val currentPage : Int,
    @Json(name = "GrouperResults")
    val grouperResult: List<GrouperResult>?,
    @Json(name = "Result")
    val result : Boolean,
    @Json(name = "Description")
    val description : String?,
    @Json(name = "Code")
    val code :String
)