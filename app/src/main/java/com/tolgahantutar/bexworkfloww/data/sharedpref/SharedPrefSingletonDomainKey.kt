package com.tolgahantutar.bexworkfloww.data.sharedpref

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefSingletonDomainKey @Inject constructor(
    @ApplicationContext context: Context
) {
    val prefs= PreferenceManager.getDefaultSharedPreferences(context)
    fun getSomeStringValue(): String? {
        return prefs.getString("domainKey", "notGenerated")
    }
    fun setSomeStringValue(newValue: String?) {
        prefs.edit().putString("domainKey", newValue).apply()
    }
}