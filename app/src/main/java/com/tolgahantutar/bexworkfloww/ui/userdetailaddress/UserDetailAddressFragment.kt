package com.tolgahantutar.bexworkfloww.ui.userdetailaddress


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.data.models.getcontact.GetContactValue
import kotlinx.android.synthetic.main.user_detail_address_fragment.*

class UserDetailAddressFragment : Fragment() {
var userDetail : GetContactValue ? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_detail_address_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
        userDetail=UserDetailAddressFragmentArgs.fromBundle(
        it
        ).GetContactDetail
            textview_username.text=userDetail?.name
            text_phone.setText(userDetail?.defaultPhone?.cleanBody)
            text_email.setText(userDetail?.defaultEmail?.address)
            text_address.setText(userDetail?.defaultAddress?.displayBody)
            text_web_address.setText(userDetail?.defaultWebAddress?.url)

        }
        }
    }

