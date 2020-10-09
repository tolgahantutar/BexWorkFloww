package com.tolgahantutar.bexworkfloww.data.models.getuserbyid.director

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DirectorModel(
    @Json(name = "Name")
    var name: String,
    @Json(name = "ID")
    var id : String
)