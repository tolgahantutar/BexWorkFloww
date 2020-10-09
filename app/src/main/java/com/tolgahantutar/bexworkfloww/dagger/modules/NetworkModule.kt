package com.tolgahantutar.bexworkfloww.dagger.modules

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tolgahantutar.bexworkfloww.data.network.apis.ContactServicesApi
import com.tolgahantutar.bexworkfloww.data.network.apis.VisaServicesApi
import com.tolgahantutar.bexworkfloww.data.network.apis.WorkFlowServicesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)

object NetworkModule {
    @Singleton
    @Provides
    fun provideHttpClient() : OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(1,TimeUnit.MINUTES)
            .writeTimeout(1,TimeUnit.MINUTES)
            .build()
    }
    @Singleton
    @Provides

    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides

    fun provideRetrofit(moshi: Moshi,httpClient: OkHttpClient) : Retrofit.Builder{
        return Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
    }

    @Singleton
    @Provides

    fun provideVisaServices(retrofit: Retrofit.Builder): VisaServicesApi{

        return retrofit
            .baseUrl("http://bexfatestv2service.saasteknoloji.com/api/visa/")
            .build()
            .create(VisaServicesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideWorkFlowServices(retrofit: Retrofit.Builder): WorkFlowServicesApi{

        return retrofit
            .baseUrl("http://bexfatestv2service.saasteknoloji.com/api/bexworkflow/")
            .build()
            .create(WorkFlowServicesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideContactServices(retrofit: Retrofit.Builder) : ContactServicesApi{
        return retrofit
            .baseUrl("http://bexfatestv2service.saasteknoloji.com/api/contact/")
            .build()
            .create(ContactServicesApi::class.java)
    }

}