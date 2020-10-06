package com.tolgahantutar.bexworkfloww.data.network.repositories

import androidx.hilt.lifecycle.ViewModelInject
import com.tolgahantutar.bexworkfloww.data.network.SafeApiRequest
import com.tolgahantutar.bexworkfloww.data.network.apis.WorkFlowServicesApi
import com.tolgahantutar.bexworkfloww.data.network.responses.GetDomainResponse
import javax.inject.Inject

class GetDomainRepository @Inject constructor (
    private val api: WorkFlowServicesApi
):SafeApiRequest(){

    suspend fun getDomain(Location: String):GetDomainResponse{
        return apiRequest { api.getDomain(Location) }
    }
}