package com.tolgahantutar.bexworkfloww.ui.editprofile


import android.view.View
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgahantutar.bexworkfloww.data.network.repositories.GetUserRepository
import com.tolgahantutar.bexworkfloww.data.network.repositories.UpdateProfileRepository
import com.tolgahantutar.bexworkfloww.data.network.responses.GetUserResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.UpdateMailResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.UpdatePhoneResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.UpdateWebAddressResponse
import com.tolgahantutar.bexworkfloww.data.sharedpref.SharedPrefSingletonAuthID
import com.tolgahantutar.bexworkfloww.data.sharedpref.SharedPrefSingletonDomainKey
import com.tolgahantutar.bexworkfloww.validations.EmailValidation
import com.tolgahantutar.bexworkfloww.validations.PhoneValidation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditProfileViewModel @ViewModelInject constructor(
    private val getUserRepository: GetUserRepository,
    private val updateProfileRepository: UpdateProfileRepository,
    private val sharedPrefSingletonAuthID: SharedPrefSingletonAuthID,
    private val sharedPrefSingletonDomainKey: SharedPrefSingletonDomainKey
) : ViewModel() {
    val getUserResponseMutable = MutableLiveData<GetUserResponse>()
    private val isPhoneChanged = MutableLiveData<Boolean>(false)
    private val isEmailChanged = MutableLiveData<Boolean>(false)
    private val isAddressChanged = MutableLiveData<Boolean>(false)
    private val isWebAddressChanged = MutableLiveData<Boolean>(false)
    //Email
    private var emailContactID: Int = 0
    private var emailPriority : Int = 0
    private var emailID : Int = 0
    //Phone
    private var phoneContactID: Int = 0
    private var phonePriority : Int = 0
    private var phoneID : Int = 0
    val isEnabled = MutableLiveData<Boolean>(false)
    //Web Address
    private var webContactID: Int = 0
    private var webPriority: Int = 0
    private var webID: Int = 0
    fun executeUserResponse() {
            viewModelScope.launch {
                val getUserResponse: GetUserResponse = getUser(sharedPrefSingletonAuthID.getSomeStringValue(), "Bearer "+sharedPrefSingletonDomainKey.getSomeStringValue()!!)
                getUserResponseMutable.value = getUserResponse
                //Email
                emailContactID = getUserResponseMutable.value!!.getUserValue.contact.defaultEmail.contactId.toInt()
                emailPriority = getUserResponseMutable.value!!.getUserValue.contact.defaultEmail.priority.toInt()
                emailID = getUserResponseMutable.value!!.getUserValue.contact.defaultEmail.id.toInt()
                //Phone
                phoneContactID = getUserResponseMutable.value!!.getUserValue.contact.defaultPhoneModel.contactId.toInt()
                phonePriority = getUserResponseMutable.value!!.getUserValue.contact.defaultPhoneModel.priority.toInt()
                phoneID = getUserResponseMutable.value!!.getUserValue.contact.defaultPhoneModel.id.toInt()
                //Web Address
                webContactID = getUserResponseMutable.value!!.getUserValue.contact.defaultWebAddressModel.contactId.toInt()
                webPriority = getUserResponseMutable.value!!.getUserValue.contact.defaultWebAddressModel.priority.toInt()
                webID = getUserResponseMutable.value!!.getUserValue.contact.defaultWebAddressModel.id.toInt()
            }
    }
    fun onClickSaveButton(email: String,phone:String,webAddress:String,view: View){
        viewModelScope.launch {
            if(isEmailChanged.value==true){
                val updateMailResponse: UpdateMailResponse = updateMail(emailContactID,email,emailPriority,emailID)
                if (updateMailResponse.result){
                    Toast.makeText(view.context, "Email Updated Successfully", Toast.LENGTH_SHORT).show()
                }
            }
            if(isPhoneChanged.value==true){

                val updatePhoneResponse: UpdatePhoneResponse = updatePhone(phoneContactID,phonePriority,"PhoneNumber","+90",
                    phone.substring(2),"","","Telefon : +90 "+phone.substring(2),
                phone,0,"","2020-11-05T08:47:08.246Z",phone,phoneID)
                if(updatePhoneResponse.result){
                    Toast.makeText(view.context, "Phone Updated Successfully", Toast.LENGTH_SHORT).show()
                }
            }
            if(isWebAddressChanged.value==true){
                val updateWebAddressResponse : UpdateWebAddressResponse = updateWebAddress(webContactID,webAddress,webPriority,webID)
                if(updateWebAddressResponse.result){
                    Toast.makeText(view.context, "Web Address Updated Successfully", Toast.LENGTH_SHORT).show()
                }
            }
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

    private suspend fun updateMail(
    contactID: Int,
    address: String,
    priority: Int,
    id: Int
    )= withContext(Dispatchers.IO){updateProfileRepository.updateMail(contactID,address,priority,id)}

    private suspend fun updatePhone(
        contactID: Int,
        priority: Int,
        type: String,
        countryCode: String,
        number: String,
        extension: String,
        availability: String,
        displayBody: String,
        cleanBody: String,
        isVerified: Int,
        verificationCode: String,
        verificationDate: String,
        basicPhoneNumber: String,
        id: Int
    )= withContext(Dispatchers.IO){updateProfileRepository.updatePhone(contactID,priority,type,countryCode,number,extension,availability,displayBody,cleanBody,
        isVerified,verificationCode,verificationDate,basicPhoneNumber,id)}

    private suspend fun updateWebAddress(
        contactID: Int,
        url: String,
        priority: Int,
        id: Int
    )= withContext(Dispatchers.IO){updateProfileRepository.updateWebAddress(contactID,url,priority, id)}
}