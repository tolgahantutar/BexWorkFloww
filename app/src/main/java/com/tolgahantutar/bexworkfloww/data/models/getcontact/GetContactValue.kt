package com.tolgahantutar.bexworkfloww.data.models.getcontact

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact.DefaultAdressModel
import com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact.DefaultEmailModel
import com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact.DefaultPhoneModel
import com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact.DefaultWebAddressModel
import com.tolgahantutar.bexworkfloww.data.models.getuserbyid.contact.contacttype.ContactTypeModel
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class GetContactValue(
    @Json(name = "AddressBookID")
    val addressBookId : Int,
    @Json(name = "ContactType")
    val contactType:ContactTypeModel,
    @Json(name = "DisplayName")
    val displayName : String,
    @Json(name = "RelatedEntityType")
    val relatedEntityType : String,
    @Json(name = "RelatedEntityID")
    val relatedEntityId : String,
    @Json(name = "DefaultAddress")
    val defaultAddress : DefaultAdressModel,
    @Json(name = "DefaultEmail")
    val defaultEmail : DefaultEmailModel,
    @Json(name = "DefaultPhone")
    val defaultPhone : DefaultPhoneModel,
    @Json(name = "DefaultWebAddress")
    val defaultWebAddress : DefaultWebAddressModel,
    @Json(name = "Name")
    val name : String,
    @Json(name="ID")
    val id : Int
):Serializable