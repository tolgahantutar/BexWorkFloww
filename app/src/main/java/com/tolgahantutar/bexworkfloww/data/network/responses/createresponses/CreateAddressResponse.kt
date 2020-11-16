package com.tolgahantutar.bexworkfloww.data.network.responses.createresponses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.createinfo.createaddress.CreateAddressValue

@JsonClass(generateAdapter = true)
data class CreateAddressResponse(
    @Json(name = "Value")
    val createAddressValue: CreateAddressValue,
    @Json(name = "Result")
    val result: Boolean,
    @Json(name = "Description")
    val description: String,
    @Json(name = "Code")
    val code: String
)