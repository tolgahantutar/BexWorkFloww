package com.tolgahantutar.bexworkfloww.data.network.responses.deleteresponses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.deleteinfo.deletewebaddress.DeleteWebAddressValue

@JsonClass(generateAdapter = true)
data class DeleteWebAddressResponse(
    @Json(name = "Value")
    val deleteWebAddressValue: DeleteWebAddressValue,
    @Json(name = "Result")
    val result: Boolean,
    @Json(name = "Description")
    val description: String,
    @Json(name = "Code")
    val code : Int
)