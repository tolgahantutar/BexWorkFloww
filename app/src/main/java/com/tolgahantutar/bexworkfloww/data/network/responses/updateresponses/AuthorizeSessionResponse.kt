package com.tolgahantutar.bexworkfloww.data.network.responses.updateresponses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.authorizesession.AuthorizeSessionModel

@JsonClass(generateAdapter = true)
data class AuthorizeSessionResponse (
    @Json(name="Value")
    val authorizeSessionModel: AuthorizeSessionModel?,
    @Json(name="Result")
    val Result : Boolean,
    @Json(name="Description")
    val Description: String,
    @Json(name="Code")
    val Code: String
)