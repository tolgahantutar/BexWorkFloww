package com.tolgahantutar.bexworkfloww.data.network.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.GetDomainModel

@JsonClass(generateAdapter = true)
data class GetDomainResponse(
    @Json(name = "Value")
    var getDomainModel: GetDomainModel,
    @Json(name = "Result")
    var Result: Boolean,
    @Json(name = "Description")
    var Description: String?,
    @Json(name = "Code")
    var Code: String
)