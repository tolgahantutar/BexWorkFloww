package com.tolgahantutar.bexworkfloww.ui.addressbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.data.models.getcontact.GetContactValue
import kotlinx.android.synthetic.main.contacts_layout.view.*

class ContactValueAdapter(private val contactValue : List<GetContactValue>) :RecyclerView.Adapter<ContactValueAdapter.ContactViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactValueAdapter.ContactViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.contacts_layout,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ContactValueAdapter.ContactViewHolder, position: Int) {
            holder.view.contac_name_textview.text =contactValue[position].name
        var getContactValue = contactValue[position]
            holder.view.setOnClickListener {
                val action = AdressBookFragmentDirections.actionUserDetail(contactValue[position])
                Navigation.findNavController(it).navigate(action)
            }
    }

    override fun getItemCount()=contactValue.size
class ContactViewHolder(val view : View) : RecyclerView.ViewHolder(view)
}