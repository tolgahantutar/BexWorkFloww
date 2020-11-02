package com.tolgahantutar.bexworkfloww.ui.editprofile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolgahantutar.bexworkfloww.data.network.repositories.GetUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.lifecycle.viewModelScope
import com.tolgahantutar.bexworkfloww.data.network.responses.GetUserResponse
import kotlinx.coroutines.launch

class EditProfileViewModel @ViewModelInject constructor(
    private val getUserRepository: GetUserRepository
) : ViewModel() {
    val getUserResponseMutable = MutableLiveData<GetUserResponse>()

    fun getUserResponse(id:Int,apiKey:String){
        viewModelScope.launch {
            val getUserResponse = getUser(id,apiKey)
            getUserResponseMutable.value=getUserResponse
        }
    }
    suspend fun getUser(
        id: Int,
        authorization : String
    )= withContext(Dispatchers.Main){getUserRepository.getUser(id,authorization)}
}