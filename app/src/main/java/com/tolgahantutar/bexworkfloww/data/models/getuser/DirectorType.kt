package com.tolgahantutar.bexworkfloww.data.models.getuser

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DirectorType(
    @Json(name = "OrganizationID")
    val organizationId : Int,
    @Json(name = "Code")
    val code : String,
    @Json(name = "Name")
    val name : String,
    @Json(name="ID")
    val id: Int
)