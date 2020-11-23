package com.tolgahantutar.bexworkfloww.ui.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.iterator
import androidx.core.view.marginBottom
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.data.models.LoggedUserModel
import com.tolgahantutar.bexworkfloww.data.sharedpref.SharedPrefSingletonLoggedUsername
import com.tolgahantutar.bexworkfloww.databinding.ActivityHomeBinding
import com.tolgahantutar.bexworkfloww.ui.editprofile.EditProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var editProfile: ImageView
    private val homeActivityViewModel: HomeActivityViewModel by viewModels()
    private val editProfileViewModel : EditProfileViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        val loggedUsername = LoggedUserModel(SharedPrefSingletonLoggedUsername(applicationContext).getSomeStringValue()!!)
        binding.loggedUserModel= loggedUsername
        setSupportActionBar(toolbar_label)
        val navController = Navigation.findNavController(this, R.id.Fragment)
        NavigationUI.setupWithNavController(nav_view, navController)
        NavigationUI.setupWithNavController(bottom_navigation , navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            for(menuItem in bottom_navigation.menu.iterator()){
                menuItem.isEnabled = true
            }
            val menu = bottom_navigation.menu.findItem(destination.id)
            menu?.isEnabled = false

            when(destination.id){
                R.id.editProfileFragment ->{
                    var params = fragment_container_linear.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins(0,0,0,0)
                    fragment_container_linear.layoutParams = params
                    bottom_navigation.visibility = View.INVISIBLE
                }
                else -> {
                    var params = fragment_container_linear.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins(0,0,0,92)
                    fragment_container_linear.layoutParams = params
                    bottom_navigation.visibility = View.VISIBLE
                }
            }
        }
        editProfile = findViewById(R.id.user_profile_image_left_drawable)
        editProfile.setOnClickListener {
            navController.navigate(R.id.editProfileFragment)
            drawer_layout.closeDrawers()
        }
        image_settings.setOnClickListener {
            navController.navigate(R.id.settingsFragment)
            drawer_layout.closeDrawers()
        }
    editProfileViewModel.isRefreshFragment.observe(this, Observer {
        if(it){
            navController.navigate(R.id.inboxFragment)
        }
    })
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.Fragment),
            drawer_layout
        )
    }
}