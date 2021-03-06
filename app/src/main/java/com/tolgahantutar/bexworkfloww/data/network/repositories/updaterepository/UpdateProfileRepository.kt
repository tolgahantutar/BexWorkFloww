package com.tolgahantutar.bexworkfloww.data.network.repositories.updaterepository

import com.tolgahantutar.bexworkfloww.data.models.requestbodies.CreateAddressBody
import com.tolgahantutar.bexworkfloww.data.network.SafeApiRequest
import com.tolgahantutar.bexworkfloww.data.network.apis.ContactServicesApi
import com.tolgahantutar.bexworkfloww.data.network.responses.createresponses.CreateAddressResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.updateresponses.UpdateMailResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.updateresponses.UpdatePhoneResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.updateresponses.UpdateWebAddressResponse
import javax.inject.Inject

class UpdateProfileRepository @Inject constructor(
    private val api : ContactServicesApi
):SafeApiRequest(){
    suspend fun updateMail(contactID:Int, address:String, priority:Int, id:Int) : UpdateMailResponse {
        return apiRequest { api.updateMail(contactID,address,priority,id) }
    }

    suspend fun updateWebAddress(contactID: Int,url:String,priority: Int,id: Int): UpdateWebAddressResponse {
        return apiRequest { api.updateWebAddress(contactID,url, priority, id) }
    }

    suspend fun updatePhone(contactID: Int,
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
                            id: Int): UpdatePhoneResponse {
        return apiRequest { api.updatePhone(contactID,priority,type,countryCode,number,extension,availability,displayBody,cleanBody,
            isVerified,verificatioCode,verificationDate,basicPhoneNumber,id) }
    }
    suspend fun updateAddress(
        updateAddressBody: CreateAddressBody
    ): CreateAddressResponse {
        return apiRequest { api.updateAddress(updateAddressBody) }
    }
}