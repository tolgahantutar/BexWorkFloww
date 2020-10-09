package com.tolgahantutar.bexworkfloww.data.models.getuserbyid

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact.ContactModel
import com.tolgahantutar.bexworkfloww.data.models.getuserbyid.director.DirectorModel

@JsonClass(generateAdapter = true)
data class UserGetByIdModel(
    @Json (name = "Title")
    var title : String,
    @Json (name = "IsStakeholder")
    var isStakeHolder : Boolean,
    @Json (name = "ProfileImageUrl")
    var profileImageUrl : String,
    @Json(name = "Director")
    var director : DirectorModel,
    @Json (name = "OrganizationID")
    var organizationId: String,
    @Json (name = "OrganizationName")
    var organizationName : String,
    @Json (name = "EmployeeStatus")
    var employeeStatus : String,
    @Json(name = "Contact")
    var contactModel: ContactModel,
    @Json(name = "FirstName")
    var firstName  : String,
    @Json(name = "LastName")
    var lastName : String,
    @Json(name = "IsSuper")
    var isSuper : Boolean,
    @Json(name = "IsExecutive")
    var isExecutive : Boolean,
    @Json(name = "Name")
    var name : String,
    @Json(name="ID")
    var id : String

)