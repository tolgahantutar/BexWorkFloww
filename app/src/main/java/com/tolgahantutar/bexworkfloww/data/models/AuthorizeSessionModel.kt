package com.tolgahantutar.bexworkfloww.data.models

import com.google.gson.annotations.SerializedName


data class AuthorizeSessionModel(
    @SerializedName("UserID")
    var UserID: Int,
    @SerializedName("DatePasswordChanged")
    var DatePasswordChanged: String,
    @SerializedName("ForceToChangePassword")
    var ForceToChangePassword: String,
    @SerializedName("LoginType")
    var LoginType: String,
    @SerializedName("UserName")
    var UserName: String,
    @SerializedName("IsActive")
    var IsActive: Boolean,
    @SerializedName("UserFirstName")
    var UserFirstName: String,
    @SerializedName("UserLastName")
    var UserLastName: String,
    @SerializedName("ID")
    var ID: Int
)
