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
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.data.models.CustomCityModel
import com.tolgahantutar.bexworkfloww.data.models.requestbodies.CreateAddressBody
import com.tolgahantutar.bexworkfloww.data.models.requestbodies.RequestBodyCountry
import com.tolgahantutar.bexworkfloww.data.models.requestbodies.RequestBodyCounty
import com.tolgahantutar.bexworkfloww.data.models.requestbodies.RequestBodyState
import com.tolgahantutar.bexworkfloww.ui.editprofile.EditProfileFragmentDirections
import com.tolgahantutar.bexworkfloww.ui.editprofile.EditProfileViewModel

class AddressEditText constructor(
    context: Context,
    countryName:String,
    countryID: Int,
    countyName:String,
    countyID: Int,
    cityName: String,
    cityID: Int,
    id:Int,
    editProfileViewModel: EditProfileViewModel,
    priority: Int,
    body:String,
    lifecycleOwner: LifecycleOwner,
    view: View

): TextInputEditText(context){
    val editText = this@AddressEditText
    var countryIDUpdate = 0
    var countryNameUpdate=""
    var cityIDUpdate = 0
    var cityNameUpdate = ""
    var countyIDUpdate =0
    var countyNameUpdate=""
    val isAddressEnabled = MutableLiveData<Boolean>(true)
    val isPriorityEnabled = MutableLiveData<Boolean>(true)
    val action = EditProfileFragmentDirections.actionEditProfileFragmentSelf()
    init {
        editText.setOnClickListener {
            var citySpinnerCount = 0
            val builder: AlertDialog.Builder =
                AlertDialog.Builder(
                    context,
                    R.style.ThemeOverlay_AppCompat_Dialog_Alert
                )
            builder.setTitle(context.getString(R.string.update_address))
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
                         countryIDUpdate= selectedCountry.id
                        countryNameUpdate = selectedCountry.name
                        Toast.makeText(context, "$countryID", Toast.LENGTH_SHORT).show()
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }
                }
            val citySpinnerArray = ArrayList<CustomCityModel>()
            citySpinnerArray.add(CustomCityModel(cityName,cityID))
            var j = 0
            if(citySpinnerCount ==0){

            }
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
            citySpinner.setSelection(0)
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
                        cityIDUpdate = selectedCity.id
                        cityNameUpdate = selectedCity.name
                        editProfileViewModel.getAllCounty(selectedCity.id)
                       editProfileViewModel.getAllCountyResponseMutable.observe(lifecycleOwner, Observer {
                           var k = 0
                           val countySpinnerArray =
                               ArrayList<CustomCityModel>()
                           if(citySpinnerCount<=1){
                               countySpinnerArray.add(CustomCityModel(countyName,countyID))
                           }
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
                           countySpinner.setSelection(0)
                       })
                        citySpinnerCount++

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
                        countyIDUpdate = selectedCounty.id
                        countyNameUpdate = selectedCounty.name
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                }

            val displayAddressEditText = EditText(context)
            displayAddressEditText.setText(body)
            displayAddressEditText.hint = context.getString(R.string.enter_address)
            val priorityAddressEditText = EditText(context)
            priorityAddressEditText.setText(priority.toString())
            priorityAddressEditText.hint = (context.getString(R.string.enter_priority))
            priorityAddressEditText.inputType = InputType.TYPE_CLASS_NUMBER
            layout.addView(countrySpinner)
            layout.addView(citySpinner)
            layout.addView(countySpinner)
            layout.addView(displayAddressEditText)
            layout.addView(priorityAddressEditText)
            builder.setView(layout)

            builder.setPositiveButton(
                context.getString(R.string.update),
                DialogInterface.OnClickListener { _, _ ->
                    editProfileViewModel.updateAddress(context,2,countryNameUpdate,countryIDUpdate,cityIDUpdate,countyNameUpdate,
                    countyIDUpdate,cityNameUpdate,displayAddressEditText.text.toString(),priorityAddressEditText.text.toString().toInt(),id)
                    Navigation.findNavController(view).navigate(action)
                })
            builder.setNegativeButton(context.getString(R.string.cancel),
                DialogInterface.OnClickListener { dialog, _ ->
                    dialog.cancel()
                })
            val dialog: AlertDialog = builder.create()
            dialog.show()
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled=true

            displayAddressEditText.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0.toString().isNullOrEmpty()){
                        isAddressEnabled.value=false
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = isAddressEnabled.value!! && isPriorityEnabled.value!!
                    }else{
                        isAddressEnabled.value=true
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = isAddressEnabled.value!! && isPriorityEnabled.value!!
                    }
                }

            })
            priorityAddressEditText.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if(p0.toString().isNullOrEmpty()){
                        isPriorityEnabled.value=false
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = isAddressEnabled.value!! && isPriorityEnabled.value!!
                    }else{
                        isPriorityEnabled.value=true
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = isAddressEnabled.value!! && isPriorityEnabled.value!!
                    }
                }

            })
        }

        editText.setOnTouchListener(object : View.OnTouchListener {
            val DRAWABLE_RIGHT = 0
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                when (p1?.action) {
                    MotionEvent.ACTION_UP -> {
                        if (p1.getRawX() >= (editText.right - editText.compoundDrawables[DRAWABLE_RIGHT].bounds.width())) {
                            val builder: AlertDialog.Builder =
                                AlertDialog.Builder(
                                    context,
                                    R.style.ThemeOverlay_AppCompat_Dialog_Alert
                                )
                            builder.setTitle(context.getString(R.string.delete_address_operation))
                            builder.setMessage(context.getString(R.string.sure_delete_address))
                            builder.setPositiveButton(
                                "Sil",
                                DialogInterface.OnClickListener { _, _ ->
                                editProfileViewModel.deleteAddress(context,2,countryName,countryID,cityID,countyName,countyID,cityName,body,priority,id)
                                    Navigation.findNavController(view).navigate(action)
                                })
                            builder.setNegativeButton(R.string.cancel,
                                DialogInterface.OnClickListener { dialog, _ ->
                                    dialog.cancel()
                                })
                            builder.show()
                            return true
                        }
                    }
                }
                return false
            }
        })
    }
}