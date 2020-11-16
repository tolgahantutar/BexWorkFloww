package com.tolgahantutar.bexworkfloww.data.network.repositories.getrepository

import com.tolgahantutar.bexworkfloww.data.network.SafeApiRequest
import com.tolgahantutar.bexworkfloww.data.network.apis.ContactServicesApi
import com.tolgahantutar.bexworkfloww.data.network.responses.getresponses.GetAllCountryResponse
import javax.inject.Inject

class GetAllCountryRepository @Inject constructor(
    private val api: ContactServicesApi
):SafeApiRequest(){
    suspend fun getAllCountry(addressBookID: Int,page: Int,pageSize: Int): GetAllCountryResponse {
        return apiRequest { api.getAllCountry(addressBookID,page, pageSize) }
    }
}