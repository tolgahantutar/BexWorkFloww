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
import androidx.lifecycle.ViewModelProvider
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
        authViewModel.isSuccessfull.observe(this, Observer {
            if(it){
                Toast.makeText(this, "User Get Successfully", Toast.LENGTH_SHORT).show()
            }
            if(!it){
                Toast.makeText(this, "Can't get the User", Toast.LENGTH_SHORT).show()
            }
        })
    authViewModel.getUserResponseMutable.observe(this, Observer {
        if (it.result){
            Toast.makeText(this, "Data Changed", Toast.LENGTH_SHORT).show()
        }
    })
    }
}