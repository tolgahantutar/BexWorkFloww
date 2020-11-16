package com.tolgahantutar.bexworkfloww.data.network.repositories.getrepository

import com.tolgahantutar.bexworkfloww.data.network.SafeApiRequest
import com.tolgahantutar.bexworkfloww.data.network.apis.ContactServicesApi
import com.tolgahantutar.bexworkfloww.data.network.responses.getresponses.GetAllEmailsResponse
import javax.inject.Inject

class GetAllEmailsRepository @Inject constructor(
    private val api : ContactServicesApi
):SafeApiRequest(){
    suspend fun getAllEmails(contactID: Int,page: Int,pageSize: Int): GetAllEmailsResponse {
        return apiRequest { api.getAllEmail(contactID, page, pageSize) }
    }
}