package com.tolgahantutar.bexworkfloww.data.network.repositories

import com.tolgahantutar.bexworkfloww.data.network.SafeApiRequest
import com.tolgahantutar.bexworkfloww.data.network.apis.WorkFlowServicesApi
import com.tolgahantutar.bexworkfloww.data.network.responses.GetUserResponse
import javax.inject.Inject

class GetUserRepository @Inject constructor(
    private val api : WorkFlowServicesApi
):SafeApiRequest(){
    suspend fun getUser(id:Int,authorization : String) : GetUserResponse{
        return apiRequest { api.getUser(id,authorization) }
    }
}