package com.tolgahantutar.bexworkfloww.ui.auth

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.tolgahantutar.bexworkfloww.data.models.GetDomainModel
import com.tolgahantutar.bexworkfloww.data.network.repositories.GetDomainRepository
import com.tolgahantutar.bexworkfloww.data.network.responses.GetDomainResponse
import com.tolgahantutar.bexworkfloww.util.ApiException
import com.tolgahantutar.bexworkfloww.util.NoInternetException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class GetDomainViewModel (
    private val repository: GetDomainRepository
):ViewModel(){
    val domainResult = MutableLiveData<Boolean>()
    val domainApiKey = MutableLiveData<String?>()
     fun getDomain(){
        val location = "bexfatest.saasteknoloji.com"
        viewModelScope.launch {
            try{
                val domainResponse=getDomain(location)
                domainResult.value = domainResponse.Result
                domainApiKey.value = domainResponse.getDomainModel.ApiKey
            }catch (e: ApiException){
                e.printStackTrace()
            }catch (e: NoInternetException){
                e.printStackTrace()
            }
        }
    }

    suspend fun getDomain(
        Location: String
    )= withContext(Dispatchers.IO){repository.getDomain(Location)}
}