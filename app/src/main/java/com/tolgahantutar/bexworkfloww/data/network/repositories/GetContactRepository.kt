package com.tolgahantutar.bexworkfloww.data.network.repositories

import com.tolgahantutar.bexworkfloww.data.network.SafeApiRequest
import com.tolgahantutar.bexworkfloww.data.network.apis.ContactServicesApi
import com.tolgahantutar.bexworkfloww.data.network.responses.GetContactResponse
import javax.inject.Inject

class GetContactRepository @Inject constructor(
    private val api : ContactServicesApi
):SafeApiRequest(){
    suspend fun getContact(addressBookId : Int,authorization : String) : GetContactResponse{
        return apiRequest { api.getContact(addressBookId,authorization) }
    }
}