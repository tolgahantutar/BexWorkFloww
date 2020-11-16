package com.tolgahantutar.bexworkfloww.data.network.repositories.getrepository

import com.tolgahantutar.bexworkfloww.data.network.SafeApiRequest
import com.tolgahantutar.bexworkfloww.data.network.apis.ContactServicesApi
import com.tolgahantutar.bexworkfloww.data.network.responses.getresponses.GetAllCountyResponse
import javax.inject.Inject

class GetAllCountyRepository @Inject constructor(
    private val api: ContactServicesApi
):SafeApiRequest(){
    suspend fun getAllCounty(parentID:Int, page: Int, pageSize: Int): GetAllCountyResponse {
        return apiRequest { api.getAllCounty(parentID, page, pageSize) }
    }
}