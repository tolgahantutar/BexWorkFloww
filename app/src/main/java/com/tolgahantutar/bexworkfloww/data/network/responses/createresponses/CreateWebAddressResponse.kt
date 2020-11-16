package com.tolgahantutar.bexworkfloww.data.network.responses.createresponses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.createinfo.createwebaddress.CreateWebAddressValue
import com.tolgahantutar.bexworkfloww.data.models.updateinfo.updatewebaddress.UpdateWebAddressValue

@JsonClass(generateAdapter = true)
data class CreateWebAddressResponse(
    @Json(name = "Value")
    val createWebAddressValue: CreateWebAddressValue,
    @Json(name = "Result")
    val result : Boolean,
    @Json(name = "Description")
    val description : String,
    @Json(name = "Code")
    val code : String
)