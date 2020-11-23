package com.tolgahantutar.bexworkfloww.ui.customedittexts

import android.content.Context
import android.content.DialogInterface
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.data.models.CustomCityModel
import com.tolgahantutar.bexworkfloww.ui.editprofile.EditProfileViewModel

class CreateAddressEditText constructor(
    context: Context,
    editProfileViewModel: EditProfileViewModel,
    viewLifecycleOwner: LifecycleOwner,
    view: View,
    action: NavDirections

):TextInputEditText(context){
    val adressTextInputEditText = this@CreateAddressEditText
    init {
        adressTextInputEditText.setOnTouchListener(object : View.OnTouchListener {
            var countryID =0
            var countryName = ""
            var cityID= 0
            var cityName = ""
            var countyID = 0
            var countyName = ""
            val DRAWABLE_RIGHT = 0
            private var isButtonEnabledAddress = MutableLiveData<Boolean>(false)
            private var isButtonEnabledPriority = MutableLiveData<Boolean>(false)
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                when (p1?.action) {
                    MotionEvent.ACTION_UP -> {
                        if (p1.getRawX() >= (adressTextInputEditText.right - adressTextInputEditText.compoundDrawables[DRAWABLE_RIGHT].bounds.width())) {
                            val builder: AlertDialog.Builder =
                                AlertDialog.Builder(
                                   context,
                                    R.style.ThemeOverlay_AppCompat_Dialog_Alert
                                )
                            builder.setTitle(context.getString(R.string.create_address_operation))
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
                                context,
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
                                context,
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
                                                        context,
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

                            val displayAddressEditText = EditText(context)
                            displayAddressEditText.inputType = InputType.TYPE_TEXT_FLAG_CAP_WORDS
                            displayAddressEditText.hint = context.getString(R.string.enter_address)
                            val priorityAddressEditText = EditText(context)
                            priorityAddressEditText.hint = (context.getString(R.string.enter_priority))
                            priorityAddressEditText.inputType = InputType.TYPE_CLASS_NUMBER


                            layout.addView(countrySpinner)
                            layout.addView(citySpinner)
                            layout.addView(countySpinner)
                            layout.addView(displayAddressEditText)
                            layout.addView(priorityAddressEditText)
                            builder.setView(layout)
                            builder.setPositiveButton(
                                context.getString(R.string.create_text),
                                DialogInterface.OnClickListener { _, _ ->
                                    editProfileViewModel.createAddress(
                                        context,
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
                                    editProfileViewModel.createAddressResponseMutable.observe(viewLifecycleOwner,
                                        Observer {
                                            if (it.result){
                                                Navigation.findNavController(view).navigate(action)
                                            }
                                        })

                                })
                            builder.setNegativeButton(context.getString(R.string.cancel),
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
    }
}