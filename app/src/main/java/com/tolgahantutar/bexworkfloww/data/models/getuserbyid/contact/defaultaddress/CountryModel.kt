package com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact.defaultaddress

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryModel(
    @Json(name = "AddressBookID")
    var addressBookId: String,
    @Json(name = "Name")
    var name: String?,
    @Json(name = "ID")
    var id : String
)