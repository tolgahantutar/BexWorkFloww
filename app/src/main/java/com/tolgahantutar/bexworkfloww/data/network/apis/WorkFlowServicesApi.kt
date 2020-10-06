package com.tolgahantutar.bexworkfloww.data.network.apis

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tolgahantutar.bexworkfloww.data.network.responses.GetDomainResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WorkFlowServicesApi {
    @FormUrlEncoded
    @POST("getdomain")
    suspend fun getDomain(
        @Field("Location") Location:String
    ):Response<GetDomainResponse>
}