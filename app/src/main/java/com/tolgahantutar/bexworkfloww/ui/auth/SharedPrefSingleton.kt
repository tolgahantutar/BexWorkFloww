package com.tolgahantutar.bexworkfloww.ui.auth

import android.content.Context
import android.content.SharedPreferences

object SharedPrefSingleton{

     fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("KEY_PREFERENCE", Context.MODE_PRIVATE)
    }

    fun getSomeStringValue(context: Context?): String? {
        return getSharedPreferences(context!!).getString("apiKey", "notGenerated")
    }
    fun setSomeStringValue(context: Context?, newValue: String?) {
        val editor = getSharedPreferences(context!!).edit()
        editor.putString("apiKey", newValue)
        editor.apply()
    }

    /*companion object{
        val instance = SharedPrefSingleton()
    }*/
}