package com.tolgahantutar.bexworkfloww.ui.settings

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tolgahantutar.bexworkfloww.R
import com.tolgahantutar.bexworkfloww.ui.auth.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.settings_fragment.*

@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private val settingsViewModel : SettingsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        settings_logout.setOnClickListener {
            startActivity(Intent(requireActivity(),LoginActivity::class.java))
            settingsViewModel.cleanUserApi()
            requireActivity().finish()

        }
    }

}