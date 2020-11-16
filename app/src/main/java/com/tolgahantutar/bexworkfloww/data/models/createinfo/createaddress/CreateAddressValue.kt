package com.tolgahantutar.bexworkfloww.data.models.createinfo.createaddress

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class CreateAddressValue(
    @Json(name = "ContactID")
    val contactID: Int,
    @Json(name = "Country")
    val country: Country,
    @Json(name = "County")
    val county: County,
    @Json(name = "State")
    val state: State,
    @Json(name = "City")
    val city: City,
    @Json(name = "Body")
    val body: String,
    @Json(name = "ZipCode")
    val zipCode: Int,
    @Json(name = "Latitude")
    val latitude: Int,
    @Json(name = "Longitute")
    val longitute: Int,
    @Json(name = "Altitude")
    val altitude: Int,
    @Json(name = "Sensitivity")
    val sensitivity: Int,
    @Json(name = "DisplayBody")
    val displayBody: String?,
    @Json(name = "Priority")
    val priority: Int,
    @Json(name = "MapUrl")
    val mapUrl: String?,
    @Json(name = "ID")
    val id: Int
):Parcelable