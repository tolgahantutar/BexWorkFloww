package com.tolgahantutar.bexworkfloww.data.models.createinfo.createaddress

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Country(
    @Json(name = "AddressBookID")
    val addressBookID: Int,
    @Json(name = "Name")
    val name: String,
    @Json(name = "ID")
    val id: Int
):Parcelable