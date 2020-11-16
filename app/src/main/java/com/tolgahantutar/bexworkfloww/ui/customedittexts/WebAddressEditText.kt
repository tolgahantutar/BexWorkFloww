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

class WebAddressEditText constructor(
    context: Context,
    id: Int,
    editProfileViewModel: EditProfileViewModel,
    priority: Int,
    view: View
): TextInputEditText(context){
    val editText = this@WebAddressEditText
    val isWebAddressEnabled = MutableLiveData<Boolean>(true)
    val isPriorityEnabled = MutableLiveData<Boolean>(true)
    val action = EditProfileFragmentDirections.actionEditProfileFragmentSelf()
    init {
        editText.setOnClickListener {
            val builder: AlertDialog.Builder =
                AlertDialog.Builder(
                    context,
                    R.style.ThemeOverlay_AppCompat_Dialog_Alert
                )
            builder.setTitle(context.getString(R.string.update_web_adress_operation))
            val layout = LinearLayout(context)
            layout.orientation = LinearLayout.VERTICAL
            val webAddressEditText = EditText(context)
            webAddressEditText.setText(editText.text)
            webAddressEditText.hint = context.getString(R.string.enter_your_web_address)
            val priorityEditText = EditText(context)
            priorityEditText.setText(priority.toString())
            priorityEditText.hint = context.getString(R.string.enter_priority)
            priorityEditText.inputType = InputType.TYPE_CLASS_NUMBER
            layout.addView(webAddressEditText)
            layout.addView(priorityEditText)
            builder.setView(layout)
            builder.setPositiveButton(
                context.getString(R.string.update_web_address),
                DialogInterface.OnClickListener { _, _ ->
                    val strUrl = webAddressEditText.text.toString()
                    val strPriority = priorityEditText.text.toString()
                    editProfileViewModel.updateWebAddressFromWebAddressEditText(strUrl,strPriority.toInt(),id,context)
                    Navigation.findNavController(view).navigate(action)
                })
            builder.setNegativeButton(context.getString(R.string.cancel),
                DialogInterface.OnClickListener { dialog, _ ->
                    dialog.cancel()
                })
            val dialog: AlertDialog = builder.create()
            dialog.show()
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled=true
            webAddressEditText.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0.toString().isNullOrEmpty()){
                        isWebAddressEnabled.value=false
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = isWebAddressEnabled.value!! && isPriorityEnabled.value!!
                    }else{
                        isWebAddressEnabled.value=true
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = isWebAddressEnabled.value!! && isPriorityEnabled.value!!
                    }
                }

            })
            priorityEditText.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if(p0.toString().isNullOrEmpty()){
                        isPriorityEnabled.value=false
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = isWebAddressEnabled.value!! && isPriorityEnabled.value!!
                    }else{
                        isPriorityEnabled.value=true
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = isWebAddressEnabled.value!! && isPriorityEnabled.value!!
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
                            builder.setTitle(context.getString(R.string.delete_web_address_operation))
                            builder.setMessage(context.getString(R.string.sure_delete_web_address))
                            builder.setPositiveButton(
                                context.getString(R.string.delete_web_address),
                                DialogInterface.OnClickListener { _, _ ->
                                    editProfileViewModel.deleteWebAddressFromWebAddressEditText(editText.text.toString(),priority,id,context)
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