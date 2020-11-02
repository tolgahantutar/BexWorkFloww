package com.tolgahantutar.bexworkfloww.ui.editprofile

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.ui.auth.SharedPrefSingletonAuthID
import com.tolgahantutar.bexworkfloww.ui.auth.SharedPrefSingletonDomainKey
import com.tolgahantutar.bexworkfloww.validations.EmailValidation
import com.tolgahantutar.bexworkfloww.validations.PhoneValidation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.edit_profile_fragment.*

@Suppress("DEPRECATION")
@AndroidEntryPoint
class EditProfileFragment : Fragment() {
    private val editProfileViewModel: EditProfileViewModel by viewModels()
    lateinit var menuSaveItem: MenuItem
    lateinit var title: String
    lateinit var s: SpannableString
    var isEnabled = MutableLiveData<Boolean>(false)
    var emailValidation = EmailValidation()
    var phoneNumberValidation = PhoneValidation()
    var isEmailValid: Boolean=false
    var isPhoneNumberValid: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.edit_profile_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val idDebug= SharedPrefSingletonAuthID.getSomeStringValue(requireContext())
        val keyDebug = SharedPrefSingletonDomainKey.getSomeStringValue(requireContext())
        editProfileViewModel.getUserResponse(idDebug, "Bearer " + keyDebug!!)
        editProfileViewModel.getUserResponseMutable.observe(viewLifecycleOwner, Observer {
            textview_username_edit_profile.text = it.getUserValue.contact.name
            text_email_edit_profile.setText(it.getUserValue.contact.defaultEmail.address)
            text_phone_edit_profile.setText(it.getUserValue.contact.defaultPhoneModel.cleanBody)
            text_address_edit_profile.setText(it.getUserValue.contact.defaultAdressModel.displayBody)
            text_web_address_edit_profile.setText(it.getUserValue.contact.defaultWebAddressModel.url)
        })
        text_phone_edit_profile.addTextChangedListener(textWatcherPhone)
        text_email_edit_profile.addTextChangedListener(textWatcherEmail)
        text_address_edit_profile.addTextChangedListener(textWatcherAddress)
        text_web_address_edit_profile.addTextChangedListener(textWatcherWebAddress)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
        menuSaveItem =menu.findItem(R.id.save_profile)
         title= menuSaveItem.title.toString()
         s = SpannableString(title)
        s.setSpan(ForegroundColorSpan(Color.parseColor("#808080")),0,s.length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        menuSaveItem.title=s
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        isEnabled.observe(viewLifecycleOwner, Observer {
            menu.findItem(R.id.save_profile).setEnabled(it)
        })
    }
    private val textWatcherEmail = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
        override fun afterTextChanged(p0: Editable?) {
            if(p0.toString()!=editProfileViewModel.getUserResponseMutable.value!!.getUserValue.contact.defaultEmail.address){
                isEnabled.value=true
                s.setSpan(ForegroundColorSpan(Color.parseColor("#FFFFFF")),0,s.length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                menuSaveItem.title=s
            }
            else{
                isEnabled.value=false
                s.setSpan(ForegroundColorSpan(Color.parseColor("#808080")),0,s.length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                menuSaveItem.title=s
            }
            isEmailValid=emailValidation.checkEmail(p0.toString())
            if(isEmailValid) {
                Toast.makeText(context, isEmailValid.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
    private val textWatcherPhone = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
        override fun afterTextChanged(p0: Editable?) {
            if(p0.toString()!=editProfileViewModel.getUserResponseMutable.value!!.getUserValue.contact.defaultPhoneModel.cleanBody){
                isEnabled.value=true
                s.setSpan(ForegroundColorSpan(Color.parseColor("#FFFFFF")),0,s.length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                menuSaveItem.title=s
            }
            else{
                isEnabled.value= false
                s.setSpan(ForegroundColorSpan(Color.parseColor("#808080")),0,s.length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                menuSaveItem.title=s
            }
            isPhoneNumberValid= phoneNumberValidation.checkPhoneNumber(p0.toString())
            if(isPhoneNumberValid){
                Toast.makeText(context, isPhoneNumberValid.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
    private val textWatcherAddress = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
        override fun afterTextChanged(p0: Editable?) {
            if(p0.toString()!=editProfileViewModel.getUserResponseMutable.value!!.getUserValue.contact.defaultAdressModel.displayBody){
                isEnabled.value=true
                s.setSpan(ForegroundColorSpan(Color.parseColor("#FFFFFF")),0,s.length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                menuSaveItem.title=s
            }
            else{
                isEnabled.value=false
                s.setSpan(ForegroundColorSpan(Color.parseColor("#808080")),0,s.length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                menuSaveItem.title=s
            }
        }
    }
    private val textWatcherWebAddress = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
        override fun afterTextChanged(p0: Editable?) {
            if(p0.toString()!=editProfileViewModel.getUserResponseMutable.value!!.getUserValue.contact.defaultWebAddressModel.url){
                isEnabled.value=true
                s.setSpan(ForegroundColorSpan(Color.parseColor("#FFFFFF")),0,s.length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                menuSaveItem.title=s
            }
            else{
                isEnabled.value=false
                s.setSpan(ForegroundColorSpan(Color.parseColor("#808080")),0,s.length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                menuSaveItem.title=s
            }
        }
    }
}