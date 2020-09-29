package com.tolgahantutar.bexworkfloww.ui.auth

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgahantutar.bexworkfloww.data.network.repositories.AuthorizeSessionRepository
import com.tolgahantutar.bexworkfloww.ui.home.HomeActivity
import com.tolgahantutar.bexworkfloww.util.ApiException
import com.tolgahantutar.bexworkfloww.util.NoInternetException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel (
    private val repository: AuthorizeSessionRepository

):ViewModel() {


   var userName :String?=null
    var password: String ? = null
    val isLoading = MutableLiveData<Boolean>()

    fun onClickUserLogin(view: View){
        val sessionID = 0
        val authorityID = 0
        val loginType = "System"


        viewModelScope.launch {
                if(!(userName==null||password==null)){
                isLoading.value = true
            try{
                 val authResponse = userLogin(sessionID,authorityID,userName!!,password!!,loginType)
                if(authResponse.Result){
                    isLoading.value=false
                    Toast.makeText(view.context, "Login Successfull", Toast.LENGTH_LONG).show()
                    val intent = Intent(view.context,HomeActivity::class.java)
                    view.context.startActivity(intent)
                }else{
                    isLoading.value=false
                    Toast.makeText(view.context, "Login Failed!!", Toast.LENGTH_LONG).show()
                }
            }catch (e: ApiException){
                e.printStackTrace()
            }catch (e: NoInternetException){
                e.printStackTrace()
            }}
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
)= withContext(Dispatchers.IO){repository.userLogin(SessionID, AuthorityID, UserName, Password, LoginType)}
}