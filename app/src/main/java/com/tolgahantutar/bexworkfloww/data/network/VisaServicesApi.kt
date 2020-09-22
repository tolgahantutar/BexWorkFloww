package com.tolgahantutar.bexworkfloww.data.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface VisaServicesApi {
@FormUrlEncoded
@POST("index#!/Visa32Services/Session_AuthorizeSession")

suspend fun userLogin(
    @Field("SessionID") SessionID:Int,
    @Field("AuthorityID") AuthorityID: Int,
    @Field("UserName") UserName: String,
    @Field("Password") Password: String,
    @Field("LoginType") LoginType: String
):Response<AuthorizeSessionResponse>

companion object{
operator fun invoke():VisaServicesApi{
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    val okHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()
    val gson = GsonBuilder()
        .setLenient()
        .create()
return Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl("http://bexfatestv2service.saasteknoloji.com/swagger/ui/")
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()
    .create(VisaServicesApi::class.java)

}
}
}