package com.tolgahantutar.bexworkfloww.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tolgahantutar.bexworkfloww.R

class AdressBookFragment : Fragment() {

    companion object {
        fun newInstance() = AdressBookFragment()
    }

    private lateinit var viewModel: AdressBookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.adress_book_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AdressBookViewModel::class.java)
        // TODO: Use the ViewModel
    }

}