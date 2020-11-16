package com.tolgahantutar.bexworkfloww.data.network.repositories

import com.tolgahantutar.bexworkfloww.data.network.responses.updateresponses.AuthorizeSessionResponse
import com.tolgahantutar.bexworkfloww.data.network.SafeApiRequest
import com.tolgahantutar.bexworkfloww.data.network.apis.VisaServicesApi
import javax.inject.Inject

class AuthorizeSessionRepository @Inject constructor(
    private val api : VisaServicesApi
):SafeApiRequest(){
suspend fun userLogin(SessionID: Int,AuthorityID: Int,UserName: String,Password : String,LoginType: String): AuthorizeSessionResponse {
return apiRequest { api.userLogin(SessionID, AuthorityID, UserName, Password, LoginType) }
}
}