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
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.ui.editprofile.EditProfileViewModel

class CreateWebAddressEditText constructor(
    context: Context,
    editProfileViewModel: EditProfileViewModel,
    view: View,
    action: NavDirections
):TextInputEditText(context){
    val webAddressTextInputEditText = this@CreateWebAddressEditText
    init {
        webAddressTextInputEditText.setOnTouchListener(object : View.OnTouchListener {
            val DRAWABLE_RIGHT = 0
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                when (p1?.action) {
                    MotionEvent.ACTION_UP -> {
                        if (p1.getRawX() >= (webAddressTextInputEditText.right - webAddressTextInputEditText.compoundDrawables[DRAWABLE_RIGHT].bounds.width())) {
                            if (webAddressTextInputEditText.text.toString().isNullOrEmpty()) {
                                webAddressTextInputEditText.error = context.getString(R.string.property_cannot_be_null)
                            } else {
                                val builder: AlertDialog.Builder =
                                    AlertDialog.Builder(
                                        context,
                                        R.style.ThemeOverlay_AppCompat_Dialog_Alert
                                    )
                                builder.setTitle(context.getString(R.string.create_web_address_operation))
                                val layout = LinearLayout(context)
                                layout.orientation = LinearLayout.VERTICAL
                                val priorityEditText = EditText(context)
                                priorityEditText.hint = context.getString(R.string.enter_priority)
                                priorityEditText.inputType = InputType.TYPE_CLASS_NUMBER
                                layout.addView(priorityEditText)
                                builder.setView(layout)
                                builder.setPositiveButton(
                                    context.getString(R.string.create_text),
                                    DialogInterface.OnClickListener { _, _ ->
                                        val priority = priorityEditText.text.toString()
                                        editProfileViewModel.createWebAddressFromWebAddressEditText(
                                            webAddressTextInputEditText.text.toString(),
                                            priority.toInt(),
                                            context
                                        )
                                        Navigation.findNavController(view).navigate(action)
                                    })
                                builder.setNegativeButton(context.getString(R.string.cancel),
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
    }
}