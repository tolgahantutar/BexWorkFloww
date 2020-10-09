package com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DefaultWebAddressModel(
    @Json(name = "ContactID")
    var contactId : String,
    @Json(name = "Url")
    var url : String?,
    @Json(name = "Priority")
    var priority : String,
    @Json(name = "ID")
    var id : String
)