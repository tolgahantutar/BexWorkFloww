package com.tolgahantutar.bexworkfloww.data.network.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.updatephone.UpdatePhoneValue

@JsonClass(generateAdapter = true)
data class UpdatePhoneResponse(
    @Json(name = "Value")
    val updatePhoneValue: UpdatePhoneValue,
    @Json(name = "Result")
    val result: Boolean,
    @Json(name = "Description")
    val description: String,
    @Json(name = "Code")
    val code: String
)