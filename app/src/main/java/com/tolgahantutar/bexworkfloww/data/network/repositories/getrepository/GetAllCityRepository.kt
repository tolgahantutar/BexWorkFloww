package com.tolgahantutar.bexworkfloww.data.network.repositories.getrepository

import com.tolgahantutar.bexworkfloww.data.network.SafeApiRequest
import com.tolgahantutar.bexworkfloww.data.network.apis.ContactServicesApi
import com.tolgahantutar.bexworkfloww.data.network.responses.getresponses.GetAllCityResponse
import javax.inject.Inject

class GetAllCityRepository @Inject constructor(
    private val api: ContactServicesApi
):SafeApiRequest() {
    suspend fun getAllCity(parentID: Int,page: Int, pageSize: Int): GetAllCityResponse {
        return apiRequest { api.getAllCity(parentID, page, pageSize) }
    }
}