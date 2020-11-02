package com.tolgahantutar.bexworkfloww.ui.addressbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.data.sharedpref.SharedPrefSingletonUserAPI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.adress_book_fragment.*

@Suppress("DEPRECATION")
@AndroidEntryPoint
class AdressBookFragment : Fragment() {
    private val addressBookViewModel : AdressBookViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.adress_book_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addressBookViewModel.executeGetContact()
                addressBookViewModel.mutableGetContactResponse.observe(viewLifecycleOwner, Observer {
                recycler_view_contacts.adapter =ContactValueAdapter(it.getContactValue)
                })
        recycler_view_contacts.setHasFixedSize(true)
        recycler_view_contacts.layoutManager =LinearLayoutManager(requireContext())
    }
}