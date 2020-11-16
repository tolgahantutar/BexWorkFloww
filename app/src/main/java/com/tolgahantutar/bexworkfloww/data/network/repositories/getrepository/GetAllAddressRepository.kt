package com.tolgahantutar.bexworkfloww.data.network.repositories.getrepository

import com.tolgahantutar.bexworkfloww.data.network.SafeApiRequest
import com.tolgahantutar.bexworkfloww.data.network.apis.ContactServicesApi
import com.tolgahantutar.bexworkfloww.data.network.responses.getresponses.GetAllAddressResponse
import javax.inject.Inject

class GetAllAddressRepository @Inject constructor(
    private val api: ContactServicesApi
):SafeApiRequest(){
    suspend fun getAllAddress(contactID: Int,page: Int,pageSize: Int): GetAllAddressResponse {
        return apiRequest { api.getAllAddress(contactID, page, pageSize) }
    }
}