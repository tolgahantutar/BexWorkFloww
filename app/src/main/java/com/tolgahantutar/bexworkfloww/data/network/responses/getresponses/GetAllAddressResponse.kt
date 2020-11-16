package com.tolgahantutar.bexworkfloww.data.network.responses.getresponses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.getalladdress.GetAllAddressValue

@JsonClass(generateAdapter = true)
data class GetAllAddressResponse(
    @Json(name = "Value")
    val getAllAddressResponse: List<GetAllAddressValue>,
    @Json(name = "TotalRowsCount")
    val totalRowsCount: Int,
    @Json(name = "CurrentPage")
    val currentPage: Int,
    @Json(name = "GrouperResults")
    val grouperResults :String?,
    @Json(name = "Result")
    val result: Boolean,
    @Json(name = "Description")
    val description: String ?,
    @Json(name = "Code")
    val code : String
)