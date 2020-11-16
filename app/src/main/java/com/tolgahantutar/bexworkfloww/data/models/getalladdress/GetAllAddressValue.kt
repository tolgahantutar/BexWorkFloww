package com.tolgahantutar.bexworkfloww.data.models.getalladdress

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact.defaultaddress.CityModel
import com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact.defaultaddress.CountryModel
import com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact.defaultaddress.CountyModel
import com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact.defaultaddress.StateModel
import kotlinx.android.parcel.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class GetAllAddressValue(
    @Json(name = "ContactID")
    val contactID: Int,
    @Json(name = "Country")
    val country: CountryModel,
    @Json(name = "County")
    val county: CountyModel,
    @Json(name = "State")
    val state: StateModel,
    @Json(name = "City")
    val city: CityModel,
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
):Parcelable