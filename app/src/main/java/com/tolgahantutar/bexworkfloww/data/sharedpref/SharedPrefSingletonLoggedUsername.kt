package com.tolgahantutar.bexworkfloww.data.sharedpref

import android.content.Context
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SharedPrefSingletonLoggedUsername @Inject constructor(
    @ApplicationContext context: Context
) {
    val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    fun getSomeStringValue(): String? {
        return prefs.getString("LoggedUsername", "none")
    }
    fun setSomeStringValue( newValue: String) {
        prefs.edit().putString("LoggedUsername", newValue).apply()
    }
}