package com.tolgahantutar.bexworkfloww.data.models.getallcountries

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.getcontact.CellValues
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@JsonClass(generateAdapter = true)
@Parcelize
data class GrouperResult (
    @Json(name = "Count")
    val count: Int,
    @Json(name = "CellValues")
    val cellValues: @RawValue List<CellValues>?
):Parcelable