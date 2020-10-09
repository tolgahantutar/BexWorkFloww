package com.tolgahantutar.bexworkfloww.data.models.getuser

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Units (
    @Json(name = "SecurityPermissionID")
    val securityPermissionId: Int,
    @Json(name = "OrganizationID")
    val organizationId : Int,
    @Json(name = "Type")
    val type: String,
    @Json(name = "IsATemplate")
    val isATemplate: Int,
    @Json(name = "Template")
    val template:Template,
    @Json(name = "DirectorType")
    val directorType: DirectorType,
    @Json(name = "SubUnits")
    val subUnits : List<SubUnits>?,
    @Json(name = "FunctionalitiesInText")
    val functionalitiesInText : String,
    @Json(name = "HierarchicalParentID")
    val hierarchicalParentID: Int,
    @Json(name = "HierarchicalParentName")
    val hierarchicalParentName: String,
    @Json(name = "SortOrder")
    val sortOrder : String,
    @Json(name = "HasSubRank")
    val hasSubRank : Boolean,
    @Json(name = "Name")
    val name : String,
    @Json(name="ID")
    val id : Int


)