package com.tolgahantutar.bexworkfloww.ui.auth


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.databinding.ActivityLoginBinding
import com.tolgahantutar.bexworkfloww.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(){
    private lateinit var binding : ActivityLoginBinding
    private val authViewModel : AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        binding.viewmodel=authViewModel
        authViewModel.checkApiKey()
        authViewModel.isCheckTrue.observe(this, Observer {
            if(it){
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
        authViewModel.isLoading.observe(this, Observer {
            if (it){
                progress_bar.visibility = View.VISIBLE
            }else{
                progress_bar.visibility = View.INVISIBLE
            }
        })
        authViewModel.isFinished.observe(this, Observer {
            if (it){
                finish()
            }
        })
    }
}