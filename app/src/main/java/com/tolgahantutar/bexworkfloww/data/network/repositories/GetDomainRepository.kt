package com.tolgahantutar.bexworkfloww.data.network.repositories

import com.tolgahantutar.bexworkfloww.data.network.SafeApiRequest
import com.tolgahantutar.bexworkfloww.data.network.apis.WorkFlowServicesApi
import com.tolgahantutar.bexworkfloww.data.network.responses.GetDomainResponse
import javax.inject.Inject

class GetDomainRepository (
    //private val api: WorkFlowServicesApi
):SafeApiRequest(){
    lateinit var api: WorkFlowServicesApi

    @Inject constructor(api:WorkFlowServicesApi) : this(){
        this.api=api
    }

    suspend fun getDomain(Location: String):GetDomainResponse{
        return apiRequest { api.getDomain(Location) }
    }
}