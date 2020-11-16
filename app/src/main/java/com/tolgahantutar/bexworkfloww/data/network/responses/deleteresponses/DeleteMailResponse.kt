package com.tolgahantutar.bexworkfloww.data.network.responses.deleteresponses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.deleteinfo.deletemail.DeleteMailValue

@JsonClass(generateAdapter = true)
data class DeleteMailResponse(
    @Json(name = "Value")
    val deleteMailValue: DeleteMailValue,
    @Json(name = "Result")
    val result: Boolean,
    @Json(name = "Description")
    val description : String,
    @Json(name = "Code")
    val code: String
)