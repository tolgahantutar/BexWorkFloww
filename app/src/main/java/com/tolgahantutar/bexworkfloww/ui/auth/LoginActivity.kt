package com.tolgahantutar.bexworkfloww.ui.auth


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.tolgahantutar.bexworkfloww.*
import com.tolgahantutar.bexworkfloww.databinding.ActivityLoginBinding
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
        authViewModel.isLoading.observe(this, Observer {
            if (it){
                progress_bar.visibility = View.VISIBLE
            }else{
                progress_bar.visibility = View.INVISIBLE
            }
        })
        authViewModel.getDomain()
        authViewModel.domainResult.observe(this, Observer {
            if(it){
               authViewModel.domainApiKey.observe(this, Observer {
                    Toast.makeText(this, authViewModel.domainApiKey.value, Toast.LENGTH_LONG).show()
                })
            }
        })
        val crashButton = Button(this)
        crashButton.text = "Crash!"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))
    }
}