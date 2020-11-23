package com.tolgahantutar.bexworkfloww.ui.editprofile


import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.*
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputLayout
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.data.models.InputEditTextModel
import com.tolgahantutar.bexworkfloww.databinding.EditProfileFragmentBinding
import com.tolgahantutar.bexworkfloww.ui.customedittexts.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.edit_profile_fragment.*


@Suppress("DEPRECATION")
@AndroidEntryPoint
class EditProfileFragment : Fragment() {
    private lateinit var binding : EditProfileFragmentBinding
    private val editProfileViewModel: EditProfileViewModel by viewModels()

    private var emailList = ArrayList<InputEditTextModel>()
    private var phoneList = ArrayList<InputEditTextModel>()
    private var addressList = ArrayList<InputEditTextModel>()
    private var webAddressList = ArrayList<InputEditTextModel>()

    private lateinit var updateMailEditText : UpdateMailEditText
    private lateinit var updatePhoneEditText: UpdatePhoneEditText
    private lateinit var updateWebAddressEditText: UpdateWebAddressEditText
    private lateinit var updateAddressEditText: UpdateAddressEditText
    //
    private lateinit var dialog: AlertDialog
    private val action = EditProfileFragmentDirections.actionEditProfileFragmentSelf()

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
        setHasOptionsMenu(true)
        binding = EditProfileFragmentBinding.inflate(inflater, container, false)
        binding.editProfile=editProfileViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val linearLayout = requireView().findViewById<LinearLayout>(R.id.edit_profile_linear_layout)
        setProgressDialog()
        editProfileViewModel.executeUserResponse()
        editProfileViewModel.getUserResponseMutable.observe(viewLifecycleOwner, Observer {
            if (it.result) {
                profile_image_text.text = "${it.getUserValue.firstName.subSequence(0, 1)}" + "${
                    it.getUserValue.lastName.subSequence(
                        0,
                        1
                    )
                }"
            }
        })
        editProfileViewModel.getAllEmailResponseMutable.observe(viewLifecycleOwner, Observer {
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

                updateMailEditText = UpdateMailEditText(
                    requireContext(),
                    it.getAllEmailsResponse[x].id,
                    editProfileViewModel,
                    it.getAllEmailsResponse[x].priority,
                    requireView()
                )
                val mailEditTextParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                updateMailEditText.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.user_email,
                    0,
                    R.drawable.remove_black,
                    0
                )
                updateMailEditText.compoundDrawablePadding = 10
                updateMailEditText.inputType = InputType.TYPE_NULL
                updateMailEditText.isFocusableInTouchMode = false
                updateMailEditText.layoutParams = mailEditTextParams
                updateMailEditText.setText(it.getAllEmailsResponse[x].address)
                val emailListElement = InputEditTextModel(textInputLayout, updateMailEditText)
                emailList.add(emailListElement)
                emailList[x].textInputLayout.addView(emailList[x].textInputEditText)
                linearLayout.addView(emailList[x].textInputLayout)
                x++
            }
            val mailTextInputLayout = TextInputLayout(
                requireContext(),
                null,
                R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox
            )
            val mailTextInputLayoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            mailTextInputLayout.hint = getString(R.string.Email)
            mailTextInputLayout.setBackgroundColor(Color.WHITE)
            mailTextInputLayoutParams.setMargins(0, 0, 0, 10)
            mailTextInputLayout.layoutParams = mailTextInputLayoutParams

            val mailTextInputEditText = CreateMailEditText(
                requireContext(),
                editProfileViewModel,
                requireView(),
                action
            )
            val mailTextInputEditTextParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            mailTextInputEditText.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.user_email,
                0,
                R.drawable.add_black,
                0
            )
            mailTextInputEditText.compoundDrawablePadding = 10
            mailTextInputEditText.layoutParams = mailTextInputEditTextParams

            mailTextInputLayout.addView(mailTextInputEditText)
            linearLayout.addView(mailTextInputLayout)
        })


        editProfileViewModel.getAllPhonesResponseMutable.observe(viewLifecycleOwner, Observer {
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

                updatePhoneEditText = UpdatePhoneEditText(
                    requireContext(),
                    it.getAllPhonesValue[x].id,
                    editProfileViewModel,
                    it.getAllPhonesValue[x].priority,
                    requireView()
                )
                val phoneEditTextParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                updatePhoneEditText.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.user_phone,
                    0,
                    R.drawable.remove_black,
                    0
                )
                updatePhoneEditText.compoundDrawablePadding = 10
                updatePhoneEditText.inputType = InputType.TYPE_NULL
                updatePhoneEditText.isFocusableInTouchMode = false
                updatePhoneEditText.layoutParams = phoneEditTextParams
                updatePhoneEditText.setText(it.getAllPhonesValue[x].cleanBody)
                val phoneListElement = InputEditTextModel(textInputLayout, updatePhoneEditText)
                phoneList.add(phoneListElement)
                phoneList[x].textInputLayout.addView(phoneList[x].textInputEditText)
                linearLayout.addView(phoneList[x].textInputLayout)
                x++
            }
            val phoneTextInputLayout = TextInputLayout(
                requireContext(),
                null,
                R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox
            )
            val phoneTextInputLayoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            phoneTextInputLayout.hint = getString(R.string.Phone)
            phoneTextInputLayout.setBackgroundColor(Color.WHITE)
            phoneTextInputLayoutParams.setMargins(0, 0, 0, 10)
            phoneTextInputLayout.layoutParams = phoneTextInputLayoutParams

            val phoneTextInputEditText = CreatePhoneEditText(
                requireContext(),
                editProfileViewModel,
                requireView(),
                action
            )
            val phoneTextInputEditTextParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            phoneTextInputEditText.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.user_phone,
                0,
                R.drawable.add_black,
                0
            )
            phoneTextInputEditText.compoundDrawablePadding = 10
            phoneTextInputEditText.inputType = InputType.TYPE_CLASS_PHONE
            phoneTextInputEditText.layoutParams = phoneTextInputEditTextParams
            phoneTextInputEditText.addTextChangedListener(phoneTextWatcher)
            phoneTextInputLayout.addView(phoneTextInputEditText)
            linearLayout.addView(phoneTextInputLayout)


        })
        editProfileViewModel.getAllAddressResponseMutable.observe(viewLifecycleOwner, Observer {
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

                updateAddressEditText = UpdateAddressEditText(
                    requireContext(),
                    it.getAllAddressResponse[x].country.name.toString(),
                    it.getAllAddressResponse[x].country.id.toInt(),
                    it.getAllAddressResponse[x].county.name.toString(),
                    it.getAllAddressResponse[x].county.id.toInt(),
                    it.getAllAddressResponse[x].city.name.toString(),
                    it.getAllAddressResponse[x].city.id.toInt(),
                    it.getAllAddressResponse[x].id.toInt(),
                    editProfileViewModel,
                    it.getAllAddressResponse[x].priority.toInt(),
                    it.getAllAddressResponse[x].body,
                    viewLifecycleOwner,
                    requireView()
                )
                val textInputEditTextParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                updateAddressEditText.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.user_address,
                    0,
                    R.drawable.remove_black,
                    0
                )
                updateAddressEditText.compoundDrawablePadding = 10
                updateAddressEditText.inputType = InputType.TYPE_NULL
                updateAddressEditText.isFocusableInTouchMode = false
                updateAddressEditText.layoutParams = textInputEditTextParams
                updateAddressEditText.setText(it.getAllAddressResponse[x].displayBody)
                val addressListElement = InputEditTextModel(textInputLayout, updateAddressEditText)
                addressList.add(addressListElement)
                addressList[x].textInputLayout.addView(addressList[x].textInputEditText)
                linearLayout.addView(addressList[x].textInputLayout)
                x++
            }
            val addressTextInputLayout = TextInputLayout(
                requireContext(),
                null,
                R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox
            )
            val addressTextInputLayoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            addressTextInputLayout.hint = getString(R.string.Address)
            addressTextInputLayout.setBackgroundColor(Color.WHITE)
            addressTextInputLayoutParams.setMargins(0, 0, 0, 10)
            addressTextInputLayout.layoutParams = addressTextInputLayoutParams

            val adressTextInputEditText = CreateAddressEditText(
                requireContext(),
                editProfileViewModel,
                viewLifecycleOwner,
                requireView(),
                action
            )
            val adressTextInputEditTextParams: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            adressTextInputEditText.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.user_address,
                0,
                R.drawable.add_black,
                0
            )
            adressTextInputEditText.compoundDrawablePadding = 10
            adressTextInputEditText.layoutParams = adressTextInputEditTextParams
            adressTextInputEditText.inputType = InputType.TYPE_NULL
            adressTextInputEditText.isFocusableInTouchMode = false

            addressTextInputLayout.addView(adressTextInputEditText)
            linearLayout.addView(addressTextInputLayout)
        })
        editProfileViewModel.getAllWebAddressResponseMutable.observe(viewLifecycleOwner, Observer {
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

                updateWebAddressEditText = UpdateWebAddressEditText(
                    requireContext(),
                    it.getAllWebAddressValue[x].id,
                    editProfileViewModel,
                    it.getAllWebAddressValue[x].priority,
                    requireView()
                )

                val textInputEditTextParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                updateWebAddressEditText.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.user_web_address,
                    0,
                    R.drawable.remove_black,
                    0
                )
                updateWebAddressEditText.compoundDrawablePadding = 10
                updateWebAddressEditText.inputType = InputType.TYPE_NULL
                updateWebAddressEditText.isFocusableInTouchMode = false
                updateWebAddressEditText.isSingleLine = false
                updateWebAddressEditText.layoutParams = textInputEditTextParams
                updateWebAddressEditText.setText(it.getAllWebAddressValue[x].url)
                val webAddressListElement = InputEditTextModel(
                    textInputLayout,
                    updateWebAddressEditText
                )
                webAddressList.add(webAddressListElement)
                webAddressList[x].textInputLayout.addView(webAddressList[x].textInputEditText)
                linearLayout.addView(webAddressList[x].textInputLayout)
                x++
            }
            val webAddressTextInputLayout = TextInputLayout(
                requireContext(),
                null,
                R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox
            )
            val webAddressTextInputLayoutParams: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            webAddressTextInputLayout.hint = getString(R.string.WebAddress)
            webAddressTextInputLayout.setBackgroundColor(Color.WHITE)
            webAddressTextInputLayoutParams.setMargins(0, 0, 0, 10)
            webAddressTextInputLayout.layoutParams = webAddressTextInputLayoutParams

            val webAddressTextInputEditText = CreateWebAddressEditText(
                requireContext(),
                editProfileViewModel,
                requireView(),
                action
            )
            val phoneTextInputEditTextParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            webAddressTextInputEditText.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.user_web_address,
                0,
                R.drawable.add_black,
                0
            )
            webAddressTextInputEditText.compoundDrawablePadding = 10
            webAddressTextInputEditText.layoutParams = phoneTextInputEditTextParams

            webAddressTextInputLayout.addView(webAddressTextInputEditText)
            linearLayout.addView(webAddressTextInputLayout)
        })
        editProfileViewModel.isProgressBarDismissed.observe(viewLifecycleOwner, Observer {
            if (it) {
                dialog.dismiss()
            }
        })
    }

    private fun setProgressDialog() {
        val llPadding = 30
        val ll = LinearLayout(requireContext())
        ll.orientation = LinearLayout.HORIZONTAL
        ll.setPadding(llPadding, llPadding, llPadding, llPadding)
        ll.gravity = Gravity.CENTER
        var llParam = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        llParam.gravity = Gravity.CENTER
        ll.layoutParams = llParam

        val progressBar = ProgressBar(requireContext())
        progressBar.isIndeterminate = true
        progressBar.setPadding(0, 0, llPadding, 0)
        progressBar.layoutParams = llParam

        llParam = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        llParam.gravity = Gravity.CENTER
        val tvText = TextView(requireContext())
        tvText.text = getString(R.string.loading)
        tvText.setTextColor(Color.parseColor("#000000"))
        tvText.textSize = 20f
        tvText.layoutParams = llParam

        ll.addView(progressBar)
        ll.addView(tvText)

        val builder = AlertDialog.Builder(requireContext())
        builder.setCancelable(true)
        builder.setView(ll)

        dialog = builder.create()
        dialog.show()

        val window = dialog.window
        if (window != null) {
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(dialog.window!!.attributes)
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            dialog.window!!.attributes = layoutParams
        }
    }
    val phoneTextWatcher = (object: TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if(p0!!.lastIndex<p1){
                if(p0.toString()[p1-1]=='0'|| p0.toString()[p1-1]=='1'){
                    Toast.makeText(requireContext(), "Son girilen değer 0 veya 1'dir.", Toast.LENGTH_SHORT).show()
                }
            }else {
                if (p0.toString()[p1] != '0' || p0.toString()[p1] != '1' || p0.toString()[p1]!='2'|| p0.toString()[p1]!='3'|| p0.toString()[p1]!='4'|| p0.toString()[p1]!='5'|| p0.toString()[p1]!='6'|| p0.toString()[p1]!='7'|| p0.toString()[p1]!='8'|| p0.toString()[p1]!='9'|| p0.toString()[p1]!='0'|| p0.toString()[p1]!='+'|| p0.toString()[p1]!='*'|| p0.toString()[p1]!='#') {

                }
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    })
}