package com.tolgahantutar.bexworkfloww.data.models.getallemails

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class GetAllEmailsValue (
    @Json(name = "ContactID")
    val contactID: Int,
    @Json(name = "Address")
    val address: String,
    @Json(name = "Priority")
    val priority: Int,
    @Json(name = "ID")
    val id : Int
):Parcelable