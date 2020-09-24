package com.tolgahantutar.bexworkfloww.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.databinding.ActivityLoginBinding
import com.tolgahantutar.bexworkfloww.ui.home.HomeActivity
import com.tolgahantutar.bexworkfloww.util.ApiException
import com.tolgahantutar.bexworkfloww.util.NoInternetException
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance<AuthViewModelFactory>()
    private lateinit var binding : ActivityLoginBinding
    private lateinit var viewModel:AuthViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        viewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)

binding.buttonLogin.setOnClickListener{
   progress_bar.visibility = View.VISIBLE
loginUser()
}
    }
private fun loginUser(){

    val SessionID = 0
    val AuthorityID = 0
    val userName =binding.editTextUsername.text.toString().trim()
    val password = binding.editTextPassword.text.toString().trim()
    val LoginType = "System"


    lifecycleScope.launch {

        try{
val authResponse = viewModel.userLogin(SessionID,AuthorityID,userName,password,LoginType)

            if(authResponse.Result==true){
                progress_bar.visibility = View.INVISIBLE
                val intent = Intent(this@LoginActivity,HomeActivity::class.java)
                startActivity(intent)
            }else{
                progress_bar.visibility = View.INVISIBLE
                Toast.makeText(applicationContext, "Login Failed!!", Toast.LENGTH_LONG).show()
            }
        }catch (e: ApiException){
            e.printStackTrace()
        }catch (e: NoInternetException){
            e.printStackTrace()
        }
    }


}
}