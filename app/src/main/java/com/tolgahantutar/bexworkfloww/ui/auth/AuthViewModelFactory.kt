package com.tolgahantutar.bexworkfloww.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tolgahantutar.bexworkfloww.data.network.repositories.AuthorizeSessionRepository

class AuthViewModelFactory(

    private val repository: AuthorizeSessionRepository
):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }


}