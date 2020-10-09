package com.tolgahantutar.bexworkfloww.ui.addressbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.data.network.repositories.GetUserGlobalRepository
import com.tolgahantutar.bexworkfloww.ui.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdressBookFragment : Fragment() {
    private val addressBookViewModel : AdressBookViewModel by viewModels()
   private val getUserGlobalRepository= GetUserGlobalRepository.instance

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.adress_book_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
addressBookViewModel.getContact(2,getUserGlobalRepository.result!!.getUserValue.apiKey)

addressBookViewModel.isSuccessfull.observe(viewLifecycleOwner, Observer {
            if (it){
                Toast.makeText(requireContext(), "ContactList Get Successfully", Toast.LENGTH_SHORT).show()
            }
        })
    }
}