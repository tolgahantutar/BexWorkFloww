package com.tolgahantutar.bexworkfloww.ui.editprofile

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.*
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.databinding.EditProfileFragmentBinding
import com.tolgahantutar.bexworkfloww.data.sharedpref.SharedPrefSingletonAuthID
import com.tolgahantutar.bexworkfloww.data.sharedpref.SharedPrefSingletonDomainKey
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.edit_profile_fragment.*
import kotlinx.android.synthetic.main.user_detail_address_fragment.*


@AndroidEntryPoint
class EditProfileFragment : Fragment() {
    private lateinit var binding : EditProfileFragmentBinding
    private val editProfileViewModel: EditProfileViewModel by viewModels()
    lateinit var menuSaveItem: MenuItem
    lateinit var title: String
    lateinit var spannable: SpannableString
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = EditProfileFragmentBinding.inflate(inflater,container,false)
        binding.editProfile=editProfileViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /*val relativeLayout = requireView().findViewById<RelativeLayout>(R.id.edit_profile_relative_layout)

        val textInputLayout = TextInputLayout(requireContext())
        val textInputLayoutParams : LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textInputLayout.hint = getString(R.string.Phone)
        textInputLayout.setBackgroundColor(Color.WHITE)
        textInputLayoutParams.setMargins(0,0,0,10)
        textInputLayout.layoutParams = textInputLayoutParams

        val textInputEditText = TextInputEditText(requireContext())
        val textInputEditTextParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textInputEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.user_phone,0,R.drawable.remove_black,0)
        textInputEditText.compoundDrawablePadding = 10
        textInputEditText.inputType = InputType.TYPE_CLASS_PHONE
        textInputEditText.layoutParams = textInputEditTextParams

        textInputLayout.addView(textInputEditText)
        relativeLayout.addView(textInputLayout)*/

        editProfileViewModel.executeUserResponse()
        /*text_phone_edit_profile.setOnTouchListener(object : View.OnTouchListener {
            val DRAWABLE_RIGHT = 0
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                when (p1?.action) {
                    MotionEvent.ACTION_UP -> {
                        if (p1.getRawX() >= (text_phone_edit_profile.right - text_phone_edit_profile.compoundDrawables[DRAWABLE_RIGHT].bounds.width())) {
                            val builder: AlertDialog.Builder =
                                AlertDialog.Builder(
                                    requireContext(),
                                    R.style.ThemeOverlay_AppCompat_Dialog_Alert
                                )
                            builder.setTitle("Telefon oluştur.")
                            val layout = LinearLayout(requireContext())
                            layout.orientation = LinearLayout.VERTICAL
                            val mailEditText = EditText(requireContext())
                            mailEditText.hint = "Telefonunuzu Yazın."
                            val priorityEditText = EditText(requireContext())
                            priorityEditText.hint = "Priority yazın."
                            layout.addView(mailEditText)
                            layout.addView(priorityEditText)
                            builder.setView(layout)
                            builder.setPositiveButton(
                                "Oluştur",
                                DialogInterface.OnClickListener { dialog, which ->
                                    val strMail = mailEditText.text.toString()
                                    val strPriority = priorityEditText.text.toString()
                                    Toast.makeText(requireContext(), "Telefon Oluşturuldu", Toast.LENGTH_SHORT).show()
                                })
                                builder.setNegativeButton("Vazgeç",DialogInterface.OnClickListener { dialog, which ->
                                    dialog.cancel()
                                })
                            builder.show()
                            return true
                        }
                    }
                }
                return false
            }
        })*/
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
        menuSaveItem =menu.findItem(R.id.save_profile)
         title= menuSaveItem.title.toString()
        spannable = SpannableString(title)
        spannable.setSpan(ForegroundColorSpan(Color.parseColor("#808080")),0,spannable.length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        menuSaveItem.title=spannable
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        editProfileViewModel.isEnabled.observe(viewLifecycleOwner, Observer {
            menu.findItem(R.id.save_profile).setEnabled(it)
            if(it){
                spannable.setSpan(ForegroundColorSpan(Color.parseColor("#FFFFFF")),0,spannable.length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                menuSaveItem.title=spannable
            }
            else{
                spannable.setSpan(ForegroundColorSpan(Color.parseColor("#808080")),0,spannable.length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                menuSaveItem.title=spannable
            }
        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save_profile ->{
                //editProfileViewModel.onClickSaveButton(text_email_edit_profile.text.toString(),text_phone_edit_profile.text.toString()
                   // ,text_web_address_edit_profile.text.toString(),requireView())
            }
        }
        return super.onOptionsItemSelected(item)
    }
}