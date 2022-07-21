package com.tuocwizards.bgrem.pages

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tuocwizards.bgrem.databinding.InfoPageBinding
import com.tuocwizards.bgrem.models.datastorages.storages.LinksStorage

class InfoPage: BaseFragment<InfoPageBinding>(
    InfoPageBinding::inflate
) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = InfoPageBinding.inflate(inflater)
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
        val intent = Intent.parseUri(LinksStorage.emailLink, Intent.URI_INTENT_SCHEME)
        startActivity(intent)
    }

    private fun openPrivacyPolicy() {
        val intent = Intent.parseUri(LinksStorage.privacyPolicyLink, Intent.URI_INTENT_SCHEME)
        startActivity(intent)
    }
}