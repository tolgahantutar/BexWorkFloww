package com.tolgahantutar.bexworkfloww.data.models.getuser

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact.ContactModel
import com.tolgahantutar.bexworkfloww.data.models.getuserbyid.director.DirectorModel

@JsonClass(generateAdapter = true)
data class GetUserValue(
    @Json(name = "Units")
    val units : List<Units>?,
    @Json(name = "ViewOrganization")
    val viewOrganization: Boolean,
    @Json(name = "ApiKey")
    val apiKey : String,
    @Json(name = "Title")
    val title : String,
    @Json(name = "IsStakeholder")
    val isStakeholder: Boolean,
    @Json(name = "ProfileImageUrl")
    val profileImageUrl : String,
    @Json(name = "Director")
    val director : DirectorModel?,
    @Json(name = "OrganizationID")
    val organizationID : Int,
    @Json(name = "OrganizationName")
    val organizationName : String,
    @Json(name = "EmployeeStatus")
    val employeeStatus : String,
    @Json(name = "Contact")
    val contact : ContactModel,
    @Json(name = "FirstName")
    val firstName : String,
    @Json(name = "LastName")
    val lastName : String,
    @Json(name = "IsSuper")
    val isSuper : Boolean,
    @Json(name = "IsExecutive")
    val isExecutive : Boolean,
    @Json(name = "Name")
    val name : String,
    @Json(name="ID")
    val id : Int
)