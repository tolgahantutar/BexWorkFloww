package com.tolgahantutar.bexworkfloww.data.network.repositories

import com.tolgahantutar.bexworkfloww.data.network.AuthorizeSessionResponse
import com.tolgahantutar.bexworkfloww.data.network.SafeApiRequest
import com.tolgahantutar.bexworkfloww.data.network.VisaServicesApi

class AuthorizeSessionRepository(
    private val api : VisaServicesApi
):SafeApiRequest(){

suspend fun userLogin(SessionID: Int,AuthorityID: Int,UserName: String,Password : String,LoginType: String): AuthorizeSessionResponse {
return apiRequest { api.userLogin(SessionID, AuthorityID, UserName, Password, LoginType) }

}


}