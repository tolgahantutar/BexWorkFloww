package com.tolgahantutar.bexworkfloww.data.sharedpref

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefSingletonAuthID @Inject constructor(
    @ApplicationContext context: Context
) {
    val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    fun getSomeStringValue(): Int {
        return prefs.getInt("AuthID", 0)
    }
    fun setSomeStringValue( newValue: Int) {
        prefs.edit().putInt("AuthID", newValue).apply()
    }
}