package com.tolgahantutar.bexworkfloww.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.data.network.responses.AuthorizeSessionResponse
import com.tolgahantutar.bexworkfloww.databinding.ActivityLoginBinding
import com.tolgahantutar.bexworkfloww.ui.home.HomeActivity
import com.tolgahantutar.bexworkfloww.util.ApiException
import com.tolgahantutar.bexworkfloww.util.NoInternetException
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val authViewModelFactory : AuthViewModelFactory by instance<AuthViewModelFactory>()
    private val getDomainViewModelFactory: GetDomainViewModelFactory by instance<GetDomainViewModelFactory>()
    private lateinit var binding : ActivityLoginBinding
    private lateinit var authViewModel:AuthViewModel
    private lateinit var getDomainViewModel: GetDomainViewModel





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        authViewModel = ViewModelProvider(this,authViewModelFactory).get(AuthViewModel::class.java)
        getDomainViewModel = ViewModelProvider(this,getDomainViewModelFactory).get(GetDomainViewModel::class.java)
        binding.viewmodel=authViewModel
        authViewModel.isLoading.observe(this, Observer {
            if (it){
                progress_bar.visibility = View.VISIBLE
            }else{
                progress_bar.visibility = View.INVISIBLE
            }
        })
        getDomainViewModel.getDomain()
        getDomainViewModel.domainResult.observe(this, Observer {
            if(it){
                getDomainViewModel.domainApiKey.observe(this, Observer {
                    Toast.makeText(this, getDomainViewModel.domainApiKey.value, Toast.LENGTH_LONG).show()
                })
            }
        })

    }
}