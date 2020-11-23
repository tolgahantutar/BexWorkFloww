package com.tolgahantutar.bexworkfloww.ui.settings

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.tolgahantutar.bexworkfloww.data.sharedpref.SharedPrefSingletonUserAPI

class SettingsViewModel @ViewModelInject constructor(
    private val sharedPrefSingletonUserAPI: SharedPrefSingletonUserAPI
) : ViewModel() {
    fun cleanUserApi(){
        sharedPrefSingletonUserAPI.setSomeStringValue("notGenerated")
    }
}