package com.tolgahantutar.bexworkfloww.ui.addressbook

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgahantutar.bexworkfloww.data.network.repositories.getrepository.GetContactRepository
import com.tolgahantutar.bexworkfloww.data.network.responses.getresponses.GetContactResponse
import com.tolgahantutar.bexworkfloww.data.sharedpref.SharedPrefSingletonUserAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdressBookViewModel @ViewModelInject constructor(
    private val getContactRepository: GetContactRepository,
    private val sharedPrefSingletonUserAPI: SharedPrefSingletonUserAPI
): ViewModel(){
    val mutableGetContactResponse = MutableLiveData<GetContactResponse>()
    fun executeGetContact() {
            viewModelScope.launch {
                val getContactResponse = suspendGetContact(2, "Bearer " + sharedPrefSingletonUserAPI.getSomeStringValue())
                if (getContactResponse.result) {
                    mutableGetContactResponse.value = getContactResponse
                }
            }
    }
suspend fun suspendGetContact(
    addressBookId : Int,
    authorization : String
)= withContext(Dispatchers.IO){getContactRepository.getContact(addressBookId,authorization)}
}