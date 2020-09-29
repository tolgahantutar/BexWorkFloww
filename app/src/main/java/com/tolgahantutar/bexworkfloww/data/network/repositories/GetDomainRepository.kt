package com.tolgahantutar.bexworkfloww.data.network.repositories

import com.tolgahantutar.bexworkfloww.data.network.SafeApiRequest
import com.tolgahantutar.bexworkfloww.data.network.apis.WorkFlowServicesApi
import com.tolgahantutar.bexworkfloww.data.network.responses.GetDomainResponse

class GetDomainRepository (
    private val api: WorkFlowServicesApi
):SafeApiRequest(){

    suspend fun getDomain(Location: String):GetDomainResponse{
        return apiRequest { api.getDomain(Location) }
    }
}