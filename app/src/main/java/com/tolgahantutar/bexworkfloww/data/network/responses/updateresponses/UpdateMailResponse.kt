package com.tolgahantutar.bexworkfloww.data.network.responses.updateresponses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.updateinfo.updatemail.UpdateMailValue

@JsonClass(generateAdapter = true)
data class UpdateMailResponse(
    @Json(name = "Value")
    val updateMailValue: UpdateMailValue,
    @Json(name = "Result")
    val result: Boolean,
    @Json(name = "Description")
    val description: String,
    @Json(name = "Code")
    val code : String
)