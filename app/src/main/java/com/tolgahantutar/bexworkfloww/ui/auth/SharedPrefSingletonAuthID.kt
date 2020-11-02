package com.tolgahantutar.bexworkfloww.ui.auth

import android.content.Context
import android.content.SharedPreferences

object SharedPrefSingletonAuthID {
    fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("KEY_PREFERENCE", Context.MODE_PRIVATE)
    }
    fun getSomeStringValue(context: Context?): Int {
        return SharedPrefSingletonUserAPI.getSharedPreferences(context!!).getInt("AuthID", 0)
    }
    fun setSomeStringValue(context: Context?, newValue: Int) {
        val editor = SharedPrefSingletonUserAPI.getSharedPreferences(context!!).edit()
        editor.putInt("AuthID", newValue)
        editor.apply()
    }
}