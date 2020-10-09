package com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DefaultEmailModel(
    @Json(name = "ContactID")
    var contactId : String,
    @Json(name = "Address")
    var address: String,
    @Json (name = "Priority")
    var priority : String,
    @Json(name = "ID")
    var id : String
)