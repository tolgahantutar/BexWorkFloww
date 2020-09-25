package com.tolgahantutar.bexworkfloww.data.models

import android.text.BoringLayout
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetDomainModel(
@Json(name = "AuthorityID")
var AuthorityID:Int,
@Json(name = "AddressBookID")
var AddressBookID: Int,
@Json(name = "ConfigurationManagerID")
var ConfigurationManagerID: Int,
@Json(name = "OrganizationID")
var OrganizationID: Int,
@Json(name = "CommunicationCenterID")
var CommunicationCenterID: Int,
@Json(name = "Authority")
var Authority: AuthorityModel?,
@Json(name = "Location")
var Location: String?,
@Json(name = "IsValid")
var IsValid: Boolean,
@Json(name = "ApiKey")
var ApiKey: String?,
@Json(name = "Name")
var Name: String?,
@Json(name = "ID")
var ID: Int
)