package com.tolgahantutar.bexworkfloww.data.models.getallcountries

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class CellValue(
    @Json(name = "Value")
    val value: String,
    @Json(name = "MemberName")
    val memberName: String,
    @Json(name = "IsGrouper")
    val isGrouper: Boolean
):Parcelable