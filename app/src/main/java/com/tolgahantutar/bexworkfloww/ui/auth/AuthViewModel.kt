package com.tolgahantutar.bexworkfloww.ui.auth

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgahantutar.bexworkfloww.data.network.repositories.AuthorizeSessionRepository
import com.tolgahantutar.bexworkfloww.data.network.repositories.GetDomainRepository
import com.tolgahantutar.bexworkfloww.data.network.repositories.GetUserGlobalRepository
import com.tolgahantutar.bexworkfloww.data.network.repositories.GetUserRepository
import com.tolgahantutar.bexworkfloww.data.network.responses.GetUserResponse
import com.tolgahantutar.bexworkfloww.ui.home.HomeActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel @ViewModelInject constructor (
   private val authorizeSessionRepository: AuthorizeSessionRepository,
   private val getDomainRepository: GetDomainRepository,
   private val getUserRepository: GetUserRepository
):ViewModel() {
  // private lateinit var  getUserResponse: GetUserResponse
    var userName :String?=null
    var password: String ? = null
    val isLoading = MutableLiveData<Boolean>()
    private val location = "bexfatest.saasteknoloji.com"
    val isSuccessfull = MutableLiveData<Boolean>()
    var getUserResponseMutable = MutableLiveData<GetUserResponse>()
    var getUserGlobalRepository = GetUserGlobalRepository.instance
    fun onClickUserLogin(view: View){
        val sessionID = 0
        val authorityID = 0
        val loginType = "System"

        viewModelScope.launch {
                if(!(userName==null||password==null)){
                isLoading.value = true

                    val authResponse = userLogin(sessionID,authorityID,userName!!,password!!,loginType)

                if(authResponse.Result){
                    isLoading.value=false
                    val domainResponse=getDomain(location)
                    val getUserResponse = getUser(authResponse.authorizeSessionModel!!.ID,"Bearer "+domainResponse.getDomainModel.ApiKey)
                    getUserGlobalRepository.setResult(getUserResponse.getUserValue,getUserResponse.result,getUserResponse.description,getUserResponse.code)

                    if (getUserGlobalRepository.result!!.result){

                        isSuccessfull.value=true
                        getUserResponseMutable.value=getUserResponse
                    }
                    //Toast.makeText(view.context, "Login Successfull", Toast.LENGTH_LONG).show()
                    val intent = Intent(view.context,HomeActivity::class.java)
                    view.context.startActivity(intent)
                }else{
                    isLoading.value=false
                    Toast.makeText(view.context, "Login Failed!!", Toast.LENGTH_LONG).show()
                }
           }
            else{
                Toast.makeText(view.context, "Kullanıcı adı ve şifre boş bırakılamaz!!", Toast.LENGTH_SHORT).show()
            }
        }
}


suspend fun userLogin(
SessionID : Int,
AuthorityID: Int,
UserName: String,
Password : String,
LoginType: String
)= withContext(Dispatchers.IO){authorizeSessionRepository.userLogin(SessionID, AuthorityID, UserName, Password, LoginType)}

suspend fun getUser(
id: Int,
authorization : String
)= withContext(Dispatchers.Main){getUserRepository.getUser(id,authorization)}

suspend fun getDomain(
Location: String
)= withContext(Dispatchers.IO){getDomainRepository.getDomain(Location)}

}