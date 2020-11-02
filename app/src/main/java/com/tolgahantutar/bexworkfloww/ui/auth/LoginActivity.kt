package com.tolgahantutar.bexworkfloww.ui.auth


import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.tolgahantutar.bexworkfloww.*
import com.tolgahantutar.bexworkfloww.databinding.ActivityLoginBinding
import com.tolgahantutar.bexworkfloww.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(){
    private lateinit var binding : ActivityLoginBinding
    private val authViewModel : AuthViewModel by viewModels()
    private lateinit var checkApiKey : String
    private lateinit var sharedPreferencesUserAPI: SharedPreferences
    private lateinit var sharedPreferencesDomainKey: SharedPreferences
    private lateinit var sharedPreferencesAuthID: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        binding.viewmodel=authViewModel
        sharedPreferencesUserAPI = SharedPrefSingletonUserAPI.getSharedPreferences(applicationContext)
        sharedPreferencesDomainKey = SharedPrefSingletonDomainKey.getSharedPreferences(applicationContext)
        sharedPreferencesAuthID = SharedPrefSingletonAuthID.getSharedPreferences(applicationContext)
        checkApiKey = SharedPrefSingletonUserAPI.getSomeStringValue(applicationContext).toString()
        if (checkApiKey!="notGenerated"){
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        authViewModel.isLoading.observe(this, Observer {
            if (it){
                progress_bar.visibility = View.VISIBLE
            }else{
                progress_bar.visibility = View.INVISIBLE
            }
        })
        authViewModel.getUserResponseMutable.observe(this, Observer {
        if (it.result){
            SharedPrefSingletonUserAPI.setSomeStringValue(applicationContext,it.getUserValue.apiKey)
        }
        })
        authViewModel.isFinished.observe(this, Observer {
            if (it){
                finish()
            }
        })
        authViewModel.isAuthExists.observe(this, Observer {
            if(it){
                authViewModel.isAuthExists.value=false
                authViewModel.authID.observe(this, Observer {
                    SharedPrefSingletonAuthID.setSomeStringValue(applicationContext,it)
                })
            }
        })
        authViewModel.isDomainExists.observe(this, Observer {
            if(it){
                authViewModel.isDomainExists.value=false
                authViewModel.domainKey.observe(this, Observer {
                   SharedPrefSingletonDomainKey.setSomeStringValue(applicationContext,it)
                })
            }
        })

    }
}