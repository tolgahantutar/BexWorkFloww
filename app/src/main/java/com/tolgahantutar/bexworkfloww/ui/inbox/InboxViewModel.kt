package com.tolgahantutar.bexworkfloww.ui.inbox

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.tolgahantutar.bexworkfloww.data.sharedpref.SharedPrefSingletonUserAPI

class InboxViewModel @ViewModelInject constructor(
    private val sharedPrefSingletonUserAPI: SharedPrefSingletonUserAPI
) : ViewModel() {
    fun cleanUserApi(){
        sharedPrefSingletonUserAPI.setSomeStringValue("notGenerated")
    }
}