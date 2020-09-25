package com.tolgahantutar.bexworkfloww.ui.auth

import androidx.lifecycle.ViewModel
import com.tolgahantutar.bexworkfloww.data.network.repositories.AuthorizeSessionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthViewModel (
    private val repository: AuthorizeSessionRepository

):ViewModel(){

suspend fun userLogin(
SessionID : Int,
AuthorityID: Int,
UserName: String,
Password : String,
LoginType: String
)= withContext(Dispatchers.IO){repository.userLogin(SessionID, AuthorityID, UserName, Password, LoginType)}
}