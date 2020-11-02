package com.tolgahantutar.bexworkfloww.ui.editprofile

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.databinding.EditProfileFragmentBinding
import com.tolgahantutar.bexworkfloww.data.sharedpref.SharedPrefSingletonAuthID
import com.tolgahantutar.bexworkfloww.data.sharedpref.SharedPrefSingletonDomainKey
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditProfileFragment : Fragment() {
    private lateinit var binding : EditProfileFragmentBinding
    private val editProfileViewModel: EditProfileViewModel by viewModels()
    lateinit var menuSaveItem: MenuItem
    lateinit var title: String
    lateinit var spannable: SpannableString
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = EditProfileFragmentBinding.inflate(inflater,container,false)
        binding.editProfile=editProfileViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        editProfileViewModel.executeUserResponse()
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
        menuSaveItem =menu.findItem(R.id.save_profile)
         title= menuSaveItem.title.toString()
        spannable = SpannableString(title)
        spannable.setSpan(ForegroundColorSpan(Color.parseColor("#808080")),0,spannable.length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        menuSaveItem.title=spannable
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        editProfileViewModel.isEnabled.observe(viewLifecycleOwner, Observer {
            menu.findItem(R.id.save_profile).setEnabled(it)
            if(it){
                spannable.setSpan(ForegroundColorSpan(Color.parseColor("#FFFFFF")),0,spannable.length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                menuSaveItem.title=spannable
            }
            else{
                spannable.setSpan(ForegroundColorSpan(Color.parseColor("#808080")),0,spannable.length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                menuSaveItem.title=spannable
            }
        })
    }
}