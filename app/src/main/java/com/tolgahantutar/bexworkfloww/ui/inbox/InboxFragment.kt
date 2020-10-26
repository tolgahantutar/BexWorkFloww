package com.tolgahantutar.bexworkfloww.ui.inbox

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.ui.auth.LoginActivity
import com.tolgahantutar.bexworkfloww.ui.auth.SharedPrefSingleton
import kotlinx.android.synthetic.main.inbox_fragment.*


class InboxFragment : Fragment() {
    private lateinit var viewModel: InboxViewModel
    //private lateinit var toolbar: Toolbar
   // private val sharedPrefSingleton = SharedPrefSingleton.instance

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.inbox_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(InboxViewModel::class.java)
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
                SharedPrefSingleton.setSomeStringValue(requireContext(), "notGenerated")
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