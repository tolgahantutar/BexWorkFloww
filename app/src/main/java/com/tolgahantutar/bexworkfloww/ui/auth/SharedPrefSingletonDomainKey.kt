package com.tolgahantutar.bexworkfloww.ui.auth

import android.content.Context
import android.content.SharedPreferences

object SharedPrefSingletonDomainKey {
    fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("KEY_PREFERENCE", Context.MODE_PRIVATE)
    }
    fun getSomeStringValue(context: Context?): String? {
        return SharedPrefSingletonUserAPI.getSharedPreferences(context!!).getString("domainKey", "notGenerated")
    }
    fun setSomeStringValue(context: Context?, newValue: String?) {
        val editor = SharedPrefSingletonUserAPI.getSharedPreferences(context!!).edit()
        editor.putString("domainKey", newValue)
        editor.apply()
    }
}