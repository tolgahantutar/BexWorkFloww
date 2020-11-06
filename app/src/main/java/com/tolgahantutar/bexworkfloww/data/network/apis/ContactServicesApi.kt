package com.tolgahantutar.bexworkfloww.data.network.apis

import com.tolgahantutar.bexworkfloww.data.network.responses.*
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ContactServicesApi {
    @FormUrlEncoded
    @POST("contact/get")
    suspend fun getContact(
        @Field("AddressBookID") addressBookId : Int,
        @Header("Authorization") authorization : String
    ):Response<GetContactResponse>

    @FormUrlEncoded
    @POST("email/update")
    suspend fun updateMail(
        @Field("ContactID") contactID: Int,
        @Field("Address") address: String,
        @Field("Priority") priority: Int,
        @Field("ID") id: Int
    ):Response<UpdateMailResponse>

    @FormUrlEncoded
    @POST("phone/update")
    suspend fun updatePhone(
        @Field("ContactID") contactID: Int,
        @Field("Priority") priority: Int,
        @Field("Type") type: String,
        @Field("CountryCode") countryCode: String,
        @Field("Number") number: String,
        @Field("Extension") extension: String,
        @Field("Availability") availability: String,
        @Field("DisplayBody") displayBody: String,
        @Field("CleanBody") cleanBody: String,
        @Field("IsVerified") isVerified : Int,
        @Field("VerificationCode") verificationCode: String,
        @Field("VerificationDate") verificationDate: String,
        @Field("BasicPhoneNumber") basicPhoneNumber: String,
        @Field("ID") id : Int
    ):Response<UpdatePhoneResponse>

    @FormUrlEncoded
    @POST("webaddress/update")
    suspend fun updateWebAddress(
        @Field("ContactID") contactID: Int,
        @Field("Url") url: String,
        @Field("Priority") priority: Int,
        @Field("ID") id : Int
    ):Response<UpdateWebAddressResponse>

    @FormUrlEncoded
    @POST("email/create")
    suspend fun createMail(
        @Field("ContactID") contactID: Int,
        @Field("Address") address: String,
        @Field("Priority") priority: Int,
        @Field("ID") id: Int
    ):Response<CreateMailResponse>
}