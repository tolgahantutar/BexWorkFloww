package com.tolgahantutar.bexworkfloww.data.models.createinfo.createwebaddress

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class CreateWebAddressValue(
    @Json(name = "ContactID")
    val contactID: Int,
    @Json(name = "Url")
    val url : String,
    @Json(name = "Priority")
    val priority: Int,
    @Json(name = "ID")
    val id : Int
):Parcelable