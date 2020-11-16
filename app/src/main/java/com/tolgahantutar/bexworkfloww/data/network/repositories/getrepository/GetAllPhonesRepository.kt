package com.tolgahantutar.bexworkfloww.data.network.repositories.getrepository

import com.tolgahantutar.bexworkfloww.data.network.SafeApiRequest
import com.tolgahantutar.bexworkfloww.data.network.apis.ContactServicesApi
import com.tolgahantutar.bexworkfloww.data.network.responses.getresponses.GetAllPhonesResponse
import javax.inject.Inject

class GetAllPhonesRepository @Inject constructor(
    private val api : ContactServicesApi
):SafeApiRequest(){
    suspend fun getAllPhones(contactID: Int,page: Int,pageSize: Int): GetAllPhonesResponse {
        return apiRequest { api.getAllPhone(contactID, page, pageSize) }
    }
}