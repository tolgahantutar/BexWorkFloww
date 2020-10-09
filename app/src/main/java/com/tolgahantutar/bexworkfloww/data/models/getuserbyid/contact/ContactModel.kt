package com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact.contacttype.ContactTypeModel


@JsonClass(generateAdapter = true)
data class ContactModel(
    @Json(name = "AddressBookID")
    var addressBookId : String,
    @Json(name = "ContactType")
    var contactTypeModel: ContactTypeModel,
    @Json(name = "DisplayName")
    var displayName : String,
    @Json(name = "RelatedEntityType")
    var relatedEntityType: String,
    @Json(name = "RelatedEntityID")
    var relatedEntityId: String,
    @Json(name = "DefaultAddress")
    var defaultAdressModel: DefaultAdressModel,
    @Json(name = "DefaultEmail")
    var defaultEmail:DefaultEmailModel,
    @Json(name = "DefaultPhone")
    var defaultPhoneModel: DefaultPhoneModel,
    @Json(name = "DefaultWebAddress")
    var defaultWebAddressModel: DefaultWebAddressModel,
    @Json(name = "Name")
    var name : String,
    @Json(name="ID")
    var id : String

)