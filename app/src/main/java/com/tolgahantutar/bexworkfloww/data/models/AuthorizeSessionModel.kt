package com.tolgahantutar.bexworkfloww.data.models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class AuthorizeSessionModel(
    @Json(name="UserID")
    var UserID: Int,
    @Json(name="DatePasswordChanged")
    var DatePasswordChanged: String,
    @Json(name="ForceToChangePassword")
    var ForceToChangePassword: String,
    @Json(name="LoginType")
    var LoginType: String,
    @Json(name="UserName")
    var UserName: String,
    @Json(name="IsActive")
    var IsActive: Boolean,
    @Json(name="UserFirstName")
    var UserFirstName: String,
    @Json(name="UserLastName")
    var UserLastName: String,
    @Json(name="ID")
    var ID: Int
)
