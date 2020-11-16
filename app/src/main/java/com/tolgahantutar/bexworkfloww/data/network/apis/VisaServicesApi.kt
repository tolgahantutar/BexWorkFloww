package com.tolgahantutar.bexworkfloww.data.network.apis

import com.tolgahantutar.bexworkfloww.data.network.responses.updateresponses.AuthorizeSessionResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface VisaServicesApi {
@FormUrlEncoded
@POST("session/authorizesession")

suspend fun userLogin(
    @Field("SessionID") SessionID:Int,
    @Field("AuthorityID") AuthorityID: Int,
    @Field("UserName") UserName: String,
    @Field("Password") Password: String,
    @Field("LoginType") LoginType: String
): Response<AuthorizeSessionResponse>
}