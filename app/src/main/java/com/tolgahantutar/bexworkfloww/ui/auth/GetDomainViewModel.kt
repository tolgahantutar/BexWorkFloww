package com.tolgahantutar.bexworkfloww.ui.auth

import androidx.lifecycle.ViewModel
import com.tolgahantutar.bexworkfloww.data.network.repositories.GetDomainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetDomainViewModel (
    private val repository: GetDomainRepository
):ViewModel(){
    suspend fun getDomain(
        Location: String
    )= withContext(Dispatchers.IO){repository.getDomain(Location)}
}