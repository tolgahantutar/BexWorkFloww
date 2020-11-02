package com.tolgahantutar.bexworkfloww.ui.inbox

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.ui.auth.LoginActivity
import com.tolgahantutar.bexworkfloww.ui.auth.SharedPrefSingletonUserAPI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.inbox_fragment.*

@AndroidEntryPoint
class InboxFragment : Fragment() {
    private val inboxViewModel : InboxViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.inbox_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        button_filter.setOnClickListener {
            val popup = PopupMenu(requireContext(), it)
            popup.inflate(R.menu.filter_menu)
            popup.show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(activity, LoginActivity::class.java)
        when(item.itemId){
            R.id.item_logout -> {
                startActivity(intent)
                SharedPrefSingletonUserAPI.setSomeStringValue(requireContext(), "notGenerated")
                activity?.onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.profile_menu, menu)
    }
}