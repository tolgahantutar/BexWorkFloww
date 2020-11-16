package com.tolgahantutar.bexworkfloww.data.network.repositories.getrepository

import com.tolgahantutar.bexworkfloww.data.network.SafeApiRequest
import com.tolgahantutar.bexworkfloww.data.network.apis.WorkFlowServicesApi
import com.tolgahantutar.bexworkfloww.data.network.responses.getresponses.GetDomainResponse
import javax.inject.Inject

class GetDomainRepository @Inject constructor (
    private val api: WorkFlowServicesApi
):SafeApiRequest(){

    suspend fun getDomain(Location: String): GetDomainResponse {
        return apiRequest { api.getDomain(Location) }
    }
}