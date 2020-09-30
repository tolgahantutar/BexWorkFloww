package com.tolgahantutar.bexworkfloww.ui.auth


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.tolgahantutar.bexworkfloww.*
import com.tolgahantutar.bexworkfloww.dagger.components.AuthViewModelComponent
import com.tolgahantutar.bexworkfloww.dagger.components.DaggerAuthViewModelComponent
import com.tolgahantutar.bexworkfloww.dagger.modules.AuthorizeSessionRepositoryModule
import com.tolgahantutar.bexworkfloww.data.network.apis.VisaServicesApi
import com.tolgahantutar.bexworkfloww.data.network.apis.WorkFlowServicesApi
import com.tolgahantutar.bexworkfloww.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(){
    private lateinit var binding : ActivityLoginBinding
    @Inject lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         val authViewModelComponent: AuthViewModelComponent = DaggerAuthViewModelComponent.builder()
            .authorizeSessionRepositoryModule(
                AuthorizeSessionRepositoryModule(
                    visaServicesApi = VisaServicesApi.invoke(),
                    workFlowServicesApi = WorkFlowServicesApi.invoke()
                )
            )
            .build()
        authViewModelComponent.inject(this)

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

    }
}