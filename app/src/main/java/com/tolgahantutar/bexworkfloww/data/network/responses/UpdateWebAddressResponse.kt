package com.tolgahantutar.bexworkfloww.data.network.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.updateinfo.updatewebaddress.UpdateWebAddressValue

@JsonClass(generateAdapter = true)
data class UpdateWebAddressResponse(
    @Json(name = "Value")
    val updateWebAddressValue: UpdateWebAddressValue,
    @Json(name = "Result")
    val result : Boolean,
    @Json(name = "Description")
    val description : String,
    @Json(name = "Code")
    val code : String
)