package com.tolgahantutar.bexworkfloww.data.models.getallcities

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class GetAllCityValue(
    @Json(name = "CountryID")
    val countryID: Int,
    @Json(name = "Name")
    val name : String,
    @Json(name = "ID")
    val id : Int
):Parcelable