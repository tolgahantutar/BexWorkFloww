package com.tolgahantutar.bexworkfloww.ui.userdetailaddress


import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.data.models.InputEditTextModel
import com.tolgahantutar.bexworkfloww.data.models.getcontact.GetContactValue
import com.tolgahantutar.bexworkfloww.data.sharedpref.SharedPrefSingletonLoggedUsername
import com.tolgahantutar.bexworkfloww.databinding.UserDetailAddressFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.user_detail_address_fragment.*

@Suppress("DEPRECATION")
@AndroidEntryPoint
class UserDetailAddressFragment : Fragment() {
    private var userDetail : GetContactValue ? = null
    private val userDetailAddressViewModel: UserDetailAddressViewModel by viewModels()

    private var emailList = ArrayList<InputEditTextModel>()
    private var phoneList = ArrayList<InputEditTextModel>()
    private var addressList = ArrayList<InputEditTextModel>()
    private var webAddressList = ArrayList<InputEditTextModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: UserDetailAddressFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.user_detail_address_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
        userDetail=UserDetailAddressFragmentArgs.fromBundle(
        it
        ).GetContactDetail
            textview_username.text=userDetail?.name
            user_profile_text_left_drawable.setText("${(userDetail!!.displayName.substringBefore(" ")).subSequence(0,1)}"+"${(userDetail!!.displayName.substringAfter(" ").subSequence(0,1))}")
        }
        userDetailAddressViewModel.getAllEmail(userDetail!!.defaultEmail.contactId.toInt())
        userDetailAddressViewModel.getAllPhone(userDetail!!.defaultPhone.contactId.toInt())
        userDetailAddressViewModel.getAllAddress(userDetail!!.defaultAddress.contactId.toInt())
        userDetailAddressViewModel.getAllWebAddress(userDetail!!.defaultWebAddress.contactId.toInt())
        val linearLayout = requireView().findViewById<LinearLayout>(R.id.user_detail_address_linear_layout)
        userDetailAddressViewModel.getAllEmailResponseMutable.observe(viewLifecycleOwner, Observer {
            var x = 0
            while (x < it.totalRowsCount) {
                val textInputLayout = TextInputLayout(
                    requireContext(),
                    null,
                    R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox
                )
                val textInputLayoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                textInputLayout.hint = getString(R.string.Email)
                textInputLayout.setBackgroundColor(Color.WHITE)
                textInputLayoutParams.setMargins(0, 0, 0, 10)
                textInputLayout.layoutParams = textInputLayoutParams

                 val updateMailEditText = TextInputEditText(requireContext())
                val mailEditTextParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                updateMailEditText.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.user_email,
                    0,
                   0,
                    0
                )
                updateMailEditText.compoundDrawablePadding = 10
                updateMailEditText.inputType = InputType.TYPE_NULL
                updateMailEditText.isFocusableInTouchMode = false
                updateMailEditText.layoutParams = mailEditTextParams
                updateMailEditText.setText(it.getAllEmailsResponse[x].address)
                val emailListElement = InputEditTextModel(textInputLayout,updateMailEditText)
                emailList.add(emailListElement)
                emailList[x].textInputLayout.addView(emailList[x].textInputEditText)
                linearLayout.addView(emailList[x].textInputLayout)
                x++
            }
        })
        userDetailAddressViewModel.getAllPhoneResponseMutable.observe(viewLifecycleOwner, Observer {
            var x = 0
            while (x < it.totalRowsCount) {
                val textInputLayout = TextInputLayout(
                    requireContext(),
                    null,
                    R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox
                )
                val textInputLayoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                textInputLayout.hint = getString(R.string.Phone)
                textInputLayout.setBackgroundColor(Color.WHITE)
                textInputLayoutParams.setMargins(0, 0, 0, 10)
                textInputLayout.layoutParams = textInputLayoutParams

                val updatePhoneEditText = TextInputEditText(requireContext())
                val phoneEditTextParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                updatePhoneEditText.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.user_phone,
                    0,
                    0,
                    0
                )
                updatePhoneEditText.compoundDrawablePadding = 10
                updatePhoneEditText.inputType = InputType.TYPE_NULL
                updatePhoneEditText.isFocusableInTouchMode = false
                updatePhoneEditText.layoutParams = phoneEditTextParams
                updatePhoneEditText.setText(it.getAllPhonesValue[x].cleanBody)
                val phoneListElement = InputEditTextModel(textInputLayout,updatePhoneEditText)
                phoneList.add(phoneListElement)
                phoneList[x].textInputLayout.addView(phoneList[x].textInputEditText)
                linearLayout.addView(phoneList[x].textInputLayout)
                x++
            }
        })
        userDetailAddressViewModel.getAllAddressResponseMutable.observe(viewLifecycleOwner, Observer {
            var x = 0
            while (x < it.totalRowsCount) {
                val textInputLayout = TextInputLayout(
                    requireContext(),
                    null,
                    R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox
                )
                val textInputLayoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                textInputLayout.hint = getString(R.string.Address)
                textInputLayout.setBackgroundColor(Color.WHITE)
                textInputLayoutParams.setMargins(0, 0, 0, 10)
                textInputLayout.layoutParams = textInputLayoutParams

                val updateAddressEditText = TextInputEditText(requireContext())
                val textInputEditTextParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                updateAddressEditText.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.user_address,
                    0,
                    0,
                    0
                )
                updateAddressEditText.compoundDrawablePadding = 10
                updateAddressEditText.inputType = InputType.TYPE_NULL
                updateAddressEditText.isFocusableInTouchMode = false
                updateAddressEditText.isSingleLine = false
                updateAddressEditText.layoutParams = textInputEditTextParams
                updateAddressEditText.setText(it.getAllAddressResponse[x].displayBody)
                val addressListElement = InputEditTextModel(textInputLayout,updateAddressEditText)
                addressList.add(addressListElement)
                addressList[x].textInputLayout.addView(addressList[x].textInputEditText)
                linearLayout.addView(addressList[x].textInputLayout)
                x++
            }
        })
        userDetailAddressViewModel.getAllWebAddressResponseMutable.observe(viewLifecycleOwner,Observer{
            var x = 0
            while (x < it.totalRowsCount) {
                val textInputLayout = TextInputLayout(
                    requireContext(),
                    null,
                    R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox
                )
                val textInputLayoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                textInputLayout.hint = getString(R.string.WebAddress)
                textInputLayout.setBackgroundColor(Color.WHITE)
                textInputLayoutParams.setMargins(0, 0, 0, 10)
                textInputLayout.layoutParams = textInputLayoutParams

                val updateWebAddressEditText = TextInputEditText(requireContext())

                val textInputEditTextParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                updateWebAddressEditText.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.user_web_address,
                    0,
                    0,
                    0
                )
                updateWebAddressEditText.compoundDrawablePadding = 10
                updateWebAddressEditText.inputType = InputType.TYPE_NULL
                updateWebAddressEditText.isFocusableInTouchMode = false
                updateWebAddressEditText.isSingleLine = false
                updateWebAddressEditText.layoutParams = textInputEditTextParams
                updateWebAddressEditText.setText(it.getAllWebAddressValue[x].url)
                val webAddressListElement = InputEditTextModel(textInputLayout,updateWebAddressEditText)
                webAddressList.add(webAddressListElement)
                webAddressList[x].textInputLayout.addView(webAddressList[x].textInputEditText)
                linearLayout.addView(webAddressList[x].textInputLayout)
                x++
            }
        })
    }
    }

