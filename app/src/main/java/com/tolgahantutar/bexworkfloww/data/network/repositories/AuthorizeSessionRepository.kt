package com.tolgahantutar.bexworkfloww.data.network.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tolgahantutar.bexworkfloww.data.network.responses.AuthorizeSessionResponse
import com.tolgahantutar.bexworkfloww.data.network.SafeApiRequest
import com.tolgahantutar.bexworkfloww.data.network.apis.VisaServicesApi
import javax.inject.Inject

class AuthorizeSessionRepository(
   // private val api : VisaServicesApi
):SafeApiRequest(){
    lateinit var api: VisaServicesApi
 @Inject constructor(api : VisaServicesApi) : this() {
this.api=api
}
suspend fun userLogin(SessionID: Int,AuthorityID: Int,UserName: String,Password : String,LoginType: String): AuthorizeSessionResponse{
return apiRequest { api.userLogin(SessionID, AuthorityID, UserName, Password, LoginType) }

}


}