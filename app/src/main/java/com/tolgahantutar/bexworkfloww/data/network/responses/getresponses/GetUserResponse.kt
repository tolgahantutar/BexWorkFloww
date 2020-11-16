package com.tolgahantutar.bexworkfloww.data.network.responses.getresponses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.getuser.GetUserValue

@JsonClass(generateAdapter = true)
data class GetUserResponse(
    @Json(name = "Value")
    var getUserValue: GetUserValue,
    @Json(name = "Result")
    val result : Boolean,
    @Json(name = "Description")
    val description : String?,
    @Json(name = "Code")
    val code : String
)