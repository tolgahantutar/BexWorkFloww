package com.tolgahantutar.bexworkfloww.ui.auth

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgahantutar.bexworkfloww.data.network.repositories.AuthorizeSessionRepository
import com.tolgahantutar.bexworkfloww.data.network.repositories.getrepository.GetDomainRepository
import com.tolgahantutar.bexworkfloww.data.network.repositories.getrepository.GetUserRepository
import com.tolgahantutar.bexworkfloww.data.network.responses.getresponses.GetUserResponse
import com.tolgahantutar.bexworkfloww.data.sharedpref.SharedPrefSingletonAuthID
import com.tolgahantutar.bexworkfloww.data.sharedpref.SharedPrefSingletonDomainKey
import com.tolgahantutar.bexworkfloww.data.sharedpref.SharedPrefSingletonUserAPI
import com.tolgahantutar.bexworkfloww.ui.home.HomeActivity
import kotlinx.coroutines.*

class AuthViewModel @ViewModelInject constructor (
    private val authorizeSessionRepository: AuthorizeSessionRepository,
    private val getDomainRepository: GetDomainRepository,
    private val getUserRepository: GetUserRepository,
    private val sharedPrefSingletonAuthID: SharedPrefSingletonAuthID,
    private val sharedPrefSingletonDomainKey: SharedPrefSingletonDomainKey,
    private val sharedPrefSingletonUserAPI: SharedPrefSingletonUserAPI
):ViewModel() {
    var userName :String?=null
    var password: String ? = null
    val isLoading = MutableLiveData<Boolean>()
    val isFinished = MutableLiveData<Boolean>()
    val isAuthExists = MutableLiveData<Boolean>(false)
    val isDomainExists = MutableLiveData<Boolean>(false)
    val authID = MutableLiveData<Int>()
    val domainKey = MutableLiveData<String>()
    private val location = "bexfatest.saasteknoloji.com"
    var getUserResponseMutable = MutableLiveData<GetUserResponse>()
    val isCheckTrue = MutableLiveData<Boolean>(false)
    fun checkApiKey(){
        if(sharedPrefSingletonUserAPI.getSomeStringValue().toString()!="notGenerated"){
            isCheckTrue.value=true
        }
        else{
            isCheckTrue.value=false
        }
    }

    fun onClickUserLogin(view: View){
        val sessionID = 0
        val authorityID = 0
        val loginType = "System"
        viewModelScope.launch {
                if(!(userName==null||password==null)){
                isLoading.value = true
                    val authResponse = userLogin(sessionID,authorityID,userName!!,password!!,loginType)
                    isAuthExists.value=true
                    authID.value=authResponse.authorizeSessionModel!!.ID
                    sharedPrefSingletonAuthID.setSomeStringValue(authID.value!!)
                if(authResponse.Result){
                    isLoading.value=false
                    val domainResponse=getDomain(location)
                    isDomainExists.value=true
                    domainKey.value = domainResponse.getDomainModel.ApiKey
                    sharedPrefSingletonDomainKey.setSomeStringValue(domainKey.value)
                    val getUserResponse = getUser(authResponse.authorizeSessionModel.ID,"Bearer "+domainResponse.getDomainModel.ApiKey)
                    getUserResponseMutable.value = getUserResponse
                    sharedPrefSingletonUserAPI.setSomeStringValue(getUserResponseMutable.value?.getUserValue?.apiKey)
                    val intent = Intent(view.context,HomeActivity::class.java)
                    view.context.startActivity(intent)
                    isFinished.value=true
                }else{
                    isLoading.value=false
                    Toast.makeText(view.context, "Hatalı Kullanıcı Adı veya Şifre!", Toast.LENGTH_LONG).show()
                }
           }
            else{
                Toast.makeText(view.context, "Kullanıcı adı ve şifre boş bırakılamaz!", Toast.LENGTH_SHORT).show()
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