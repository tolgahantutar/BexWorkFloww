package com.tolgahantutar.bexworkfloww.ui.addressbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.data.network.repositories.GetUserGlobalRepository
import com.tolgahantutar.bexworkfloww.ui.auth.SharedPrefSingleton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.adress_book_fragment.*

@AndroidEntryPoint
class AdressBookFragment : Fragment() {
    private val addressBookViewModel : AdressBookViewModel by viewModels()
   private val getUserGlobalRepository= GetUserGlobalRepository.instance
    private val sharedPrefSingleton = SharedPrefSingleton.instance

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.adress_book_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addressBookViewModel.getContact(2,sharedPrefSingleton.getSomeStringValue(requireContext())/*getUserGlobalRepository.apiKey*/)
                addressBookViewModel.mutableGetContactResponse.observe(viewLifecycleOwner, Observer {
                recycler_view_contacts.adapter =ContactValueAdapter(it.getContactValue)
                })


        recycler_view_contacts.setHasFixedSize(true)
        recycler_view_contacts.layoutManager =LinearLayoutManager(requireContext())
    }
}