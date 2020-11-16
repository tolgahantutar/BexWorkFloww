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
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.ui.editprofile.EditProfileFragmentDirections
import com.tolgahantutar.bexworkfloww.ui.editprofile.EditProfileViewModel
import com.tolgahantutar.bexworkfloww.validations.EmailValidation

class MailEditText constructor(
    context: Context,
    id: Int,
    editProfileViewModel: EditProfileViewModel,
    priority: Int,
    view: View
): TextInputEditText(context){
    val editText = this@MailEditText
    val isEmailEnabled = MutableLiveData<Boolean>(true)
    val isPriorityEnabled = MutableLiveData<Boolean>(true)
    val action = EditProfileFragmentDirections.actionEditProfileFragmentSelf()
    init {
        editText.setOnClickListener {
                          val builder: AlertDialog.Builder =
                              AlertDialog.Builder(
                                  context,
                                  R.style.ThemeOverlay_AppCompat_Dialog_Alert
                              )
                          builder.setTitle(context.getString(R.string.update_email))
                          val layout = LinearLayout(context)
                          layout.orientation = LinearLayout.VERTICAL
                          val mailEditText = EditText(context)
                            mailEditText.setText(editText.text)
                          mailEditText.hint = context.getString(R.string.type_your_mail_please)
                          mailEditText.isSingleLine=true
                          val priorityEditText = EditText(context)
                            priorityEditText.setText(priority.toString())
                          priorityEditText.hint = context.getString(R.string.enter_priority)
                            priorityEditText.inputType = InputType.TYPE_CLASS_NUMBER
                          layout.addView(mailEditText)
                          layout.addView(priorityEditText)
                          builder.setView(layout)
                          builder.setPositiveButton(
                              R.string.update,
                              DialogInterface.OnClickListener { _, _ ->
                                  val strMail = mailEditText.text.toString()
                                  val strPriority = priorityEditText.text.toString()
                                  editProfileViewModel.updateMailFromMailEditText(strMail,strPriority.toInt(),id,context)
                                  Navigation.findNavController(view).navigate(action)
                              })
                              builder.setNegativeButton(R.string.cancel,
                                  DialogInterface.OnClickListener { dialog, _ ->
                                  dialog.cancel()
                              })
                          val dialog: AlertDialog = builder.create()
                          dialog.show()
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled=true
                        mailEditText.addTextChangedListener(object: TextWatcher{
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
                                if (p0.toString().isNullOrEmpty()){
                                    isEmailEnabled.value=false
                                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                                        isEmailEnabled.value!! && isPriorityEnabled.value!!
                                }else{
                                    if(EmailValidation().checkEmail(p0.toString())){
                                        isEmailEnabled.value=true
                                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                                            isEmailEnabled.value!! && isPriorityEnabled.value!!
                                    }else{
                                        isEmailEnabled.value=false
                                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                                            isEmailEnabled.value!! && isPriorityEnabled.value!!
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
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = isEmailEnabled.value!! && isPriorityEnabled.value!!
                    }
                    else{
                        isPriorityEnabled.value=true
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = isEmailEnabled.value!! && isPriorityEnabled.value!!
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
                            builder.setTitle(context.getString(R.string.email_delete_operation))
                            builder.setMessage(context.getString(R.string.sure_delete_mail))
                            builder.setPositiveButton(
                                "Sil",
                                DialogInterface.OnClickListener { _, _ ->
                                    editProfileViewModel.deleteMailFromMailEditText(editText.text.toString(),priority,id,context)
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