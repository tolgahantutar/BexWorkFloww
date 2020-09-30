package com.tolgahantutar.bexworkfloww.dagger.modules

import com.tolgahantutar.bexworkfloww.data.network.apis.VisaServicesApi
import com.tolgahantutar.bexworkfloww.data.network.apis.WorkFlowServicesApi
import com.tolgahantutar.bexworkfloww.data.network.repositories.AuthorizeSessionRepository
import com.tolgahantutar.bexworkfloww.data.network.repositories.GetDomainRepository
import dagger.Module
import dagger.Provides


@Module
class AuthorizeSessionRepositoryModule {
    lateinit var visaServicesApi : VisaServicesApi
    lateinit var workFlowServicesApi: WorkFlowServicesApi

    constructor(visaServicesApi: VisaServicesApi,workFlowServicesApi: WorkFlowServicesApi){
        this.visaServicesApi=visaServicesApi
        this.workFlowServicesApi=workFlowServicesApi

    }
    @Provides
    fun provideAuthorizeSessionRepository(): AuthorizeSessionRepository {
        return AuthorizeSessionRepository(visaServicesApi)
    }
    @Provides
    fun provideGetDomainRepository(): GetDomainRepository {
        return GetDomainRepository(workFlowServicesApi)
    }

}