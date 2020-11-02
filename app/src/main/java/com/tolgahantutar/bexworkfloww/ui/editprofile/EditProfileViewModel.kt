package com.tolgahantutar.bexworkfloww.ui.editprofile


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgahantutar.bexworkfloww.data.network.repositories.GetUserRepository
import com.tolgahantutar.bexworkfloww.data.network.responses.GetUserResponse
import com.tolgahantutar.bexworkfloww.data.sharedpref.SharedPrefSingletonAuthID
import com.tolgahantutar.bexworkfloww.data.sharedpref.SharedPrefSingletonDomainKey
import com.tolgahantutar.bexworkfloww.validations.EmailValidation
import com.tolgahantutar.bexworkfloww.validations.PhoneValidation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditProfileViewModel @ViewModelInject constructor(
    private val getUserRepository: GetUserRepository,
    private val sharedPrefSingletonAuthID: SharedPrefSingletonAuthID,
    private val sharedPrefSingletonDomainKey: SharedPrefSingletonDomainKey
) : ViewModel() {
    val getUserResponseMutable = MutableLiveData<GetUserResponse>()
    private val isPhoneChanged = MutableLiveData<Boolean>(false)
    private val isEmailChanged = MutableLiveData<Boolean>(false)
    private val isAddressChanged = MutableLiveData<Boolean>(false)
    private val isWebAddressChanged = MutableLiveData<Boolean>(false)
    val isEnabled = MutableLiveData<Boolean>(false)
    fun executeUserResponse() {
            viewModelScope.launch {
                val getUserResponse: GetUserResponse = getUser(sharedPrefSingletonAuthID.getSomeStringValue(), "Bearer "+sharedPrefSingletonDomainKey.getSomeStringValue()!!)
                getUserResponseMutable.value = getUserResponse
            }

    }
    fun onPhoneTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if(PhoneValidation().checkPhoneNumber(s.toString())) {
            isPhoneChanged.value =
                s.toString() != getUserResponseMutable.value?.getUserValue?.contact?.defaultPhoneModel?.cleanBody
        }else{
           isPhoneChanged.value=false
        }
        isEnabled.value = isPhoneChanged.value==true||isEmailChanged.value==true||isAddressChanged.value==true||isWebAddressChanged.value==true
    }
    fun onEmailTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if(EmailValidation().checkEmail(s.toString())) {
            isEmailChanged.value =
                s.toString() != getUserResponseMutable.value?.getUserValue?.contact?.defaultEmail?.address
        }
        else{
            isEmailChanged.value=false
        }
        isEnabled.value = isPhoneChanged.value==true||isEmailChanged.value==true||isAddressChanged.value==true||isWebAddressChanged.value==true
    }
    fun onAddressTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        isAddressChanged.value = s.toString() != getUserResponseMutable.value?.getUserValue?.contact?.defaultAdressModel?.displayBody
        isEnabled.value = isPhoneChanged.value==true||isEmailChanged.value==true||isAddressChanged.value==true||isWebAddressChanged.value==true
    }
    fun onWebAddressTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        isWebAddressChanged.value = s.toString() != getUserResponseMutable.value?.getUserValue?.contact?.defaultWebAddressModel?.url
        isEnabled.value = isPhoneChanged.value==true||isEmailChanged.value==true||isAddressChanged.value==true||isWebAddressChanged.value==true
    }


    private suspend fun getUser(
        id: Int,
        authorization: String
    )= withContext(Dispatchers.IO){getUserRepository.getUser(id, authorization)}
}