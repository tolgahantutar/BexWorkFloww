package com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DefaultPhoneModel(
    @Json(name = "ContactID")
    var contactId : String,
    @Json(name = "Priority")
    var priority :String,
    @Json(name = "Type")
    var type : String,
    @Json(name = "CountryCode")
    var countryCode : String,
    @Json(name = "Number")
    var number : String,
    @Json(name = "Extension")
    var extension : String?,
    @Json(name = "Availability")
    var availability : String?,
    @Json(name = "DisplayBody")
    var displayBody : String,
    @Json(name = "CleanBody")
    var cleanBody : String,
    @Json(name = "IsVerified")
    var isVerified : String,
    @Json(name = "VerificationCode")
    var verificationCode : String?,
    @Json(name = "VerificationDate")
    var verificationDate : String,
    @Json(name = "BasicPhoneNumber")
    var basicPhoneNumber : String,
    @Json(name = "ID")
    var id : String
)