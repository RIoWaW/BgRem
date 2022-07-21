package com.tuocwizards.bgrem.pages

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tuocwizards.bgrem.R
import com.tuocwizards.bgrem.databinding.SplashPageBinding
import com.tuocwizards.bgrem.viewmodels.SplashPageVM

class SplashPage : BaseFragment<SplashPageBinding>(
    SplashPageBinding::inflate
) {

    private val appPreferences = "settings"
    private val viewModel: SplashPageVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getBackgrounds()
        binding = SplashPageBinding.inflate(inflater)
        val isUserAgreeWithConditions = context!!
            .getSharedPreferences(appPreferences, Context.MODE_PRIVATE)
            .getBoolean("isUserAgreeWithConditions", false)

        if (isUserAgreeWithConditions) {
            goToNextPageWithAnimation(R.id.action_splashPage_to_mainPage)
        } else {
            goToNextPageWithAnimation(R.id.action_splashPage_to_startPage)
        }

        return binding.root
    }

    private fun goToNextPageWithAnimation(actionId: Int) = binding.apply {
        rem.post {
            rem.animate()
                .translationX(bg.width.toFloat())
                .setDuration(2000)
                .setStartDelay(400)
                .start()
            bg.animate()
                .translationX(-rem.width.toFloat())
                .setDuration(2000)
                .setStartDelay(400)
                .withEndAction {
                    goByAction(actionId)
                }.start()
        }
    }

    private fun goByAction(actionId: Int) {
        findNavController().navigate(actionId)
    }
}