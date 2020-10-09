package com.tolgahantutar.bexworkfloww.data.models.getdomain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthorityModel(
    @Json(name = "SecurityManagerID")
    var SecurityManagerID: Int,
    @Json(name = "SessionTimeOut")
    var SessionTimeOut: Int,
    @Json(name = "DaysPasswordExpires")
    var DaysPasswordExpires: Int,
    @Json(name = "DaysRemindExpiry")
    var DaysRemindExpiry: Int,
    @Json(name = "MinimumPasswordLength")
    var MinimumPasswordLength: Int,
    @Json(name = "MinimumNumberCount")
    var MinimumNumberCount: Int,
    @Json(name = "MinimumLetterCount")
    var MinimumLetterCount: Int,
    @Json(name = "UpperOrLowerCase")
    var UpperOrLowerCase: Int,
    @Json(name = "RequiresActivation")
    var RequiresActivation: Int,
    @Json(name = "ActivationValidityTime")
    var ActivationValidityTime: Int,
    @Json(name = "UseSystemLogin")
    var UseSystemLogin: Int,
    @Json(name = "Name")
    var Name: String,
    @Json(name = "ID")
    var ID: Int
)