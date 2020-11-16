package com.tolgahantutar.bexworkfloww.data.network.responses.deleteresponses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.deleteinfo.deletephone.DeletePhoneValue

@JsonClass(generateAdapter = true)
data class DeletePhoneResponse(
    @Json(name = "Value")
    val deletePhoneValue: DeletePhoneValue,
    @Json(name = "Result")
    val result: Boolean,
    @Json(name = "Description")
    val description: String,
    @Json(name = "Code")
    val code: String
)