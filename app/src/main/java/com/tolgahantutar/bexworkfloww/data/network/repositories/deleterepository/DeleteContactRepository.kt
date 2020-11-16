package com.tolgahantutar.bexworkfloww.data.network.repositories.deleterepository

import com.tolgahantutar.bexworkfloww.data.models.requestbodies.CreateAddressBody
import com.tolgahantutar.bexworkfloww.data.network.SafeApiRequest
import com.tolgahantutar.bexworkfloww.data.network.apis.ContactServicesApi
import com.tolgahantutar.bexworkfloww.data.network.responses.createresponses.CreateAddressResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.deleteresponses.DeleteMailResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.deleteresponses.DeletePhoneResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.deleteresponses.DeleteWebAddressResponse
import javax.inject.Inject

class DeleteContactRepository @Inject constructor(
    private val api: ContactServicesApi
):SafeApiRequest(){
    suspend fun deleteAddress(
        deleteAddressBody: CreateAddressBody
    ): CreateAddressResponse {
        return apiRequest { api.deleteAddress(deleteAddressBody) }
    }
    suspend fun deleteMail(contactID: Int,address: String,priority: Int,id:Int): DeleteMailResponse {
        return apiRequest { api.deleteEmail(contactID, address, priority, id) }
    }
    suspend fun deletePhone(
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
    ): DeletePhoneResponse {
        return apiRequest {  api.deletePhone(contactID,priority,type,countryCode,number,extension,availability,displayBody,cleanBody,isVerified,
            verificatioCode,verificationDate,basicPhoneNumber, id)}
    }
    suspend fun deleteWebAddress(contactID: Int,url: String,priority: Int,id:Int): DeleteWebAddressResponse {
        return apiRequest { api.deleteWebAddress(contactID,url,priority, id) }
    }
}