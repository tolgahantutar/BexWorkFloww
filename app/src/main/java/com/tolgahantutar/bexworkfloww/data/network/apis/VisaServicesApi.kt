package com.tolgahantutar.bexworkfloww.data.network.apis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tolgahantutar.bexworkfloww.data.network.responses.AuthorizeSessionResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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