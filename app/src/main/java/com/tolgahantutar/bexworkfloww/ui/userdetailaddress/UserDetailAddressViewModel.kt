package com.tolgahantutar.bexworkfloww.ui.userdetailaddress

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgahantutar.bexworkfloww.data.network.repositories.getrepository.GetAllAddressRepository
import com.tolgahantutar.bexworkfloww.data.network.repositories.getrepository.GetAllEmailsRepository
import com.tolgahantutar.bexworkfloww.data.network.repositories.getrepository.GetAllPhonesRepository
import com.tolgahantutar.bexworkfloww.data.network.repositories.getrepository.GetAllWebAddressRepository
import com.tolgahantutar.bexworkfloww.data.network.responses.getresponses.GetAllAddressResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.getresponses.GetAllEmailsResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.getresponses.GetAllPhonesResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.getresponses.GetAllWebAddressResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserDetailAddressViewModel @ViewModelInject constructor(
    private val getAllPhonesRepository: GetAllPhonesRepository,
    private val getAllAddressRepository: GetAllAddressRepository,
    private val getAllWebAddressRepository: GetAllWebAddressRepository,
    private val getAllEmailsRepository: GetAllEmailsRepository
) : ViewModel() {
    val getAllEmailResponseMutable = MutableLiveData<GetAllEmailsResponse>()
    val getAllPhoneResponseMutable = MutableLiveData<GetAllPhonesResponse>()
    val getAllAddressResponseMutable = MutableLiveData<GetAllAddressResponse>()
    val getAllWebAddressResponseMutable = MutableLiveData<GetAllWebAddressResponse>()

fun getAllInfo(emailContactID: Int,phoneContactID: Int,addressContactID: Int,webAddressContactID: Int){
    viewModelScope.launch {
        val getAllEmailsResponse: GetAllEmailsResponse = getAllEmailsAsync(emailContactID,-1,0)
        if(getAllEmailsResponse.result){
            getAllEmailResponseMutable.value = getAllEmailsResponse
        }
        val getAllPhoneResponse: GetAllPhonesResponse = getAllPhonesAsync(phoneContactID,-1,0)
        if(getAllPhoneResponse.result){
            getAllPhoneResponseMutable.value = getAllPhoneResponse
        }
        val getAllAddressResponse: GetAllAddressResponse = getAllAddressAsync(addressContactID,-1,0)
        if(getAllAddressResponse.result){
            getAllAddressResponseMutable.value = getAllAddressResponse
        }
        val getAllWebAddressResponse: GetAllWebAddressResponse = getAllWebAddressAsync(webAddressContactID,-1,0)
        if(getAllWebAddressResponse.result){
            getAllWebAddressResponseMutable.value = getAllWebAddressResponse
        }
    }
}
fun getAllEmail(emailContactID: Int){
    viewModelScope.launch {
        val getAllEmailsResponse: GetAllEmailsResponse = getAllEmailsAsync(emailContactID,-1,0)
        if(getAllEmailsResponse.result){
            getAllEmailResponseMutable.value = getAllEmailsResponse
        }
    }
}
    fun getAllPhone(phoneContactID: Int){
        viewModelScope.launch {
            val getAllPhoneResponse: GetAllPhonesResponse = getAllPhonesAsync(phoneContactID,-1,0)
            if(getAllPhoneResponse.result){
                getAllPhoneResponseMutable.value = getAllPhoneResponse
            }
        }
    }
    fun getAllAddress(addressContactID: Int){
        viewModelScope.launch {
            val getAllAddressResponse: GetAllAddressResponse = getAllAddressAsync(addressContactID,-1,0)
            if(getAllAddressResponse.result){
                getAllAddressResponseMutable.value = getAllAddressResponse
            }
        }
    }
    fun getAllWebAddress(webAddressContactID: Int){
        viewModelScope.launch {
            val getAllWebAddressResponse: GetAllWebAddressResponse = getAllWebAddressAsync(webAddressContactID,-1,0)
            if(getAllWebAddressResponse.result){
                getAllWebAddressResponseMutable.value = getAllWebAddressResponse
            }
        }
    }


    private suspend fun getAllEmailsAsync(
        contactID: Int,
        page: Int,
        pageSize: Int
    )= withContext(Dispatchers.IO){getAllEmailsRepository.getAllEmails(contactID,page,pageSize)}

    private suspend fun getAllPhonesAsync(
        contactID: Int,
        page: Int,
        pageSize: Int
    ) = withContext(Dispatchers.IO){getAllPhonesRepository.getAllPhones(contactID, page, pageSize)}

    private suspend fun getAllAddressAsync(
        contactID: Int,
        page: Int,
        pageSize: Int
    )= withContext(Dispatchers.IO){getAllAddressRepository.getAllAddress(contactID, page, pageSize)}

    private suspend fun getAllWebAddressAsync(
        contactID: Int,
        page: Int,
        pageSize: Int
    )= withContext(Dispatchers.IO){getAllWebAddressRepository.getAllWebAddress(contactID, page, pageSize)}
}