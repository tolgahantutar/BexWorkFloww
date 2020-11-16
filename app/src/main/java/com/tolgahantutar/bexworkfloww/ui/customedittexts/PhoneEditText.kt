package com.tolgahantutar.bexworkfloww.ui.customedittexts

import android.content.Context
import android.content.DialogInterface
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.ui.editprofile.EditProfileFragmentDirections
import com.tolgahantutar.bexworkfloww.ui.editprofile.EditProfileViewModel
import com.tolgahantutar.bexworkfloww.validations.EmailValidation
import com.tolgahantutar.bexworkfloww.validations.PhoneValidation

class PhoneEditText constructor(
    context: Context,
    id: Int,
    editProfileViewModel: EditProfileViewModel,
    priority: Int,
    view: View
): TextInputEditText(context){
    val editText = this@PhoneEditText
    val isPhoneEnabled = MutableLiveData<Boolean>(true)
    val isPriorityEnabled = MutableLiveData<Boolean>(true)
    val action = EditProfileFragmentDirections.actionEditProfileFragmentSelf()
    init {
        editText.setOnClickListener {
            val builder: AlertDialog.Builder =
                AlertDialog.Builder(
                    context,
                    R.style.ThemeOverlay_AppCompat_Dialog_Alert
                )
            builder.setTitle(context.getString(R.string.update_phone))
            val layout = LinearLayout(context)
            layout.orientation = LinearLayout.VERTICAL
            val phoneEditText = EditText(context)
            phoneEditText.setText(editText.text)
            phoneEditText.hint = context.getString(R.string.type_your_phone_please)
            phoneEditText.isSingleLine=true
            phoneEditText.inputType = InputType.TYPE_CLASS_PHONE
            val priorityEditText = EditText(context)
            priorityEditText.setText(priority.toString())
            priorityEditText.hint = context.getString(R.string.enter_priority)
            priorityEditText.inputType = InputType.TYPE_CLASS_NUMBER
            layout.addView(phoneEditText)
            layout.addView(priorityEditText)
            builder.setView(layout)
            builder.setPositiveButton(
                context.getString(R.string.update),
                DialogInterface.OnClickListener { _, _ ->
                    val strPhone = phoneEditText.text.toString()
                    val strPriority = priorityEditText.text.toString()
                    editProfileViewModel.updatePhoneFromMailEditText(strPhone,strPriority.toInt(),id,context)
                    Navigation.findNavController(view).navigate(action)
                })
            builder.setNegativeButton(context.getString(R.string.cancel),
                DialogInterface.OnClickListener { dialog, _ ->
                    dialog.cancel()
                })
            val dialog: AlertDialog = builder.create()
            dialog.show()
            phoneEditText.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0.toString().isNullOrEmpty()){
                        isPhoneEnabled.value=false
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                            isPhoneEnabled.value!! && isPriorityEnabled.value!!
                    }else{
                        if(PhoneValidation().checkPhoneNumber(p0.toString())){
                            isPhoneEnabled.value=true
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                                isPhoneEnabled.value!! && isPriorityEnabled.value!!
                        }else{
                            isPhoneEnabled.value=false
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                                isPhoneEnabled.value!! && isPriorityEnabled.value!!
                        }
                    }
                }

            })
            priorityEditText.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0.toString().isNullOrEmpty()){
                        isPriorityEnabled.value=false
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = isPhoneEnabled.value!! && isPriorityEnabled.value!!
                    }
                    else{
                        isPriorityEnabled.value=true
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = isPhoneEnabled.value!! && isPriorityEnabled.value!!
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
                            builder.setTitle(context.getString(R.string.delete_phone_operation))
                            builder.setMessage(context.getString(R.string.sure_delete_phone))
                            builder.setPositiveButton(
                                context.getString(R.string.delete_phone),
                                DialogInterface.OnClickListener { _, _ ->
                                    editProfileViewModel.deletePhoneFromPhoneEditText(editText.text.toString(),priority,id,context)
                                    Navigation.findNavController(view).navigate(action)
                                })
                            builder.setNegativeButton(context.getString(R.string.cancel),
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