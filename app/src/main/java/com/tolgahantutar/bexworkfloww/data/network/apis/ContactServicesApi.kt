package com.tolgahantutar.bexworkfloww.data.network.apis

import com.tolgahantutar.bexworkfloww.data.models.requestbodies.*
import com.tolgahantutar.bexworkfloww.data.network.responses.createresponses.CreateAddressResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.createresponses.CreateMailResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.createresponses.CreatePhoneResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.createresponses.CreateWebAddressResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.deleteresponses.DeleteMailResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.deleteresponses.DeletePhoneResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.deleteresponses.DeleteWebAddressResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.getresponses.*
import com.tolgahantutar.bexworkfloww.data.network.responses.updateresponses.UpdateMailResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.updateresponses.UpdatePhoneResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.updateresponses.UpdateWebAddressResponse
import retrofit2.Response
import retrofit2.http.*

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
    @POST("webaddress/create")
    suspend fun createWebAddress(
        @Field("ContactID") contactID: Int,
        @Field("Url") url: String,
        @Field("Priority") priority: Int,
        @Field("ID") id : Int
    ): Response<CreateWebAddressResponse>

    @FormUrlEncoded
    @POST("email/create")
    suspend fun createMail(
        @Field("ContactID") contactID: Int,
        @Field("Address") address: String,
        @Field("Priority") priority: Int,
        @Field("ID") id: Int
    ):Response<CreateMailResponse>

    @FormUrlEncoded
    @POST("email/get")
    suspend fun getAllEmail(
        @Field("ContactID") contactID: Int,
        @Field("Page") page : Int,
        @Field("PageSize") pageSize: Int
    ):Response<GetAllEmailsResponse>

    @FormUrlEncoded
    @POST("email/delete")
    suspend fun deleteEmail(
        @Field("ContactID") contactID: Int,
        @Field("Address") address: String,
        @Field("Priority") priority: Int,
        @Field("ID") id: Int
    ):Response<DeleteMailResponse>

    @FormUrlEncoded
    @POST("phone/get")
    suspend fun getAllPhone(
        @Field("ContactID") contactID: Int,
        @Field("Page") page : Int,
        @Field("PageSize") pageSize: Int
    ):Response<GetAllPhonesResponse>

    @FormUrlEncoded
    @POST("phone/delete")
    suspend fun deletePhone(
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
    ):Response<DeletePhoneResponse>

    @FormUrlEncoded
    @POST("phone/create")
    suspend fun createPhone(
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
    ):Response<CreatePhoneResponse>

    @FormUrlEncoded
    @POST("address/get")
    suspend fun getAllAddress(
        @Field("ContactID") contactID: Int,
        @Field("Page") page : Int,
        @Field("PageSize") pageSize: Int
    ):Response<GetAllAddressResponse>

    @POST("address/create")
    suspend fun createAddress(
        @Body createAddressBody: CreateAddressBody
    ):Response<CreateAddressResponse>

    @POST("address/delete")
    suspend fun deleteAddress(
        @Body deleteAddressBody: CreateAddressBody
    ):Response<CreateAddressResponse>

    @POST("address/update")
    suspend fun updateAddress(
        @Body updateAddressBody: CreateAddressBody
    ):Response<CreateAddressResponse>

    @FormUrlEncoded
    @POST("webaddress/get")
    suspend fun getAllWebAddress(
        @Field("ContactID") contactID: Int,
        @Field("Page") page : Int,
        @Field("PageSize") pageSize: Int
    ):Response<GetAllWebAddressResponse>

    @FormUrlEncoded
    @POST("webaddress/delete")
    suspend fun deleteWebAddress(
        @Field("ContactID") contactID: Int,
        @Field("Url") address: String,
        @Field("Priority") priority: Int,
        @Field("ID") id: Int
    ):Response<DeleteWebAddressResponse>

    @FormUrlEncoded
    @POST("country/get")
    suspend fun getAllCountry(
        @Field("AddressBookID") addressBookId: Int,
        @Field("Page") page: Int,
        @Field("PageSize") pageSize: Int
    ):Response<GetAllCountryResponse>

    @FormUrlEncoded
    @POST("city/get")
    suspend fun getAllCity(
        @Field("ParentID") parentID: Int,
        @Field("Page") page: Int,
        @Field("PageSize") pageSize: Int
    ):Response<GetAllCityResponse>

    @FormUrlEncoded
    @POST("county/get")
    suspend fun getAllCounty(
        @Field("ParentID") parentID: Int,
        @Field("Page") page: Int,
        @Field("PageSize") pageSize: Int
    ):Response<GetAllCountyResponse>
}