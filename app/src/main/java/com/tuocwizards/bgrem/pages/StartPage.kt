package com.tuocwizards.bgrem.pages

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tuocwizards.bgrem.R
import com.tuocwizards.bgrem.databinding.StartPageBinding

class StartPage: Fragment() {

    private val appPreferences = "settings"
    private lateinit var binding: StartPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StartPageBinding.inflate(inflater, container, false)

        binding.apply {
            startButton.setOnClickListener {
                agreeWithConditions()
                goByAction(R.id.action_startPage_to_mainPage) }
            termsOfUse.setOnClickListener { openTermsOfUse() }
            privacyPolicy.setOnClickListener { openPrivacyPolicy() }
            infoButton.setOnClickListener { goByAction(R.id.action_startPage_to_infoPage) }
        }
        return binding.root
    }

    private fun agreeWithConditions() {
        context!!.getSharedPreferences(appPreferences, Context.MODE_PRIVATE)
            .edit().putBoolean("isUserAgreeWithConditions", true).apply()
    }

    private fun goByAction(actionId: Int) {
        findNavController().navigate(actionId)
    }

    private fun openTermsOfUse() {
        val intent = Intent.parseUri("https://bgrem.deelvin.com/terms_of_use/?app=true", Intent.URI_INTENT_SCHEME)
        startActivity(intent)
    }

    private fun openPrivacyPolicy() {
        val intent = Intent.parseUri("https://bgrem.deelvin.com/privacy_policy/?app=true", Intent.URI_INTENT_SCHEME)
        startActivity(intent)
    }
}