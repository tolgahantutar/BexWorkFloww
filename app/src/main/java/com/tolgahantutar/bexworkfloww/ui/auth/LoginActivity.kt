package com.tolgahantutar.bexworkfloww.ui.auth


import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
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
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        binding.viewmodel=authViewModel
        sharedPreferences = SharedPrefSingleton.getSharedPreferences(applicationContext)
        checkApiKey = SharedPrefSingleton.getSomeStringValue(applicationContext).toString()
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
            SharedPrefSingleton.setSomeStringValue(applicationContext,it.getUserValue.apiKey)
        }
        })
        authViewModel.isFinished.observe(this, Observer {
            if (it){
                finish()
            }
        })

    }
}