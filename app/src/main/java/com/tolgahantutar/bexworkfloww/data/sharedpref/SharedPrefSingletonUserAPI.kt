package com.tolgahantutar.bexworkfloww.data.sharedpref

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefSingletonUserAPI @Inject constructor(
    @ApplicationContext context: Context
){
    val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun getSomeStringValue(): String? {
        return prefs.getString("apiKey", "notGenerated")
    }
    fun setSomeStringValue(newValue: String?) {
         prefs.edit().putString("apiKey", newValue).apply()
    }
}