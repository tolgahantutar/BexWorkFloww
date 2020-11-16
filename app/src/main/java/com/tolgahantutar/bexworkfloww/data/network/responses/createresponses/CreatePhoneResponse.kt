package com.tolgahantutar.bexworkfloww.data.network.responses.createresponses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.createinfo.createphone.CreatePhoneValue
import com.tolgahantutar.bexworkfloww.data.models.updateinfo.updatephone.UpdatePhoneValue

@JsonClass(generateAdapter = true)
data class CreatePhoneResponse(
    @Json(name = "Value")
    val createPhoneValue: CreatePhoneValue,
    @Json(name = "Result")
    val result: Boolean,
    @Json(name = "Description")
    val description: String,
    @Json(name = "Code")
    val code: String
)