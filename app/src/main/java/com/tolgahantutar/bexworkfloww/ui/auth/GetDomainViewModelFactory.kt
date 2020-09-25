package com.tolgahantutar.bexworkfloww.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tolgahantutar.bexworkfloww.data.network.repositories.GetDomainRepository

class GetDomainViewModelFactory (
    private val repository: GetDomainRepository
):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GetDomainViewModel(repository) as T
            }
}