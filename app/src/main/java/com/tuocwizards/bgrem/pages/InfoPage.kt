package com.tuocwizards.bgrem.pages

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.tuocwizards.bgrem.databinding.InfoPageBinding

class InfoPage: Fragment() {

    private lateinit var viewModel: ViewModel
    private lateinit var binding: InfoPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = InfoPageBinding.inflate(inflater, container, false)
        binding.apply {
            backButton.setOnClickListener {
                findNavController().navigateUp()
            }
            email.setOnClickListener { openSupportEmail() }
            privacyPolicy.setOnClickListener { openPrivacyPolicy() }
        }

        return binding.root
    }

    private fun openSupportEmail() {
        val intent = Intent.parseUri("mailto:support@deelvin.com", Intent.URI_INTENT_SCHEME)
        startActivity(intent)
    }

    private fun openPrivacyPolicy() {
        val intent = Intent.parseUri("https://bgrem.deelvin.com/privacy_policy/?app=true", Intent.URI_INTENT_SCHEME)
        startActivity(intent)
    }

}