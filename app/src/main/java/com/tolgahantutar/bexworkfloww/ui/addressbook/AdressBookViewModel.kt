package com.tolgahantutar.bexworkfloww.ui.addressbook

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgahantutar.bexworkfloww.data.network.repositories.GetContactRepository
import com.tolgahantutar.bexworkfloww.data.network.responses.GetContactResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdressBookViewModel @ViewModelInject constructor(
private val getContactRepository: GetContactRepository
): ViewModel(){
    val isSuccessfull= MutableLiveData<Boolean>()
    val mutableGetContactResponse = MutableLiveData<GetContactResponse>()
       fun getContact(addressBookId: Int,authorization: String?){
           viewModelScope.launch {
               val getContactResponse = suspendGetContact(addressBookId,"Bearer "+authorization)
               if(getContactResponse.result){
                   mutableGetContactResponse.value=getContactResponse
               }
           }
       }

suspend fun suspendGetContact(
    addressBookId : Int,
    authorization : String
)= withContext(Dispatchers.IO){getContactRepository.getContact(addressBookId,authorization)}
}