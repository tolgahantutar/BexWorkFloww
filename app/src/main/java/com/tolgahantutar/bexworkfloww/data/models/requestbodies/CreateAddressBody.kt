package com.tolgahantutar.bexworkfloww.data.models.requestbodies

class CreateAddressBody (ContactID: Int,Country: RequestBodyCountry,County:RequestBodyCounty,State:RequestBodyState,City: RequestBodyCity,
Body:String,ZipCode:Int,Latitude: Int,Longitute:Int,Altitude:Int,Sensitivity:Int,DisplayBody:String,Priority:Int,MapUrl:String,ID:Int){
    val ContactID = ContactID
    val Country = Country
    val County = County
    val State= State
    val  City = City
    val Body = Body
    val ZipCode = ZipCode
    val Latitude = Latitude
    val Longitute = Longitute
    val Altitude = Altitude
    val Sensitivity = Sensitivity
    val DisplayBody = DisplayBody
    val Priority = Priority
    val MapUrl = MapUrl
    val ID = ID
}