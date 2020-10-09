package com.tolgahantutar.bexworkfloww.data.network.apis

import com.tolgahantutar.bexworkfloww.data.network.responses.GetContactResponse
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
}