package com.tolgahantutar.bexworkfloww.data.network.repositories.getrepository

import com.tolgahantutar.bexworkfloww.data.network.SafeApiRequest
import com.tolgahantutar.bexworkfloww.data.network.apis.ContactServicesApi
import com.tolgahantutar.bexworkfloww.data.network.responses.getresponses.GetAllWebAddressResponse
import javax.inject.Inject

class GetAllWebAddressRepository @Inject constructor(
    private val api: ContactServicesApi
):SafeApiRequest() {
    suspend fun getAllWebAddress(contactID: Int,page: Int,pageSize: Int) : GetAllWebAddressResponse {
        return apiRequest { api.getAllWebAddress(contactID, page, pageSize) }
    }
}