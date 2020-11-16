package com.tolgahantutar.bexworkfloww.data.network.repositories.createrepository

import com.tolgahantutar.bexworkfloww.data.models.requestbodies.*
import com.tolgahantutar.bexworkfloww.data.network.SafeApiRequest
import com.tolgahantutar.bexworkfloww.data.network.apis.ContactServicesApi
import com.tolgahantutar.bexworkfloww.data.network.responses.createresponses.CreateAddressResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.createresponses.CreateMailResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.createresponses.CreatePhoneResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.createresponses.CreateWebAddressResponse
import javax.inject.Inject

class CreateContactRepository @Inject constructor(
    private val api : ContactServicesApi
):SafeApiRequest(){
    suspend fun createMail(contactID: Int,address: String,priority: Int, id: Int): CreateMailResponse {
        return apiRequest { api.createMail(contactID, address, priority, id) }
    }
    suspend fun createPhone(
        contactID: Int,
        priority: Int,
        type: String,
        countryCode: String,
        number: String,
        extension: String,
        availability: String,
        displayBody: String,
        cleanBody: String,
        isVerified: Int,
        verificatioCode: String,
        verificationDate: String,
        basicPhoneNumber: String,
        id: Int
    ): CreatePhoneResponse {
        return apiRequest { api.createPhone(contactID,priority,type,countryCode,number,extension,availability,displayBody,cleanBody,
            isVerified,verificatioCode,verificationDate,basicPhoneNumber,id) }
    }
    suspend fun createWebAddress(
        contactID: Int,url:String,priority: Int,id: Int
    ): CreateWebAddressResponse {
        return apiRequest { api.createWebAddress(contactID,url, priority, id) }
    }
    suspend fun createAddress(
        createAddressBody: CreateAddressBody
        ): CreateAddressResponse {
        return apiRequest { api.createAddress(createAddressBody) }
    }
}