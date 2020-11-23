package com.tolgahantutar.bexworkfloww.ui.editprofile


import android.content.Context
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgahantutar.bexworkfloww.data.models.requestbodies.*
import com.tolgahantutar.bexworkfloww.data.network.repositories.createrepository.CreateContactRepository
import com.tolgahantutar.bexworkfloww.data.network.repositories.deleterepository.DeleteContactRepository
import com.tolgahantutar.bexworkfloww.data.network.repositories.getrepository.*
import com.tolgahantutar.bexworkfloww.data.network.repositories.updaterepository.UpdateProfileRepository
import com.tolgahantutar.bexworkfloww.data.network.responses.createresponses.CreateAddressResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.createresponses.CreateMailResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.createresponses.CreatePhoneResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.createresponses.CreateWebAddressResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.deleteresponses.DeleteMailResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.deleteresponses.DeletePhoneResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.deleteresponses.DeleteWebAddressResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.getresponses.*
import com.tolgahantutar.bexworkfloww.data.network.responses.updateresponses.UpdateMailResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.updateresponses.UpdatePhoneResponse
import com.tolgahantutar.bexworkfloww.data.network.responses.updateresponses.UpdateWebAddressResponse
import com.tolgahantutar.bexworkfloww.data.sharedpref.SharedPrefSingletonAuthID
import com.tolgahantutar.bexworkfloww.data.sharedpref.SharedPrefSingletonDomainKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditProfileViewModel @ViewModelInject constructor(
    private val getUserRepository: GetUserRepository,
    private val updateProfileRepository: UpdateProfileRepository,
    private val createContactRepository: CreateContactRepository,
    private val deleteContactRepository: DeleteContactRepository,
    private val getAllEmailsRepository: GetAllEmailsRepository,
    private val getAllPhonesRepository: GetAllPhonesRepository,
    private val getAllAddressRepository: GetAllAddressRepository,
    private val getAllWebAddressRepository: GetAllWebAddressRepository,
    private val getAllCountryRepository: GetAllCountryRepository,
    private val getAllCityRepository: GetAllCityRepository,
    private val getAllCountyRepository: GetAllCountyRepository,
    private val sharedPrefSingletonAuthID: SharedPrefSingletonAuthID,
    private val sharedPrefSingletonDomainKey: SharedPrefSingletonDomainKey
) : ViewModel() {
    val isRefreshFragment= MutableLiveData<Boolean>(false)

    val getUserResponseMutable = MutableLiveData<GetUserResponse>()
    val getAllEmailResponseMutable = MutableLiveData<GetAllEmailsResponse>()
    val getAllPhonesResponseMutable = MutableLiveData<GetAllPhonesResponse>()
    val getAllAddressResponseMutable = MutableLiveData<GetAllAddressResponse>()
    val getAllWebAddressResponseMutable = MutableLiveData<GetAllWebAddressResponse>()
    val getAllCountryResponseMutable = MutableLiveData<GetAllCountryResponse>()
    val getAllCityResponseMutable = MutableLiveData<GetAllCityResponse>()
    val getAllCountyResponseMutable = MutableLiveData<GetAllCountyResponse>()
    val isProgressBarDismissed = MutableLiveData<Boolean>(false)
    val createAddressResponseMutable = MutableLiveData<CreateAddressResponse>()
    //Email
    private var emailContactID: Int = 0
    private var emailPriority : Int = 0
    private var emailID : Int = 0
    //Phone
    private var phoneContactID: Int = 0
    private var phonePriority : Int = 0
    private var phoneID : Int = 0
    //Web Address
    private var webContactID: Int = 0
    private var webPriority: Int = 0
    private var webID: Int = 0
    //Address
    private var addressContactID: Int = 0
    private var addressPriority: Int = 0
    private var addressID: Int = 0

    fun executeUserResponse() {
            viewModelScope.launch {
                val getUserResponse: GetUserResponse = getUser(sharedPrefSingletonAuthID.getSomeStringValue(), "Bearer "+sharedPrefSingletonDomainKey.getSomeStringValue()!!)
                getUserResponseMutable.value = getUserResponse
                //Email
                emailContactID = getUserResponseMutable.value!!.getUserValue.contact.relatedEntityId.toInt()
                emailPriority = getUserResponseMutable.value!!.getUserValue.contact.defaultEmail.priority.toInt()
                emailID = getUserResponseMutable.value!!.getUserValue.contact.defaultEmail.id.toInt()
                //Phone
                phoneContactID = getUserResponseMutable.value!!.getUserValue.contact.relatedEntityId.toInt()
                phonePriority = getUserResponseMutable.value!!.getUserValue.contact.defaultPhoneModel.priority.toInt()
                phoneID = getUserResponseMutable.value!!.getUserValue.contact.defaultPhoneModel.id.toInt()
                //Web Address
                webContactID = getUserResponseMutable.value!!.getUserValue.contact.relatedEntityId.toInt()
                webPriority = getUserResponseMutable.value!!.getUserValue.contact.defaultWebAddressModel.priority.toInt()
                webID = getUserResponseMutable.value!!.getUserValue.contact.defaultWebAddressModel.id.toInt()
                //Address
                addressContactID = getUserResponseMutable.value!!.getUserValue.contact.relatedEntityId.toInt()
                addressPriority = getUserResponseMutable.value!!.getUserValue.contact.defaultAdressModel.priority.toInt()
                addressID = getUserResponseMutable.value!!.getUserValue.contact.defaultAdressModel.id.toInt()

                if(getUserResponse.result){
                    val getAllEmailsResponse: GetAllEmailsResponse = getAllEmailsAsync(emailContactID,-1,0)
                    if(getAllEmailsResponse.result){
                        getAllEmailResponseMutable.value = getAllEmailsResponse
                    }
                    val getAllPhonesResponse: GetAllPhonesResponse = getAllPhonesAsync(phoneContactID,-1,0)
                    if (getAllPhonesResponse.result){
                        getAllPhonesResponseMutable.value = getAllPhonesResponse
                    }
                    val getAllAddressResponse: GetAllAddressResponse = getAllAddressAsync(addressContactID,-1,0)
                    if(getAllAddressResponse.result){
                        getAllAddressResponseMutable.value = getAllAddressResponse
                    }
                    val getAllWebAddressResponse: GetAllWebAddressResponse = getAllWebAddressAsync(webContactID,-1,0)
                    if(getAllWebAddressResponse.result){
                        getAllWebAddressResponseMutable.value = getAllWebAddressResponse
                    }
                    val getAllCountryResponse: GetAllCountryResponse = getAllCountryAsync(2, 0, -1)
                    getAllCountryResponseMutable.value = getAllCountryResponse

                    val getAllCityResponse: GetAllCityResponse = getAllCityAsync(1,0,0)
                    getAllCityResponseMutable.value = getAllCityResponse
                    isProgressBarDismissed.value=true
                    isProgressBarDismissed.value=false
                }
            }
    }

    fun getAllCounty(parentID: Int){
        viewModelScope.launch {
                val getAllCountyResponse: GetAllCountyResponse = getAllCountyAsync(parentID,0,0)
                if(getAllCountyResponse.result) {
                    getAllCountyResponseMutable.value = getAllCountyResponse
                }

        }
    }
    fun createAddress(context: Context,addressBookID: Int,countryName: String,countryID:Int,cityID: Int,countyName:String,countyID: Int,
    cityName:String,body:String,priority: Int){
        val createAddressBody = CreateAddressBody(addressContactID,
            RequestBodyCountry(addressBookID,countryName,countryID), RequestBodyCounty
        (cityID,countyName,countyID), RequestBodyState
        (countryID,"",0),RequestBodyCity
        (countryID,cityName,cityID),body,0,0,0,0,0,"",priority,"",0
        )
        viewModelScope.launch {
            val createAddressResponse: CreateAddressResponse = createAddress(createAddressBody)
            createAddressResponseMutable.value = createAddressResponse
            if(createAddressResponse.result){
                Toast.makeText(context, "Address Created Successfully", Toast.LENGTH_SHORT).show()
            }

        }
    }

        fun deleteAddress(context: Context,addressBookID: Int,countryName: String,countryID:Int,cityID: Int,countyName:String,countyID: Int,
                          cityName:String,body:String,priority: Int,id:Int){
            val deleteAddressBody = CreateAddressBody(addressContactID,
                RequestBodyCountry(addressBookID,countryName,countryID), RequestBodyCounty
                    (cityID,countyName,countyID), RequestBodyState
                    (countryID,"",0),RequestBodyCity
                    (countryID,cityName,cityID),body,0,0,0,0,0,"",priority,"",id
            )
            viewModelScope.launch {
                val deleteAddressResponse: CreateAddressResponse = deleteAddress(deleteAddressBody)
                if(deleteAddressResponse.result){
                    Toast.makeText(context, "Address Deleted Successfully", Toast.LENGTH_SHORT).show()
                }
            }
        }

    fun updateAddress(context: Context,addressBookID: Int,countryName: String,countryID:Int,cityID: Int,countyName:String,countyID: Int,
                      cityName:String,body:String,priority: Int,id:Int){
        val updateAddressBody = CreateAddressBody(addressContactID,
            RequestBodyCountry(addressBookID,countryName,countryID), RequestBodyCounty
                (cityID,countyName,countyID), RequestBodyState
                (countryID,"",0),RequestBodyCity
                (countryID,cityName,cityID),body,0,0,0,0,0,"",priority,"",id
        )
        viewModelScope.launch {
            val updateAddressResponse: CreateAddressResponse = updateAddress(updateAddressBody)
            if(updateAddressResponse.result){
                Toast.makeText(context, "Address Updated Successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateMailFromMailEditText(mail: String,priority: Int,id:Int,context: Context){
        viewModelScope.launch {

            val updateMailResponse: UpdateMailResponse = updateMail(emailContactID,mail,priority,id)
            if (updateMailResponse.result){
                Toast.makeText(context, "Email Updated Successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun deleteMailFromMailEditText(mail: String,priority: Int,id:Int,context: Context){
        viewModelScope.launch {
            val deleteMailResponse: DeleteMailResponse = deleteMail(emailContactID,mail,priority,id)
            if (deleteMailResponse.result){
                Toast.makeText(context, "Email Deleted Successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun createMailFromMailEditText(mail: String,priority: Int,context: Context){
        viewModelScope.launch {
            val createMailResponse: CreateMailResponse = createMail(emailContactID,mail,priority,0)
            if (createMailResponse.result){
                Toast.makeText(context, "Email Created Successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun updatePhoneFromMailEditText(phone: String,priority: Int,id:Int,context: Context){
        viewModelScope.launch {
            val updatePhoneResponse: UpdatePhoneResponse = updatePhone(phoneContactID,priority,"PhoneNumber","+90",
                phone.substring(2),"","","Telefon : +90 "+phone.substring(2),
                phone,0,"","2020-11-05T08:47:08.246Z",phone,id)
            if (updatePhoneResponse.result){
                Toast.makeText(context, "Phone Updated Successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun deletePhoneFromPhoneEditText(phone: String,priority: Int,id:Int,context: Context){
        viewModelScope.launch {
            val deletePhoneResponse: DeletePhoneResponse = deletePhone(phoneContactID,priority,"PhoneNumber","+90",
                phone.substring(2),"","","Telefon : +90 "+phone.substring(2),
                phone,0,"","2020-11-05T08:47:08.246Z",phone,id)
            if (deletePhoneResponse.result){
                Toast.makeText(context, "Phone Deleted Successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun createPhoneFromPhoneEditText(phone: String,priority: Int,context: Context){
        viewModelScope.launch {
            val createPhoneResponse: CreatePhoneResponse = createPhone(phoneContactID,priority,"PhoneNumber","+90",
                phone.substring(2),"","","Telefon : +90 "+phone.substring(2),
                phone,0,"","2020-11-05T08:47:08.246Z",phone,0)
            if(createPhoneResponse.result){
                Toast.makeText(context, "Phone Created Successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun updateWebAddressFromWebAddressEditText(url: String,priority: Int,id:Int,context: Context){
        viewModelScope.launch {
            val updateWebAddressResponse : UpdateWebAddressResponse = updateWebAddress(webContactID,url,priority,id)
            if(updateWebAddressResponse.result){
                Toast.makeText(context, "Web Address Updated Successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun deleteWebAddressFromWebAddressEditText(url: String,priority: Int,id:Int,context: Context){
        viewModelScope.launch {
            val deleteWebAddressResponse : DeleteWebAddressResponse = deleteWebAddress(webContactID,url,priority,id)
            if(deleteWebAddressResponse.result){
                Toast.makeText(context, "Web Address Deleted Successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun createWebAddressFromWebAddressEditText(url: String,priority: Int,context: Context){
        viewModelScope.launch {
            val createWebAddressResponse: CreateWebAddressResponse = createWebAddress(webContactID,url,priority,0)
            if(createWebAddressResponse.result){
                Toast.makeText(context, "Web Address Created Successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private suspend fun getUser(
        id: Int,
        authorization: String
    )= withContext(Dispatchers.Main){getUserRepository.getUser(id, authorization)}

    private suspend fun createMail(
        contactID: Int,
        address: String,
        priority: Int,
        id: Int
    )= withContext(Dispatchers.IO){createContactRepository.createMail(contactID, address, priority, id)}

    private suspend fun updateMail(
    contactID: Int,
    address: String,
    priority: Int,
    id: Int
    )= withContext(Dispatchers.IO){updateProfileRepository.updateMail(contactID,address,priority,id)}

    private suspend fun deleteMail(
        contactID: Int,
        address: String,
        priority: Int,
        id: Int
    ) = withContext(Dispatchers.IO){deleteContactRepository.deleteMail(contactID,address, priority, id)}
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

    private suspend fun deletePhone(
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
    ) = withContext(Dispatchers.IO){deleteContactRepository.deletePhone(contactID,priority,type,countryCode,number,extension,availability,displayBody,cleanBody,
        isVerified,verificationCode,verificationDate,basicPhoneNumber,id)}

    private suspend fun createPhone(
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
    ) = withContext(Dispatchers.IO){createContactRepository.createPhone(contactID,priority,type,countryCode,number,extension,availability,displayBody,cleanBody,
        isVerified,verificationCode,verificationDate,basicPhoneNumber,id)}

    private suspend fun updateWebAddress(
        contactID: Int,
        url: String,
        priority: Int,
        id: Int
    )= withContext(Dispatchers.IO){updateProfileRepository.updateWebAddress(contactID,url,priority, id)}

    private suspend fun deleteWebAddress(
        contactID: Int,
        url: String,
        priority: Int,
        id: Int
    ) = withContext(Dispatchers.IO){deleteContactRepository.deleteWebAddress(contactID, url, priority, id)}

    private suspend fun createWebAddress(
        contactID: Int,
        url: String,
        priority: Int,
        id: Int
    )= withContext(Dispatchers.IO){createContactRepository.createWebAddress(contactID, url, priority, id)}

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

    private suspend fun getAllCountryAsync(
        addressBookID: Int,
        page: Int,
        pageSize: Int
    ) = withContext(Dispatchers.IO){getAllCountryRepository.getAllCountry(addressBookID, page, pageSize)}

    private suspend fun getAllCityAsync(
        parentID: Int,
        page: Int,
        pageSize: Int
    ) = withContext(Dispatchers.IO){getAllCityRepository.getAllCity(parentID, page, pageSize)}

    private suspend fun getAllCountyAsync(
        parentID: Int,
        page: Int,
        pageSize: Int
    ) = withContext(Dispatchers.IO){getAllCountyRepository.getAllCounty(parentID, page, pageSize)}

    private suspend fun createAddress(
        createAddressBody: CreateAddressBody
    ) = withContext(Dispatchers.IO){createContactRepository.createAddress(createAddressBody)}

    private suspend fun deleteAddress(
        deleteAddressBody: CreateAddressBody
    ) = withContext(Dispatchers.IO){deleteContactRepository.deleteAddress(deleteAddressBody)}

    private suspend fun updateAddress(
        updateAddressBody: CreateAddressBody
    ) = withContext(Dispatchers.IO){updateProfileRepository.updateAddress(updateAddressBody)}
}