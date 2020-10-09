package com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact.contacttype

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
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
)