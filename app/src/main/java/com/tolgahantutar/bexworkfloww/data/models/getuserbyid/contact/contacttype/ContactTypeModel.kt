package com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact.contacttype

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class ContactTypeModel(
    @Json(name = "AddressBookID")
    var addressBookId : String,
    @Json (name = "ShowOnMap")
    var showOnMap : String,
    @Json (name = "Priority")
    var priority : String,
    @Json(name = "Name")
    var name : String?,
    @Json (name ="ID")
    var id : String
):Parcelable