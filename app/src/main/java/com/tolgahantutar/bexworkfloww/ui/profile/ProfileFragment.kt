package com.tolgahantutar.bexworkfloww.ui.profile

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.ui.auth.LoginActivity
import com.tolgahantutar.bexworkfloww.ui.auth.SharedPrefSingleton

class ProfileFragment : Fragment() {
    private lateinit var viewModel: ProfileViewModel
    private val sharedPrefSingleton = SharedPrefSingleton.instance

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(activity,LoginActivity::class.java)
        when(item.itemId){
            R.id.item_logout ->{
             startActivity(intent)
                sharedPrefSingleton.setSomeStringValue(requireContext(),"notGenerated")
                activity?.onBackPressed()
            }

        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.profile_menu,menu)
    }

}