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

    companion object{
        operator fun invoke(): WorkFlowServicesApi{
            val okHttpClient = OkHttpClient.Builder().build()
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://bexfatestv2service.saasteknoloji.com/api/bexworkflow/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(WorkFlowServicesApi::class.java)
        }
    }
}