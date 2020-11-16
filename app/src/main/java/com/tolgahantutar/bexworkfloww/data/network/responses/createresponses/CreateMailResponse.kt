package com.tolgahantutar.bexworkfloww.data.network.responses.createresponses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.createinfo.createmail.CreateMailValue


@JsonClass(generateAdapter = true)
data class CreateMailResponse(
    @Json(name = "Value")
    val createMailValue: CreateMailValue,
    @Json(name = "Result")
    val result: Boolean,
    @Json(name = "Description")
    val description : String,
    @Json(name = "Code")
    val code: String
)