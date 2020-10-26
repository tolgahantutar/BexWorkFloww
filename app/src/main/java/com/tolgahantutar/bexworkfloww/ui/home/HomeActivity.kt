package com.tolgahantutar.bexworkfloww.ui.home

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.iterator
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tolgahantutar.bexworkfloww.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var toolbar : androidx.appcompat.widget.Toolbar
    lateinit var contactsHeader : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar_label)
        val navController = Navigation.findNavController(this, R.id.Fragment)
        NavigationUI.setupWithNavController(nav_view, navController)
        NavigationUI.setupWithNavController(bottom_navigation , navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
        contactsHeader = findViewById(R.id.contacts_header)
        contactsHeader.setOnClickListener {
            drawer_layout.closeDrawers()
            navController.navigate(R.id.toFragmentAddressbook)
            bottom_navigation.selectedItemId = R.id.toFragmentAddressbook
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->

            for(menuItem in bottom_navigation.menu.iterator()){
                menuItem.isEnabled = true
            }

            val menu = bottom_navigation.menu.findItem(destination.id)
            menu?.isEnabled = false
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.Fragment),
            drawer_layout
        )
    }
}