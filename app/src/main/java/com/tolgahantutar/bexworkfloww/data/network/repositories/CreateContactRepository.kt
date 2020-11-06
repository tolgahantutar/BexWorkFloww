package com.tolgahantutar.bexworkfloww.data.network.repositories

import com.tolgahantutar.bexworkfloww.data.network.SafeApiRequest
import com.tolgahantutar.bexworkfloww.data.network.apis.ContactServicesApi
import com.tolgahantutar.bexworkfloww.data.network.responses.CreateMailResponse
import javax.inject.Inject

class CreateContactRepository @Inject constructor(
    private val api : ContactServicesApi
):SafeApiRequest(){
    suspend fun createMail(contactID: Int,address: String,priority: Int, id: Int): CreateMailResponse {
        return apiRequest { api.createMail(contactID, address, priority, id) }
    }
}