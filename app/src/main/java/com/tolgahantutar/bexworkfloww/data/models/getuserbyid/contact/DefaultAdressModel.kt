package com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact.defaultaddress.CityModel
import com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact.defaultaddress.CountryModel
import com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact.defaultaddress.CountyModel
import com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact.defaultaddress.StateModel

@JsonClass(generateAdapter = true)
data class DefaultAdressModel(
    @Json(name = "ContactID")
    var contactId : String,
    @Json(name = "Country")
    var countryModel: CountryModel,
    @Json(name = "County")
    var countyModel: CountyModel,
    @Json(name = "State")
    var stateModel: StateModel,
    @Json(name = "City")
    var cityModel: CityModel,
    @Json(name = "Body")
    var body :String,
    @Json(name = "ZipCode")
    var zipCode : String,
    @Json(name = "Latitude")
    var latitude: String,
    @Json(name = "Longitute")
    var longitute: String,
    @Json(name = "Altitude")
    var altitude : String,
    @Json(name = "Sensitivity")
    var sensitivity : String,
    @Json(name = "DisplayBody")
    var displayBody : String?,
    @Json(name = "Priority")
    var priority : String,
    @Json(name = "MapUrl")
    var mapUrl : String?,
    @Json(name = "ID")
    var id : String
)