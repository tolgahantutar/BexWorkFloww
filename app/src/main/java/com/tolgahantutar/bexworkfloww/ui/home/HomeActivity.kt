package com.tolgahantutar.bexworkfloww.ui.home

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.iterator
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var editProfile: ImageView
    private val homeActivityViewModel: HomeActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
         binding.homeViewModel=homeActivityViewModel

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
        }
        editProfile = findViewById(R.id.user_profile_image_left_drawable)
        editProfile.setOnClickListener {
            navController.navigate(R.id.editProfileFragment)
            drawer_layout.closeDrawers()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.Fragment),
            drawer_layout
        )
    }
}