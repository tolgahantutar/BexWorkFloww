package com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact.defaultaddress

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class CountyModel(
    @Json(name = "CityID")
    var cityId : String,
    @Json(name = "Name")
    var name: String?,
    @Json(name = "ID")
    var id: String
):Parcelable