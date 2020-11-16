package com.tolgahantutar.bexworkfloww.ui.editprofile


import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.text.*
import android.text.style.ForegroundColorSpan
import android.view.*
import android.view.ViewGroup
import android.widget.*
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.data.models.CustomCityModel
import com.tolgahantutar.bexworkfloww.databinding.EditProfileFragmentBinding
import com.tolgahantutar.bexworkfloww.ui.customedittexts.AddressEditText
import com.tolgahantutar.bexworkfloww.ui.customedittexts.MailEditText
import com.tolgahantutar.bexworkfloww.ui.customedittexts.PhoneEditText
import com.tolgahantutar.bexworkfloww.ui.customedittexts.WebAddressEditText
import com.tolgahantutar.bexworkfloww.validations.EmailValidation
import com.tolgahantutar.bexworkfloww.validations.PhoneValidation
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditProfileFragment : Fragment() {
    private lateinit var binding : EditProfileFragmentBinding
    private val editProfileViewModel: EditProfileViewModel by viewModels()

    private var isButtonEnabledAddress = MutableLiveData<Boolean>(false)
    private var isButtonEnabledPriority = MutableLiveData<Boolean>(false)

    private var textInputLayoutArrayEmail = ArrayList<TextInputLayout>()
    private var textInputEditTextArrayEmail = ArrayList<TextInputEditText>()
    private var textInputLayoutArrayPhone = ArrayList<TextInputLayout>()
    private var textInputEditTextArrayPhone = ArrayList<TextInputEditText>()
    private var textInputLayoutArrayAddress = ArrayList<TextInputLayout>()
    private var textInputEditTextArrayAddress = ArrayList<TextInputEditText>()
    private var textInputLayoutArrayWebAddress = ArrayList<TextInputLayout>()
    private var textInputEditTextArrayWebAddress = ArrayList<TextInputEditText>()
    private lateinit var mailEditText : MailEditText
    private lateinit var phoneEditText: PhoneEditText
    private lateinit var webAddressEditText: WebAddressEditText
    //
    private lateinit var dialog: AlertDialog
    private val action = EditProfileFragmentDirections.actionEditProfileFragmentSelf()
    //
    var countryID =0
    var cityID = 0
    var countyID = 0

    var cityName=""
    var countryName= ""
    var countyName = ""
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
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val linearLayout = requireView().findViewById<LinearLayout>(R.id.edit_profile_linear_layout)
        setProgressDialog()
        editProfileViewModel.executeUserResponse()
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

                textInputLayoutArrayEmail.add(textInputLayout)
                mailEditText = MailEditText(
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
                mailEditText.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.user_email,
                    0,
                    R.drawable.remove_black,
                    0
                )
                mailEditText.compoundDrawablePadding = 10
                mailEditText.inputType = InputType.TYPE_NULL
                mailEditText.isFocusableInTouchMode = false
                mailEditText.layoutParams = mailEditTextParams
                mailEditText.setText(it.getAllEmailsResponse[x].address)
                textInputEditTextArrayEmail.add(mailEditText)
                textInputLayoutArrayEmail[x].addView(textInputEditTextArrayEmail[x])
                linearLayout.addView(textInputLayoutArrayEmail[x])
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

            val mailTextInputEditText = TextInputEditText(requireContext())
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
            mailTextInputEditText.setOnTouchListener(object : View.OnTouchListener {
                val DRAWABLE_RIGHT = 0
                override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                    when (p1?.action) {
                        MotionEvent.ACTION_UP -> {
                            if (p1.getRawX() >= (mailTextInputEditText.right - mailTextInputEditText.compoundDrawables[DRAWABLE_RIGHT].bounds.width())) {
                                if (mailTextInputEditText.text.toString().isNullOrEmpty()) {
                                    mailTextInputEditText.error = getString(R.string.property_cannot_be_null)
                                } else {
                                    if (EmailValidation().checkEmail(mailTextInputEditText.text.toString())) {
                                        val builder: AlertDialog.Builder =
                                            AlertDialog.Builder(
                                                requireContext(),
                                                R.style.ThemeOverlay_AppCompat_Dialog_Alert
                                            )
                                        builder.setTitle(getString(R.string.create_email_operation))
                                        val layout = LinearLayout(context)
                                        layout.orientation = LinearLayout.VERTICAL
                                        val priorityEditText = EditText(context)
                                        priorityEditText.hint = getString(R.string.enter_priority)
                                        priorityEditText.inputType = InputType.TYPE_CLASS_NUMBER
                                        layout.addView(priorityEditText)
                                        builder.setView(layout)
                                        builder.setPositiveButton(
                                            getString(R.string.create_text),
                                            DialogInterface.OnClickListener { _, _ ->
                                                val priority = priorityEditText.text.toString()
                                                editProfileViewModel.createMailFromMailEditText(
                                                    mailTextInputEditText.text.toString(),
                                                    priority.toInt(),
                                                    requireContext()
                                                )
                                            Navigation.findNavController(requireView()).navigate(action)
                                            })
                                        builder.setNegativeButton(getString(R.string.cancel),
                                            DialogInterface.OnClickListener { dialog, _ ->
                                                dialog.cancel()
                                            })
                                        val dialog: AlertDialog = builder.create()
                                        dialog.show()
                                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                                            false
                                        priorityEditText.addTextChangedListener(object :
                                            TextWatcher {
                                            override fun beforeTextChanged(
                                                p0: CharSequence?,
                                                p1: Int,
                                                p2: Int,
                                                p3: Int
                                            ) {
                                            }

                                            override fun onTextChanged(
                                                p0: CharSequence?,
                                                p1: Int,
                                                p2: Int,
                                                p3: Int
                                            ) {
                                            }

                                            override fun afterTextChanged(p0: Editable?) {
                                                dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                                                    !p0.toString().isNullOrEmpty()
                                            }
                                        })
                                        return true
                                    } else {
                                        mailTextInputEditText.error =
                                            getString(R.string.invalid_email)
                                    }
                                }
                            }
                        }
                    }
                    return false
                }
            })
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

                textInputLayoutArrayPhone.add(textInputLayout)
                phoneEditText = PhoneEditText(
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
                phoneEditText.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.user_phone,
                    0,
                    R.drawable.remove_black,
                    0
                )
                phoneEditText.compoundDrawablePadding = 10
                phoneEditText.inputType = InputType.TYPE_NULL
                phoneEditText.isFocusableInTouchMode = false
                phoneEditText.layoutParams = phoneEditTextParams
                phoneEditText.setText(it.getAllPhonesValue[x].cleanBody)
                textInputEditTextArrayPhone.add(phoneEditText)
                textInputLayoutArrayPhone[x].addView(textInputEditTextArrayPhone[x])
                linearLayout.addView(textInputLayoutArrayPhone[x])
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

            val phoneTextInputEditText = TextInputEditText(requireContext())
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

            phoneTextInputLayout.addView(phoneTextInputEditText)
            linearLayout.addView(phoneTextInputLayout)
            phoneTextInputEditText.setOnTouchListener(object : View.OnTouchListener {
                val DRAWABLE_RIGHT = 0
                override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                    when (p1?.action) {
                        MotionEvent.ACTION_UP -> {
                            if (p1.getRawX() >= (phoneTextInputEditText.right - phoneTextInputEditText.compoundDrawables[DRAWABLE_RIGHT].bounds.width())) {
                                if (phoneTextInputEditText.text.toString().isNullOrEmpty()) {
                                    phoneTextInputEditText.error = getString(R.string.property_cannot_be_null)
                                } else {
                                    if (!(PhoneValidation().checkPhoneNumber(phoneTextInputEditText.text.toString()))) {
                                        phoneTextInputEditText.error =
                                            getString(R.string.invalid_phone)
                                    } else {
                                        val builder: AlertDialog.Builder =
                                            AlertDialog.Builder(
                                                requireContext(),
                                                R.style.ThemeOverlay_AppCompat_Dialog_Alert
                                            )
                                        builder.setTitle(getString(R.string.create_phone_operation))
                                        val layout = LinearLayout(context)
                                        layout.orientation = LinearLayout.VERTICAL
                                        val priorityEditText = EditText(context)
                                        priorityEditText.hint = getString(R.string.enter_priority)
                                        priorityEditText.inputType = InputType.TYPE_CLASS_NUMBER
                                        layout.addView(priorityEditText)
                                        builder.setView(layout)
                                        builder.setPositiveButton(
                                            getString(R.string.create_text),
                                            DialogInterface.OnClickListener { _, _ ->
                                                val priority = priorityEditText.text.toString()
                                                editProfileViewModel.createPhoneFromPhoneEditText(
                                                    phoneTextInputEditText.text.toString(),
                                                    priority.toInt(),
                                                    requireContext()
                                                )
                                                Navigation.findNavController(requireView()).navigate(action)
                                            })
                                        builder.setNegativeButton(getString(R.string.cancel),
                                            DialogInterface.OnClickListener { dialog, _ ->
                                                dialog.cancel()
                                            })
                                        val dialog: AlertDialog = builder.create()
                                        dialog.show()
                                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                                            false
                                        priorityEditText.addTextChangedListener(object :
                                            TextWatcher {
                                            override fun beforeTextChanged(
                                                p0: CharSequence?,
                                                p1: Int,
                                                p2: Int,
                                                p3: Int
                                            ) {

                                            }

                                            override fun onTextChanged(
                                                p0: CharSequence?,
                                                p1: Int,
                                                p2: Int,
                                                p3: Int
                                            ) {

                                            }

                                            override fun afterTextChanged(p0: Editable?) {
                                                dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                                                    !p0.toString().isNullOrEmpty()
                                            }

                                        })
                                        return true
                                    }
                                }
                            }
                        }
                    }
                    return false
                }
            })
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

                textInputLayoutArrayAddress.add(textInputLayout)
                val textInputEditText = AddressEditText(
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
                textInputEditText.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.user_address,
                    0,
                    R.drawable.remove_black,
                    0
                )
                textInputEditText.compoundDrawablePadding = 10
                textInputEditText.inputType = InputType.TYPE_NULL
                textInputEditText.isFocusableInTouchMode = false
                textInputEditText.layoutParams = textInputEditTextParams
                textInputEditText.setText(it.getAllAddressResponse[x].displayBody)
                textInputEditTextArrayAddress.add(textInputEditText)
                textInputLayoutArrayAddress[x].addView(textInputEditTextArrayAddress[x])
                linearLayout.addView(textInputLayoutArrayAddress[x])
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

            val adressTextInputEditText = TextInputEditText(requireContext())
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
            adressTextInputEditText.setOnTouchListener(object : View.OnTouchListener {
                val DRAWABLE_RIGHT = 0
                override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                    when (p1?.action) {
                        MotionEvent.ACTION_UP -> {
                            if (p1.getRawX() >= (adressTextInputEditText.right - adressTextInputEditText.compoundDrawables[DRAWABLE_RIGHT].bounds.width())) {
                                val builder: AlertDialog.Builder =
                                    AlertDialog.Builder(
                                        requireContext(),
                                        R.style.ThemeOverlay_AppCompat_Dialog_Alert
                                    )
                                builder.setTitle(getString(R.string.create_address_operation))
                                val layout = LinearLayout(context)
                                layout.orientation = LinearLayout.VERTICAL
                                val countrySpinnerArray = ArrayList<CustomCityModel>()
                                var i = 0
                                while (i < editProfileViewModel.getAllCountryResponseMutable.value?.getAllCountryValue!!.size) {
                                    countrySpinnerArray.add(
                                        CustomCityModel(
                                            editProfileViewModel.getAllCountryResponseMutable.value?.getAllCountryValue!![i].name,
                                            editProfileViewModel.getAllCountryResponseMutable.value?.getAllCountryValue!![i].id
                                        )
                                    )
                                    i++
                                }
                                val countrySpinner = Spinner(context)
                                val countrySpinnerArrayAdapter = ArrayAdapter<CustomCityModel>(
                                    requireContext(),
                                    android.R.layout.simple_spinner_dropdown_item,
                                    countrySpinnerArray
                                )
                                countrySpinner.adapter = countrySpinnerArrayAdapter
                                countrySpinner.onItemSelectedListener =
                                    object : AdapterView.OnItemSelectedListener {
                                        override fun onItemSelected(
                                            p0: AdapterView<*>?,
                                            p1: View?,
                                            p2: Int,
                                            p3: Long
                                        ) {
                                            val selectedCountry: CustomCityModel =
                                                p0!!.selectedItem as CustomCityModel
                                            countryID = selectedCountry.id
                                            countryName = selectedCountry.name
                                        }

                                        override fun onNothingSelected(p0: AdapterView<*>?) {

                                        }

                                    }


                                val citySpinnerArray = ArrayList<CustomCityModel>()
                                var j = 0
                                while (j < editProfileViewModel.getAllCityResponseMutable.value?.getAllCityValue!!.size) {
                                    citySpinnerArray.add(
                                        CustomCityModel(
                                            editProfileViewModel.getAllCityResponseMutable.value?.getAllCityValue!![j].name,
                                            editProfileViewModel.getAllCityResponseMutable.value?.getAllCityValue!![j].id
                                        )
                                    )
                                    j++
                                }
                                val citySpinner = Spinner(context)
                                val citySpinnerArrayAdapter = ArrayAdapter<CustomCityModel>(
                                    requireContext(),
                                    android.R.layout.simple_spinner_dropdown_item,
                                    citySpinnerArray
                                )
                                citySpinner.adapter = citySpinnerArrayAdapter
                                val countySpinner = Spinner(context)
                                citySpinner.onItemSelectedListener =
                                    object : AdapterView.OnItemSelectedListener {
                                        override fun onItemSelected(
                                            p0: AdapterView<*>?,
                                            p1: View?,
                                            p2: Int,
                                            p3: Long
                                        ) {
                                            val selectedCity: CustomCityModel =
                                                p0!!.selectedItem as CustomCityModel
                                            cityID = selectedCity.id
                                            cityName = selectedCity.name
                                            editProfileViewModel.getAllCounty(selectedCity.id)
                                            editProfileViewModel.getAllCountyResponseMutable.observe(
                                                viewLifecycleOwner,
                                                Observer {
                                                    var k = 0
                                                    var countySpinnerArray =
                                                        ArrayList<CustomCityModel>()
                                                    while (k < editProfileViewModel.getAllCountyResponseMutable.value?.getAllCountyValue!!.size) {
                                                        countySpinnerArray.add(
                                                            CustomCityModel(
                                                                editProfileViewModel.getAllCountyResponseMutable.value?.getAllCountyValue!![k].name,
                                                                editProfileViewModel.getAllCountyResponseMutable.value?.getAllCountyValue!![k].id
                                                            )
                                                        )
                                                        k++
                                                    }
                                                    val countySpinnerArrayAdapter =
                                                        ArrayAdapter<CustomCityModel>(
                                                            requireContext(),
                                                            android.R.layout.simple_spinner_dropdown_item,
                                                            countySpinnerArray
                                                        )
                                                    countySpinner.adapter =
                                                        countySpinnerArrayAdapter
                                                })


                                        }

                                        override fun onNothingSelected(p0: AdapterView<*>?) {

                                        }
                                    }




                                countySpinner.onItemSelectedListener =
                                    object : AdapterView.OnItemSelectedListener {
                                        override fun onItemSelected(
                                            p0: AdapterView<*>?,
                                            p1: View?,
                                            p2: Int,
                                            p3: Long
                                        ) {
                                            val selectedCounty: CustomCityModel =
                                                p0!!.selectedItem as CustomCityModel
                                            countyID = selectedCounty.id
                                            countyName = selectedCounty.name
                                        }

                                        override fun onNothingSelected(p0: AdapterView<*>?) {

                                        }

                                    }

                                val displayAddressEditText = EditText(requireContext())
                                displayAddressEditText.hint = getString(R.string.enter_address)
                                val priorityAddressEditText = EditText(requireContext())
                                priorityAddressEditText.hint = (getString(R.string.enter_priority))
                                priorityAddressEditText.inputType = InputType.TYPE_CLASS_NUMBER


                                layout.addView(countrySpinner)
                                layout.addView(citySpinner)
                                layout.addView(countySpinner)
                                layout.addView(displayAddressEditText)
                                layout.addView(priorityAddressEditText)
                                builder.setView(layout)
                                builder.setPositiveButton(
                                    getString(R.string.create_text),
                                    DialogInterface.OnClickListener { _, _ ->
                                        editProfileViewModel.createAddress(
                                            requireContext(),
                                            2,
                                            countryName,
                                            countryID,
                                            cityID,
                                            countyName,
                                            countyID,
                                            cityName,
                                            displayAddressEditText.text.toString(),
                                            priorityAddressEditText.text.toString().toInt()
                                        )
                                        Navigation.findNavController(requireView()).navigate(action)
                                    })
                                builder.setNegativeButton(getString(R.string.cancel),
                                    DialogInterface.OnClickListener { dialog, _ ->
                                        dialog.cancel()
                                    })
                                val dialog: AlertDialog = builder.create()
                                dialog.show()
                                dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false
                                displayAddressEditText.addTextChangedListener(object : TextWatcher {
                                    override fun beforeTextChanged(
                                        p0: CharSequence?,
                                        p1: Int,
                                        p2: Int,
                                        p3: Int
                                    ) {

                                    }

                                    override fun onTextChanged(
                                        p0: CharSequence?,
                                        p1: Int,
                                        p2: Int,
                                        p3: Int
                                    ) {

                                    }

                                    override fun afterTextChanged(p0: Editable?) {
                                        if (p0.toString().isNullOrEmpty()) {
                                            isButtonEnabledAddress.value = false
                                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                                                isButtonEnabledAddress.value!! && isButtonEnabledPriority.value!!
                                        } else {
                                            isButtonEnabledAddress.value = true
                                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                                                isButtonEnabledAddress.value!! && isButtonEnabledPriority.value!!
                                        }
                                    }

                                })
                                priorityAddressEditText.addTextChangedListener(object :
                                    TextWatcher {
                                    override fun beforeTextChanged(
                                        p0: CharSequence?,
                                        p1: Int,
                                        p2: Int,
                                        p3: Int
                                    ) {

                                    }

                                    override fun onTextChanged(
                                        p0: CharSequence?,
                                        p1: Int,
                                        p2: Int,
                                        p3: Int
                                    ) {

                                    }

                                    override fun afterTextChanged(p0: Editable?) {
                                        if (p0.toString().isNullOrEmpty()) {
                                            isButtonEnabledPriority.value = false
                                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                                                isButtonEnabledPriority.value!! && isButtonEnabledAddress.value!!
                                        } else {
                                            isButtonEnabledPriority.value = true
                                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                                                isButtonEnabledPriority.value!! && isButtonEnabledAddress.value!!
                                        }
                                    }

                                })
                                return true
                            }
                        }
                    }
                    return false
                }
            })
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

                textInputLayoutArrayWebAddress.add(textInputLayout)
                webAddressEditText = WebAddressEditText(
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
                webAddressEditText.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.user_web_address,
                    0,
                    R.drawable.remove_black,
                    0
                )
                webAddressEditText.compoundDrawablePadding = 10
                webAddressEditText.inputType = InputType.TYPE_NULL
                webAddressEditText.isFocusableInTouchMode = false
                webAddressEditText.isSingleLine = false
                webAddressEditText.layoutParams = textInputEditTextParams
                webAddressEditText.setText(it.getAllWebAddressValue[x].url)
                textInputEditTextArrayWebAddress.add(webAddressEditText)
                textInputLayoutArrayWebAddress[x].addView(textInputEditTextArrayWebAddress[x])
                linearLayout.addView(textInputLayoutArrayWebAddress[x])
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

            val webAddressTextInputEditText = TextInputEditText(requireContext())
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
            webAddressTextInputEditText.setOnTouchListener(object : View.OnTouchListener {
                val DRAWABLE_RIGHT = 0
                override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                    when (p1?.action) {
                        MotionEvent.ACTION_UP -> {
                            if (p1.getRawX() >= (webAddressTextInputEditText.right - webAddressTextInputEditText.compoundDrawables[DRAWABLE_RIGHT].bounds.width())) {
                                if (webAddressTextInputEditText.text.toString().isNullOrEmpty()) {
                                    webAddressTextInputEditText.error = getString(R.string.property_cannot_be_null)
                                } else {
                                    val builder: AlertDialog.Builder =
                                        AlertDialog.Builder(
                                            requireContext(),
                                            R.style.ThemeOverlay_AppCompat_Dialog_Alert
                                        )
                                    builder.setTitle(getString(R.string.create_web_address_operation))
                                    val layout = LinearLayout(context)
                                    layout.orientation = LinearLayout.VERTICAL
                                    val priorityEditText = EditText(context)
                                    priorityEditText.hint = getString(R.string.enter_priority)
                                    priorityEditText.inputType = InputType.TYPE_CLASS_NUMBER
                                    layout.addView(priorityEditText)
                                    builder.setView(layout)
                                    builder.setPositiveButton(
                                        getString(R.string.create_text),
                                        DialogInterface.OnClickListener { _, _ ->
                                            val priority = priorityEditText.text.toString()
                                            editProfileViewModel.createWebAddressFromWebAddressEditText(
                                                webAddressTextInputEditText.text.toString(),
                                                priority.toInt(),
                                                requireContext()
                                            )
                                            Navigation.findNavController(requireView()).navigate(action)
                                        })
                                    builder.setNegativeButton(getString(R.string.cancel),
                                        DialogInterface.OnClickListener { dialog, _ ->
                                            dialog.cancel()
                                        })
                                    val dialog: AlertDialog = builder.create()
                                    dialog.show()
                                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false
                                    priorityEditText.addTextChangedListener(object : TextWatcher {
                                        override fun beforeTextChanged(
                                            p0: CharSequence?,
                                            p1: Int,
                                            p2: Int,
                                            p3: Int
                                        ) {

                                        }

                                        override fun onTextChanged(
                                            p0: CharSequence?,
                                            p1: Int,
                                            p2: Int,
                                            p3: Int
                                        ) {

                                        }

                                        override fun afterTextChanged(p0: Editable?) {
                                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                                                !p0.toString().isNullOrEmpty()
                                        }

                                    })
                                    return true
                                }
                            }
                        }
                    }
                    return false
                }
            })
        })
        editProfileViewModel.isProgressBarDismissed.observe(viewLifecycleOwner, Observer {
            if(it){
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

}