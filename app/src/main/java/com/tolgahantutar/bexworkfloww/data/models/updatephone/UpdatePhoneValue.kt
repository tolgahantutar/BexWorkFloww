package com.tolgahantutar.bexworkfloww.data.models.updatephone

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


@JsonClass (generateAdapter = true)
@Parcelize
data class UpdatePhoneValue(
    @Json(name = "ContactID")
    val contactID: Int,
    @Json(name = "Priority")
    val priority: Int,
    @Json(name = "Type")
    val type: String,
    @Json(name = "CountryCode")
    val countryCode: String,
    @Json(name = "Number")
    val number: String,
    @Json(name = "Extension")
    val extension: String,
    @Json(name = "Availability")
    val availability: String,
    @Json(name = "DisplayBody")
    val displayBody : String,
    @Json(name = "CleanBody")
    val cleanBody: String,
    @Json(name = "IsVerified")
    val isVerified: Int,
    @Json(name = "VerificationCode")
    val verificationCode: String,
    @Json(name = "VerificationDate")
    val verificationDate: String,
    @Json(name = "BasicPhoneNumber")
    val basicPhoneNumber: String,
    @Json(name = "ID")
    val id : Int
):Parcelable